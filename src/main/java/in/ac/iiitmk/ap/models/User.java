package in.ac.iiitmk.ap.models;

import java.text.*;

import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * User model for storing user details. This is not used for authenticating users. That task is delegated to {@link UserAccountInformation}.
 * @author Prashant Chaturvedi
 *
 */
@Entity
@Table (name = "USER")
public class User {
	
	private static final String IIITMK_EMAIL_ID_EXT = "@iiitmk.ac.in";
	
	private Long id;
	
	private String fullName;
	
	private String email;
	
	private String studentEmail;
	
	private String universityRegistrationNumber;
	
	private int yearOfAdmission;
	
	private Course courseSelected;
	
	public User () {}

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "USER_ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column (name = "USER_FULL_NAME", nullable = false)
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@Column (name = "USER_EMAIL", nullable = false, unique = true)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column (name = "USER_STUDENT_EMAIL", nullable = false, unique = true)
	public String getStudentEmail () {
		return MessageFormat.format("{0}{1}", this.studentEmail, IIITMK_EMAIL_ID_EXT);
	}
	
	public void setStudentEmail (String studentEmail) {
		this.studentEmail = studentEmail;
	}

	@Column (name = "USER_UNIVERSITY_REGISTRATION_NUMBER", nullable = false, unique = true)
	public String getUniversityRegistrationNumber() {
		return universityRegistrationNumber;
	}

	public void setUniversityRegistrationNumber(String universityRegistrationNumber) {
		this.universityRegistrationNumber = universityRegistrationNumber;
	}

	@Column (name = "USER_YEAR_OF_ADMISSION", nullable = false)
	public int getYearOfAdmission() {
		return yearOfAdmission;
	}

	public void setYearOfAdmission(int yearOfAdmission) {
		this.yearOfAdmission = yearOfAdmission;
	}

	@Enumerated
	@Column (name = "USER_COURSE", nullable = false)
	public Course getCourseSelected() {
		return courseSelected;
	}

	public void setCourseSelected(Course courseSelected) {
		this.courseSelected = courseSelected;
	}
}
