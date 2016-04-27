package in.ac.iiitmk.ap.controllers.handlers;

import java.io.*;
import java.nio.file.*;
import java.text.*;

/**
 * This enum tries to group the list of pages at one place along with their associated URLs.
 * This approach also reduces magic numbers in the code. This conversion of code is taken care of by
 * {@link WebControllerMethodReturnTypeHandler}
 * 
 * @author Prashant Chaturvedi
 *
 */
public enum Page {
	
	// Public page links defined here
	PAGE_PUBLIC_HOME ("home", Page.PUBLIC_DIR),
	
	// Alumini page links defined here
	PAGE_ALUMINI_HOME ("home", Page.ALUMINI_DIR);
	
	private static final String PUBLIC_DIR = "public";
	private static final String ALUMINI_DIR = "alumini";
	
	private static final String URL_PATTERN = "{0}{1}{2}";
	
	private static final String FORWARD = "forward:";
	private static final String REDIRECT = "redirect:";
	
	private String pageId;
	// Represents the directory in which the file is wrt the prefix of the path. 
	private String directory;
	
	private Page (String pageId) { this.pageId = pageId; }
	
	private Page (String pageId, String directory) {
		this.pageId = pageId;
		this.directory = directory;
	}
	
	public String forward () { return FORWARD + this.toString(); }
	
	public String redirect () { return REDIRECT + this.toString(); }
	
	@Override 
	public String toString () { 
		// String representation of the Path. Java standardises the path to remove the trailing File.separator
		return this.directory == null ? this.pageId : MessageFormat.format(URL_PATTERN, Paths.get(directory).toString(), File.separator, this.pageId);
	}
}