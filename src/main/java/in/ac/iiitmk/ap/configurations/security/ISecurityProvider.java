package in.ac.iiitmk.ap.configurations.security;

import org.springframework.security.config.annotation.authentication.builders.*;

import in.ac.iiitmk.ap.configurations.*;

/**
 * This interface should be implemented by the different Security Providers. The security providers
 * have one task at hand - to build {@link AuthenticationManagerBuilder}. One of these beans would be injected
 * in the {@link WebSecurity} class for providing security.
 * 
 * @author PrashantCahturvedi
 *
 */
@FunctionalInterface
public interface ISecurityProvider {
	/**
	 * The implementor of this interface should implement this method to build {@link AuthenticationManagerBuilder}
	 * @param builder
	 */
	void build (AuthenticationManagerBuilder builder) throws Exception;
}
