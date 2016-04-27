package in.ac.iiitmk.ap.configurations;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.web.method.support.*;
import org.springframework.web.servlet.config.annotation.*;

import in.ac.iiitmk.ap.controllers.handlers.*;

/**
 * Web application configuration.
 * @author Prashant Chaturvedi
 *
 */
@Configuration
public class WebConfiguration extends WebMvcConfigurerAdapter {
	
	private static final String RESOURCE_HANDLER_PATTERN = "/public/**";
	private static final String RESOURCE_HANDLER_URL = "classpath:public/";
	
	private WebControllerMethodReturnTypeHandler mWebControllerMethodReturnTypeHandler;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler(RESOURCE_HANDLER_PATTERN).addResourceLocations(RESOURCE_HANDLER_URL);
	}
	
	/**
	 * Adds custom {@link Page} type as a view resolver in any controller of this application.
	 */
	@Override
	public void addReturnValueHandlers (List<HandlerMethodReturnValueHandler> returnValueHandlers) {
		super.addReturnValueHandlers(returnValueHandlers);
		returnValueHandlers.add(this.mWebControllerMethodReturnTypeHandler); 
	}
	
	@Autowired
	public void setWebControllerMethodReturnTypeHandler (WebControllerMethodReturnTypeHandler webControllerMethodReturnTypeHandler) {
		this.mWebControllerMethodReturnTypeHandler = webControllerMethodReturnTypeHandler;
	}
}
