package in.ac.iiitmk.ap.services;

import java.util.*;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import in.ac.iiitmk.ap.models.*;
import in.ac.iiitmk.ap.models.web.*;
import in.ac.iiitmk.ap.repositories.*;
import in.ac.iiitmk.ap.services.api.*;

/**
 * Implementation of {@link UserAccountInformationService}.
 * 
 * @author Prashant Chaturvedi
 *
 */
@Service
public class SimpleUserAccountInformationService implements UserAccountInformationService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SimpleUserAccountInformationService.class);
	
	private UserAccountInformationRepository mUserAccountInformationRepository;
	
	@Autowired
	public SimpleUserAccountInformationService (UserAccountInformationRepository userAccountInformationRepository) {
		this.mUserAccountInformationRepository = userAccountInformationRepository;
	}

	@Override
	public Optional<UserAccountInformation> getUserAccountInformationById(Long id) {
		UserAccountInformation userAccountInformation = this.mUserAccountInformationRepository.findOne(id);
		if (userAccountInformation == null) {
			LOGGER.info("UserAccountInformation for id: {} was not found.", id);
		} else {
			LOGGER.info("UserAccountInformation for id: {} was found to be: {}", id, userAccountInformation);
		}
		return Optional.ofNullable(userAccountInformation);
	}

	@Override
	public Optional<User> getUserFromUserAccountInformation(Long id) {
		Optional<UserAccountInformation> optionalUserAccountInformation = this.getUserAccountInformationById(id);
		if (optionalUserAccountInformation.isPresent()) {
			UserAccountInformation userAccountInformation = optionalUserAccountInformation.get();
			LOGGER.info("UserAccountInformation for id: {} was found to be: {}", id, userAccountInformation);
			LOGGER.info("User information corresponding to the UAI_ID: {} was found to be: {}", id, userAccountInformation.getUser());
			return Optional.of(userAccountInformation.getUser());
		}
		LOGGER.info("UserAccountInformation for id: {} was not found.", id);
		return Optional.empty();
	}

	@Override
	public List<UserAccountInformation> getAllUserAccountInformation () {
		LOGGER.info("Retriving all user account information from database.");
		List<UserAccountInformation> allUserAccountInformation = this.mUserAccountInformationRepository.findAll();
		if (LOGGER.isDebugEnabled()) {
			allUserAccountInformation.stream().forEach(userAccountInformation -> LOGGER.info("Found record for id: {}", userAccountInformation.getId()));
		}
		return allUserAccountInformation;
	}

	/**
	 * The {@link RegistrationFormModel} is used to created the object of {@link User} which would register a user with the application.
	 * Only when the user has been registered, it is given the permissions which is done by calling this method.
	 * @param registrationFormModel
	 * @param user
	 * @return
	 */
	@Override
	@Transactional
	public UserAccountInformation createNewUserAccountInformation(RegistrationFormModel registrationFormModel, User user) {
		UserAccountInformation userAccountInformation = new UserAccountInformation();
		userAccountInformation.setUserId(registrationFormModel.getStudentEmail());
		userAccountInformation.setPassword(registrationFormModel.getPassword());
		userAccountInformation.setUser(user);
		userAccountInformation = this.mUserAccountInformationRepository.save(userAccountInformation);
		LOGGER.info("Created UserAccountInformationEntity with id: {}", userAccountInformation.getId());
		return userAccountInformation;
	}

}
