package in.ac.iiitmk.ap.services.api;

import java.util.*;

import in.ac.iiitmk.ap.models.*;

/**
 * Provides an API for accessing NEWS table
 * @author Prashant Chaturvedi
 *
 */
public interface NewsService {
	/**
	 * Returns the {@link News} item by id.
	 * @param id
	 * @return
	 */
	Optional<News> getNewsById (Long id);
	
	/**
	 * Returns the list of all the news items in the table.
	 * @return
	 */
	Optional<List<News>> getAllNews ();
	
	/**
	 * Returns a list of all {@link News} sorted by date created.
	 * @return
	 */
	Optional<List<News>> getAllNewsSortedByCreationDate ();
	
	/**
	 * Returns the list of top n {@link News} items sorted by their date of creation
	 * @param count
	 * @return
	 */
	Optional<List<News>> getTopNNewsSortedByCreationDate (int count);
	
	/**
	 * Creates a new {@link News} item in the database table.
	 * @param newNews
	 * @return
	 */
	News create (News newNews);
}
