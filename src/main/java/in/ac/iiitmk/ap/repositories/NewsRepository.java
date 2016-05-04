package in.ac.iiitmk.ap.repositories;

import org.springframework.data.jpa.repository.*;

import in.ac.iiitmk.ap.models.*;

/**
 * This interface directly accesses the database table for News.
 * @author Prashant Chaturvedi
 *
 */
public interface NewsRepository extends JpaRepository<News, Long> {}
