package in.ac.iiitmk.ap.services;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import in.ac.iiitmk.ap.models.*;
import in.ac.iiitmk.ap.models.web.*;
import in.ac.iiitmk.ap.repositories.*;
import in.ac.iiitmk.ap.services.api.*;

/**
 * {@link SimpleUserService} is the implementation of the contract defined by {@link UserService}
 * @author pcnicky
 *
 */
@Service
public class SimpleUserService implements UserService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SimpleUserService.class);
	
	private UserRepository mUserRepository;
	
	private UserAccountInformationService mUserAccountInformationService;
	
	@Autowired
	public SimpleUserService (UserRepository userRepository, UserAccountInformationService userAccountInformationService) {
		this.mUserRepository = userRepository;
		this.mUserAccountInformationService = userAccountInformationService;
	}

	@Override
	public Optional<User> getUserById(Long id) {
		User user = this.mUserRepository.findOne(id);
		if (user == null) {
			LOGGER.info("No user with id: {} was found", id);
		} else {
			LOGGER.info ("User with id: {} was found to be: {}", id, user);
		}
		return Optional.ofNullable(user);
	}
	
	@Override
	public Optional<List<User>> getAllUser () {
		List<User> allUsers = this.mUserRepository.findAll();
		LOGGER.info("A total of {} users were found in the database.", allUsers == null? 0 : allUsers.size());
		if (LOGGER.isDebugEnabled() && allUsers != null) {
			LOGGER.debug("List of all found users:");
			allUsers.stream().forEach(eachUser -> LOGGER.info("User with id: {} is: {}", eachUser.getId(), eachUser));
		}
		return Optional.ofNullable(allUsers);
	}

	@Override
	public Optional<User> searchUserByEmail(String email) {
		LOGGER.info("Searching for user with email id: {}", email);
		return this.searchForSingleUserMatch(user -> user.getEmail().equals(email), SearchCriteria.EMAIL);
	}

	@Override
	public Optional<User> searchUserByStudentEmail(String email) {
		LOGGER.info("Searching fpr user with student email id: {}", email);
		return this.searchForSingleUserMatch(user -> user.getStudentEmail().equals(email), SearchCriteria.STUDENT_EMAIL);
	}

	@Override
	public Optional<List<User>> searchUsersByCourse(Course course) {
		LOGGER.info("Searching for all the users that took the course {}", course.toString());
		return this.searchForMultipleUserMatch(user -> user.getCourseSelected().equals(course), SearchCriteria.COURSE);
	}

	@Override
	public Optional<List<User>> searchUsersByYearOfAdmission(int yearOfAdmission) {
		LOGGER.info("Searching for users who got admission in the year: {}", yearOfAdmission);
		return this.searchForMultipleUserMatch(user -> user.getYearOfAdmission() == yearOfAdmission, SearchCriteria.YEAR_OF_ADMISSION);
	}

	@Override
	public Optional<User> searchUserByRegisterationNumber(String registrationNumber) {
		LOGGER.info("Searching for user with university roll number: {}", registrationNumber);
		return this.searchForSingleUserMatch(user -> user.getUniversityRegistrationNumber().equalsIgnoreCase(registrationNumber), SearchCriteria.REGISTRATION_NUMBER);
	}

	@Transactional
	@Override
	public User createNewUser(RegistrationFormModel registrationFormModel) {
		LOGGER.info("Registering a new user: {}", registrationFormModel);
		User user = new User ();
		user.setFullName(registrationFormModel.getFullName());
		user.setEmail(registrationFormModel.getEmail());
		user.setStudentEmail(registrationFormModel.getStudentEmail());
		user.setUniversityRegistrationNumber(registrationFormModel.getRegistrationNumber());
		user.setYearOfAdmission(registrationFormModel.getYearOfAdmission());
		user.setCourseSelected(registrationFormModel.getCourseSelected());
		LOGGER.info("Creating user permissions");
		this.mUserAccountInformationService.createNewUserAccountInformation(registrationFormModel, user);
		LOGGER.info("Creating user account");
		user = this.mUserRepository.save(user);
		return user;
	}
	
	/**
	 * Given the predicate, find a single first user that matches the given {@link SearchCriteria}.
	 * @param predicate
	 * @param searchCriteria
	 * @return
	 */
	private Optional<User> searchForSingleUserMatch (Predicate<User> predicate, SearchCriteria searchCriteria) {
		List<User> allUsers = this.mUserRepository.findAll();
		Optional<User> usersMatchingSearchCriteria = allUsers.stream()
				.filter(predicate)
				.findFirst();
		if (usersMatchingSearchCriteria.isPresent()) {
			LOGGER.info("User with {} was found: {} was found.", searchCriteria.toString(), usersMatchingSearchCriteria.get());
		} else {
			LOGGER.info("No user with {} was found", searchCriteria.toString());
		}
		return usersMatchingSearchCriteria;
	}
	
	/**
	 * Given the predicate, find all the users matching the {@link SearchCriteria}.
	 * @param predicate
	 * @param searchCriteria
	 * @return
	 */
	private Optional<List<User>> searchForMultipleUserMatch (Predicate<User> predicate, SearchCriteria searchCriteria) {
		List<User> allUsers = this.mUserRepository.findAll();
		List<User> usersMatchingSearchCriteria = allUsers.stream()
				.filter(predicate)
				.collect(Collectors.toList());
		if (usersMatchingSearchCriteria != null) {
			LOGGER.info("Number of users found matching {} is {}", searchCriteria.toString(), usersMatchingSearchCriteria.size());
		} else {
			LOGGER.info("No user matching {} was found.", searchCriteria.toString());
		}
		return Optional.ofNullable(usersMatchingSearchCriteria);
	}
	
	private enum SearchCriteria {
		
		EMAIL ("Email"), 
		STUDENT_EMAIL ("StudentEmail"), 
		REGISTRATION_NUMBER ("RegsitrationNumber"), 
		YEAR_OF_ADMISSION ("YearOfAdmission"), 
		COURSE ("Course");
		
		private String value;
		
		private SearchCriteria (String value) { this.value = value; }
		
		@Override public String toString () { return this.value; }
	}

}
