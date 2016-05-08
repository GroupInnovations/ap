package in.ac.iiitmk.ap.services.api;

import java.util.*;

import in.ac.iiitmk.ap.models.*;
import in.ac.iiitmk.ap.models.web.*;

/**
 * 
 * @author Prashant Chaturvedi
 *
 */
public interface UserService {
	
	/**
	 * Returns an {@link User} by the id.
	 * @param id
	 * @return
	 */
	Optional<User> getUserById (Long id);
	
	/**
	 * Returns all the {@link User}
	 * @return
	 */
	Optional<List<User>> getAllUser ();
	
	/**
	 * Given the email id, search the user having that email id.
	 * @param email
	 * @return
	 */
	Optional<User> searchUserByEmail (String email);
	
	/**
	 * Given the student email id, search the user having that email id.
	 * @param email
	 * @return
	 */
	Optional<User> searchUserByStudentEmail (String email);
	
	/**
	 * Given the {@link Course}, find all the students that have taken the {@link Course}.
	 * @param course
	 * @return
	 */
	Optional<List<User>> searchUsersByCourse (Course course);
	
	/**
	 * Given the year of admission, find all the students that joined the institute in that year
	 * @param yearOfAdmission
	 * @return
	 */
	Optional<List<User>> searchUsersByYearOfAdmission (int yearOfAdmission);
	
	/**
	 * Find the user having the given email id.
	 * @param registrationNumber
	 * @return
	 */
	Optional<User> searchUserByRegisterationNumber (String registrationNumber);
	
	/**
	 * Creates a new user.
	 * @param registrationFormModel
	 * @return
	 */
	User createNewUser (RegistrationFormModel registrationFormModel);
}
