package in.ac.iiitmk.ap.controllers.handlers;

import org.springframework.core.*;
import org.springframework.stereotype.*;
import org.springframework.web.context.request.*;
import org.springframework.web.method.support.*;

/**
 * Generally speaking, controller methods return string value that represents the view that it maps too. Such 
 * implementations could be difficult to maintain as a change in the mapping would require that all such return 
 * values be changed. In order to avoid this, the view id have been consolidated into an enum called Page. This 
 * class will automatically intercept any such returned values and would provide the string that represents the view.
 * 
 * @author Prashant Chaturvedi
 *
 */
@Component
public class WebControllerMethodReturnTypeHandler implements HandlerMethodReturnValueHandler {
	@Override
	public boolean supportsReturnType(MethodParameter returnType) {
		return Page.class.isAssignableFrom(returnType.getParameterType());
	}

	@Override
	public void handleReturnValue(Object returnedValue, MethodParameter returnedType,
			ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws Exception {

		if (returnedValue == null) {
			return;
		}
		if (returnedValue instanceof Page) {
			// Delegate the call to toString()
			mavContainer.setViewName(((Page) returnedValue).toString());
		} else {
			throw new RuntimeException("Returned type could not be resolved.");
		}
	}
}
