package in.ac.iiitmk.ap.configurations.security;

import org.springframework.beans.factory.annotation.*;
import org.springframework.security.config.annotation.authentication.builders.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.*;

/**
 * Provides an implementation of {@link ISecurityProvider}. This class builds {@link AuthenticationManagerBuilder}
 * to provide database based authentication. We use {@link UserDetailsService} and {@link UserDetails} for authenticating
 * users of the application.
 * @author Prashant Chaturvedi
 *
 */
@Component
@Qualifier ("DatabaseBasedAuthenticationManagerBuilder")
public class DatabaseBasedAuthenticationManagerBuilder implements ISecurityProvider {
	
	@Override
	public void build (AuthenticationManagerBuilder builder) throws Exception {
		
	}
}
