package in.ac.iiitmk.ap.configurations;

import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.authentication.builders.*;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.config.annotation.web.configuration.*;

import in.ac.iiitmk.ap.configurations.security.*;

/**
 * Contains configuration for web security. One route that we could have taken was to have
 * {@link WebSecurity#globalSecurityConfigurationBuilder(AuthenticationManagerBuilder)} out of this class and inject it. Since this method 
 * is concerned with what type of datasource we are using, we could then have added one for InMemory, Database and possibly LDAP.
 * @author Prashant Chaturvedi
 *
 */
@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
	// All these URLs would be allowed to bypass SpringSecurity. Be careful when adding an entry. Something like /** could be a disaster.
	private static final String[] PERMIT_ALL_ANT_MATCHERS = {
			// Static resources
			"/public/**",
			
			// Specific URLS that are guarded by controller
			"/", "/home", "/register/**", "/about-us", "/news/**", "/disclaimer", "/privacy-policy",
			
			// Specific URLS that are part of public APIs
			"/api/public/**",
	};
	
	@Autowired
	@Qualifier ("InMemoryAuthenticationManagerBuilder")
	private ISecurityProvider mSecurityProvider;
	
	@Override
	protected void configure (HttpSecurity httpSecurity) throws Exception {
		httpSecurity
			.authorizeRequests()
				.antMatchers(PERMIT_ALL_ANT_MATCHERS).permitAll()
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.and()
			.httpBasic()
				.and()
			// TODO: Enable CSRF. This can only be done after the private APIs start using OAuth2 for authentication.
			.csrf().disable();
	}
	
	@Autowired
	protected void globalSecurityConfigurationBuilder (AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		this.mSecurityProvider.build(authenticationManagerBuilder);
	}
}
