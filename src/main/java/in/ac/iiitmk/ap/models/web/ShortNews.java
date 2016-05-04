package in.ac.iiitmk.ap.models.web;

import java.text.*;
import java.time.*;

/**
 * This model represents the news that is displayed on the home page of the application. It also has information
 * about the location of the actual long form news article.
 * @author Prashant Chaturvedi
 *
 */
public class ShortNews {
	
	private String title;
	
	private String summary;
	
	private Long id;
	
	private LocalDateTime createdOn;
	
	private String linkToActualArticle;
	
	public ShortNews (Long id, String title, String summary, LocalDateTime createdOn) {
		this.id = id;
		this.title = title;
		this.summary = summary;
		this.createdOn = createdOn;
		
		this.linkToActualArticle = this.generatedLinkForActualArticle(this.id);
	}
	
	public String getTitle() {
		return title;
	}

	public String getSummary() {
		return summary;
	}

	public Long getId() {
		return id;
	}

	public LocalDateTime getCreatedOn() {
		return createdOn;
	}

	public String getLinkToActualArticle() {
		return linkToActualArticle;
	}
	
	@Override
	public String toString () {
		return MessageFormat.format("Id: {0}; Title: {1}; Summary: {2}; CreatedOn: {3}", id, title, summary, createdOn);
	}

	private String generatedLinkForActualArticle (Long id) {
		return null;
	}
}
