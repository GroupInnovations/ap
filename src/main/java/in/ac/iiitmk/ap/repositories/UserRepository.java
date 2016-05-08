package in.ac.iiitmk.ap.repositories;

import org.springframework.data.jpa.repository.*;

import in.ac.iiitmk.ap.models.*;

/**
 * 
 * @author Prashant Chaturvedi
 *
 */
public interface UserRepository extends JpaRepository<User, Long> {}
