package in.ac.iiitmk.ap.models;

import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * This class is used to authenticate users. It does not store user information other thank the user id and password.
 * @author Prashant Chaturvedi
 *
 */
@Entity
@Table (name = "USER_ACCOUNT_INFO")
public class UserAccountInformation {
	
	private long id;
	
	private String userId;
	
	private String password;
	
	private Role role;
	
	private boolean isAccountEnabled;
	
	private boolean isAccountActivated;
	
	private User user;
	
	public UserAccountInformation () {
		isAccountActivated = isAccountEnabled = false;
		role = Role.ROLE_ALUMINI;
	}

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "UAI_ID")
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column (name = "UAI_UID", nullable = false, unique = true)
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column (name = "UAI_PASSWORD", nullable = false)
	@Size (min = 8)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Enumerated
	@Column (name = "UAI_ROLE")
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Column (name = "UAI_IAE")
	public boolean isAccountEnabled() {
		return isAccountEnabled;
	}

	public void setAccountEnabled(boolean isAccountEnabled) {
		this.isAccountEnabled = isAccountEnabled;
	}

	@Column (name = "UAI_IAA")
	public boolean isAccountActivated() {
		return isAccountActivated;
	}

	public void setAccountActivated(boolean isAccountActivated) {
		this.isAccountActivated = isAccountActivated;
	}

	@OneToOne (cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
