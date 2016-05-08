package in.ac.iiitmk.ap.controllers;

import java.util.*;

import javax.validation.*;
import javax.ws.rs.*;

import org.apache.commons.validator.routines.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.validation.*;
import org.springframework.web.bind.*;
import org.springframework.web.bind.annotation.*;

import in.ac.iiitmk.ap.controllers.handlers.*;
import in.ac.iiitmk.ap.models.*;
import in.ac.iiitmk.ap.models.web.*;
import in.ac.iiitmk.ap.services.api.*;
import in.ac.iiitmk.ap.validators.*;

@Controller
public class RegisterController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RegisterController.class);
	
	private static final String REGISTRATION_FORM_MODEL = "registrationFormModel";
	private static final String REGISTRATION_SUCCESS = "registrationSuccess";
	private static final String REGISTRATION_FAILURE = "registrationFailed";
	
	private UserService mUserService;
	
	private RegistrationFormModelValidator mRegistrationFormModelValidator;
	
	@Autowired
	public RegisterController (UserService userService, RegistrationFormModelValidator validator) {
		this.mUserService = userService;
		this.mRegistrationFormModelValidator = validator;
	}
	
	@InitBinder
	public void initBinder (WebDataBinder binder) {
		binder.addValidators(this.mRegistrationFormModelValidator);
	}
	
	/**
	 * Shows registration page only.
	 * @param map
	 * @return
	 */
	@RequestMapping (value = "/register", method = RequestMethod.GET)
	public Page showRegisterPage (ModelMap map) {
		map.addAttribute(REGISTRATION_FORM_MODEL, new RegistrationFormModel());
		return Page.PAGE_PUBLIC_REGISTER;
	}
	
	/**
	 * Actual user registration is done here.
	 * @param registrationFormModel
	 * @param bindingResult
	 * @param modelMap
	 * @return
	 */
	@RequestMapping (value = "/register", method = RequestMethod.POST)
	public Page doRegistrationOfNewUser (@ModelAttribute(REGISTRATION_FORM_MODEL) @Valid RegistrationFormModel registrationFormModel, BindingResult bindingResult, ModelMap modelMap) {
		LOGGER.info("Attempting to register a new user account: {}", registrationFormModel);
		if (bindingResult.hasErrors()) {
			modelMap.addAttribute(REGISTRATION_FAILURE, "There was an error processing your form. Please fill in correct details. If the problem persists, please contact web admin");
			return Page.PAGE_PUBLIC_REGISTER;
		}
		this.mUserService.createNewUser(registrationFormModel);
		registrationFormModel = null;
		modelMap.addAttribute(REGISTRATION_SUCCESS, "Your registration has been successful. An email containing the activation code will be sent to you in a couple of days after your identity has been verified.");
		return Page.PAGE_PUBLIC_REGISTER;
	}
	
	@RequestMapping (value = "/register/validate", method = RequestMethod.POST, params = {"query=isValidEmail"})
	@ResponseBody
	public boolean isValidNewEmailId (@RequestParam("email") String email) {
		LOGGER.info("Attempting to find the validity of email id: {}", email);
		boolean isEmailValid = EmailValidator.getInstance().isValid(email);
		LOGGER.info("Is email valid? {}", isEmailValid? "Yes" : "No");
		if (!isEmailValid) { return false; }
		Optional<User> optionalUserWithMatchingEmail = this.mUserService.searchUserByEmail(email);
		LOGGER.info("Is there an user with the email id: {}? {}", email, optionalUserWithMatchingEmail.isPresent()? "Yes" : "No");
		
		return !optionalUserWithMatchingEmail.isPresent();
	}
	
	@RequestMapping (value = "/register/validate", method = RequestMethod.POST, params = {"query=isValidStudentEmail"})
	@ResponseBody
	public boolean isValidStudentEmail (@RequestParam ("studentEmail") String studentEmail) {
		LOGGER.info("Attempting to fins the validity of Student email id: {}", studentEmail);
		String tempStudentEmail = studentEmail + "@iiitmk.ac.in";
		boolean isEmailValid = EmailValidator.getInstance().isValid(tempStudentEmail);
		LOGGER.info("Is student email id valid? {}", isEmailValid? "Yes" : "No");
		if (!isEmailValid) { return false; }
		Optional<User> optionalUserWithMatchedEmailId = this.mUserService.searchUserByStudentEmail(studentEmail);
		LOGGER.info("Is there an user with the student email id: {}? {}", studentEmail, optionalUserWithMatchedEmailId.isPresent()? "Yes" : "No");
		
		return !optionalUserWithMatchedEmailId.isPresent();
	}
	
	@RequestMapping (value = "/register/validate", method = RequestMethod.POST, params = {"query=isValidRegistrationNumber"})
	@ResponseBody
	public boolean isValidRegisrationNumber (@RequestParam ("registrationNumber") String registrationNumber) {
		Optional<User> optionalUserWithMatchingRegistrationNumber = this.mUserService.searchUserByRegisterationNumber(registrationNumber);
		LOGGER.info("Is thete a user with the registration number: {}? {}", registrationNumber, optionalUserWithMatchingRegistrationNumber.isPresent()? "Yes" : "No");
		return !optionalUserWithMatchingRegistrationNumber.isPresent();
	}
}
