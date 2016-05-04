package in.ac.iiitmk.ap.models;

import java.text.*;
import java.time.*;

import javax.persistence.*;

/**
 * This data model represents news that is posted on the application's home page.
 * @author Prashant Chaturvedi
 *
 */

@Entity
@Table (name="NEWS")
public class News implements Comparable<News> {
	
	private Long id;
	
	private String title;
	
	private byte[] news;
	
	private LocalDateTime createdOn;
	
	public News () {}

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "NEWS_ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column (name = "NEWS_TITLE", nullable = false)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Lob
	public byte[] getNews() {
		return news;
	}

	public void setNews(byte[] news) {
		this.news = news;
	}

	@Column (name = "NEWS_CREATED_ON", nullable = false)
	public LocalDateTime getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}
	
	@Override
	public int compareTo (News other) {
		return -1 * this.createdOn.compareTo(other.createdOn);
	}
	
	@Override
	public String toString () {
		return MessageFormat.format("Id: {0}; Title: {1}; CreatedOn: {2}", id, title, createdOn);
	}
}
