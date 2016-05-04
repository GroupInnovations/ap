package in.ac.iiitmk.ap.utils;

import org.springframework.stereotype.*;

/**
 * This class summarizes long text to a shorter version. In this implementation we take first N words to create summary.
 * @author pcnicky
 *
 */
@Component
public class SimpleLongTextSummarizer implements ISummarizeLongText {
	
	private static final int MAX_WORDS = 30;

	@Override
	public String summarize(String longText) {
		StringBuffer buffer = new StringBuffer();
		String[] words = longText.split(" ");
		for (int i = 0; i < MAX_WORDS; ++i) {
			buffer.append(words[i]);
			buffer.append(" ");
		}
		return buffer.toString();
	}

	@Override
	public String summarize(byte[] longText) {
		String longTextString = new String (longText);
		return summarize(longTextString);
	}
}
