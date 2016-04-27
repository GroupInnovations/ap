package in.ac.iiitmk.ap.configurations.security;

import org.springframework.beans.factory.annotation.*;
import org.springframework.security.config.annotation.authentication.builders.*;
import org.springframework.stereotype.*;

/**
 * Provides an implementation of {@link ISecurityProvider}. This class builds {@link AuthenticationManagerBuilder}
 * to provide in-memory authentication. By default, this class uses a default username of user and a password of password.
 * This class should not be used in production.
 * @author Prashant Chaturvedi
 *
 */
@Component
@Qualifier ("InMemoryAuthenticationManagerBuilder")
public class InMemoryAuthenticationManagerBuilder implements ISecurityProvider {
	
	private static final String DEFAULT_USERNAME = "user";
	private static final String DEFAULT_PASSWORD = "password";
	private static final String DEFAULT_ROLE = "ALUMINI";
	
	@Override
	public void build (AuthenticationManagerBuilder builder) throws Exception {
		builder
			.inMemoryAuthentication()
				.withUser(DEFAULT_USERNAME).password(DEFAULT_PASSWORD).roles(DEFAULT_ROLE);
	}
}
