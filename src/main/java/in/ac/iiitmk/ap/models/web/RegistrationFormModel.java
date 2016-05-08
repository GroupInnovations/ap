package in.ac.iiitmk.ap.models.web;

import java.text.*;

import javax.validation.constraints.*;

import org.hibernate.validator.constraints.*;

import in.ac.iiitmk.ap.models.*;
import in.ac.iiitmk.ap.validators.*;

/**
 * This is a model that has nothing to do with the domain. It acts as a data provider for User and User account models.
 * This model stores the form data from the registration form and is used by the service layer to create instances of 
 * {@link User} and {@link UserAccountInformation}. A corresponding {@link RegistrationFormModelValidator} acts as a validator
 * for this model.
 * 
 * @author Prashant Chaturvedi
 *
 */
public class RegistrationFormModel {
	
	private String fullName;
	
	private String email;
	
	private String studentEmail;
	
	private String registrationNumber;
	
	private int yearOfAdmission;
	
	private Course courseSelected;
	
	private String password;
	
	private String repeatedPassword;
	
	public RegistrationFormModel () {}

	@NotNull
	@NotEmpty
	@Size (min = 3, message = "Full Name should be of atleast 3 characters")
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@NotNull
	@NotEmpty
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@NotNull
	@NotEmpty
	public String getStudentEmail() {
		return studentEmail;
	}

	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}

	@NotNull
	@NotEmpty
	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public int getYearOfAdmission() {
		return yearOfAdmission;
	}

	public void setYearOfAdmission(int yearOfAdmission) {
		this.yearOfAdmission = yearOfAdmission;
	}

	public Course getCourseSelected() {
		return courseSelected;
	}

	public void setCourseSelected(Course courseSelected) {
		this.courseSelected = courseSelected;
	}

	@NotNull
	@NotEmpty
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@NotNull
	@NotEmpty
	public String getRepeatedPassword() {
		return repeatedPassword;
	}

	public void setRepeatedPassword(String repeatedPassword) {
		this.repeatedPassword = repeatedPassword;
	}
	
	@Override
	public String toString () {
		return MessageFormat.format("Full Name: {0};"
				+ "Email Id: {1};"
				+ "Student Email Id: {2}"
				+ "Registration Number: {3};"
				+ "Course Selected: {4};"
				+ "Year Of Admission: {5}", fullName, email, studentEmail, registrationNumber, courseSelected.toString(), yearOfAdmission);
	}
}
