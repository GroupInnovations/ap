package in.ac.iiitmk.ap.validators;

import org.apache.commons.validator.routines.*;
import org.slf4j.*;
import org.springframework.stereotype.*;
import org.springframework.validation.*;

import in.ac.iiitmk.ap.models.web.*;

/**
 * Validator class for {@link RegistrationFormModel}
 * @author Prashant Chaturvedi
 *
 */
@Component
public class RegistrationFormModelValidator implements Validator {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationFormModelValidator.class);

	@Override
	public boolean supports(Class<?> klass) {
		return klass.equals(RegistrationFormModel.class);
	}

	@Override
	public void validate(Object validatableRegistrationFormModel, Errors errors) {
		RegistrationFormModel modelToValidate = (RegistrationFormModel) validatableRegistrationFormModel;
		
		this.validateFullName(modelToValidate.getFullName(), errors);
		this.validateEmailId(modelToValidate.getEmail(), errors, EmailType.PERSONAL);
		this.validateEmailId(modelToValidate.getStudentEmail(), errors, EmailType.STUDENT);
		this.validatePassword(modelToValidate.getPassword(), modelToValidate.getRepeatedPassword(), errors);
		this.validateYearOfAdmission(modelToValidate.getYearOfAdmission(), errors);
	}
	
	/**
	 * No Special character in the name except '.'. '.' should not be the first character of the name or any part of
	 * the name. Spaces are allowed. Every space should be followed by [A-Za-z].
	 * @param fullName
	 * @param errors
	 */
	private void validateFullName (String fullName, Errors errors) {
		
	}
	
	private void validateEmailId (String email, Errors error, EmailType type) {
		if (type == EmailType.STUDENT) {
			email = email + "@iiitmk.ac.in";
		}
		boolean isEmailValid = EmailValidator.getInstance().isValid(email);
		LOGGER.info ("Email id: {} is {}", email, isEmailValid ? "valid" : "not valid");
	}
	
	private void validatePassword (String password, String repeatedPassword, Errors errors) {}
	
	private void validateYearOfAdmission (int yearOfAdmission, Errors errors) {}
	
	/**
	 * We do not require two email validators for validating different email id types
	 * @author pcnicky
	 *
	 */
	private enum EmailType {PERSONAL, STUDENT}
	
}
