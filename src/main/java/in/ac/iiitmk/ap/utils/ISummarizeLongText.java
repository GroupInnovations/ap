package in.ac.iiitmk.ap.utils;

/**
 * A long text as a news article or a blog should be summarized and shown to the user.
 * @author Prashant Chaturvedi
 *
 */
public interface ISummarizeLongText {
	
	public String summarize (String longText);
	
	public String summarize (byte[] longText);
	
}
