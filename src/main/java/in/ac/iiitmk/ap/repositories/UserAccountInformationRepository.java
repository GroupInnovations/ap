package in.ac.iiitmk.ap.repositories;

import org.springframework.data.jpa.repository.*;

import in.ac.iiitmk.ap.models.*;

/**
 * Spring Data JpaRepository for UAI_TABLE
 * 
 * @author Prashant Chaturvedi
 *
 */
public interface UserAccountInformationRepository extends JpaRepository<UserAccountInformation, Long> {}
