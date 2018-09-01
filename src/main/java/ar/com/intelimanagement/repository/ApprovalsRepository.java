package ar.com.intelimanagement.repository;

import ar.com.intelimanagement.domain.Approvals;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Approvals entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ApprovalsRepository extends JpaRepository<Approvals, Long>, JpaSpecificationExecutor<Approvals> {

}
