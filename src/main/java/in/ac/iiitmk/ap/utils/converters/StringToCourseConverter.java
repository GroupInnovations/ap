package in.ac.iiitmk.ap.utils.converters;

import java.util.*;

import org.slf4j.*;
import org.springframework.core.convert.converter.*;
import org.springframework.stereotype.*;

import in.ac.iiitmk.ap.models.*;

/**
 * Converts {@link String} to {@link Course} 
 * @author Prashant Chaturvedi
 *
 */
@Component
public class StringToCourseConverter implements Converter<String, Course> {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(StringToCourseConverter.class);
	
	@Override
	public Course convert (String course) {
		Optional<Course> matchingCourseOptional = Course.toCourse(course);
		if (matchingCourseOptional.isPresent()) {
			LOGGER.info("Matching course for the course field found as: {}", matchingCourseOptional.get().toString());
			return matchingCourseOptional.get();
		}
		LOGGER.info("No matching course was found.");
		return null;
	}
}
