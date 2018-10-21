package ar.com.intelimanagement.repository;

import ar.com.intelimanagement.domain.User;
import ar.com.intelimanagement.domain.Variation;
import ar.com.intelimanagement.service.dto.VariationDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Variation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VariationRepository extends JpaRepository<Variation, Long>, JpaSpecificationExecutor<Variation>,VariationCustomizedRepository {

}
