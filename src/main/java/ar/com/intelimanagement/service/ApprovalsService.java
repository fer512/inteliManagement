package ar.com.intelimanagement.service;

import ar.com.intelimanagement.domain.Approvals;
import ar.com.intelimanagement.domain.SupervisorApprovals;
import ar.com.intelimanagement.service.dto.ApprovalsDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Approvals.
 */
public interface ApprovalsService {

    /**
     * Save a approvals.
     *
     * @param approvalsDTO the entity to save
     * @return the persisted entity
     */
    ApprovalsDTO save(ApprovalsDTO approvalsDTO);

    /**
     * Get all the approvals.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<ApprovalsDTO> findAll(Pageable pageable);


    /**
     * Get the "id" approvals.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<ApprovalsDTO> findOne(Long id);

    /**
     * Delete the "id" approvals.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

	Approvals approve(Long id) throws Exception;

	Approvals save(Approvals approvals);

	Approvals rejected(Long id) throws Exception;
}
