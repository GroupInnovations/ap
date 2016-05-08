package in.ac.iiitmk.ap.services.api;

import java.util.*;

import in.ac.iiitmk.ap.models.*;
import in.ac.iiitmk.ap.models.web.*;

/**
 * 
 * @author Prashant Chaturvedi
 *
 */
public interface UserAccountInformationService {
	/**
	 * Given id, returns instance of {@link UserAccountInformation}
	 * @param id
	 * @return Optional of {@link UserAccountInformation} if id is found
	 * 			{@link Optional#empty()} otherwise
	 */
	Optional<UserAccountInformation> getUserAccountInformationById (Long id);
	
	/**
	 * Given id, get the {@link User} instance whose account information is stored in the given id
	 * @param id
	 * @return Optional of {@link User} if the id is found
	 * 			{@link Optional#empty()} otherwise
	 */
	Optional<User> getUserFromUserAccountInformation (Long id);
	
	/**
	 * List all the {@link UserAccountInformation}
	 * @return
	 */
	List<UserAccountInformation> getAllUserAccountInformation ();
	
	/**
	 * Creates a new {@link UserAccountInformation} from {@link RegistrationFormModel}
	 * @param user
	 * @return Entity representing saved {@link UserAccountInformation}
	 */
	UserAccountInformation createNewUserAccountInformation (RegistrationFormModel registrationFormModel, User user);
}
