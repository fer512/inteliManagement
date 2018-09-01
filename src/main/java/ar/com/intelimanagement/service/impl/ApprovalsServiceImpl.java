package ar.com.intelimanagement.service.impl;

import ar.com.intelimanagement.service.ApprovalsService;
import ar.com.intelimanagement.domain.Approvals;
import ar.com.intelimanagement.repository.ApprovalsRepository;
import ar.com.intelimanagement.service.dto.ApprovalsDTO;
import ar.com.intelimanagement.service.mapper.ApprovalsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;
/**
 * Service Implementation for managing Approvals.
 */
@Service
@Transactional
public class ApprovalsServiceImpl implements ApprovalsService {

    private final Logger log = LoggerFactory.getLogger(ApprovalsServiceImpl.class);

    private final ApprovalsRepository approvalsRepository;

    private final ApprovalsMapper approvalsMapper;

    public ApprovalsServiceImpl(ApprovalsRepository approvalsRepository, ApprovalsMapper approvalsMapper) {
        this.approvalsRepository = approvalsRepository;
        this.approvalsMapper = approvalsMapper;
    }

    /**
     * Save a approvals.
     *
     * @param approvalsDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ApprovalsDTO save(ApprovalsDTO approvalsDTO) {
        log.debug("Request to save Approvals : {}", approvalsDTO);
        Approvals approvals = approvalsMapper.toEntity(approvalsDTO);
        approvals = approvalsRepository.save(approvals);
        return approvalsMapper.toDto(approvals);
    }

    /**
     * Get all the approvals.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ApprovalsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Approvals");
        return approvalsRepository.findAll(pageable)
            .map(approvalsMapper::toDto);
    }


    /**
     * Get one approvals by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ApprovalsDTO> findOne(Long id) {
        log.debug("Request to get Approvals : {}", id);
        return approvalsRepository.findById(id)
            .map(approvalsMapper::toDto);
    }

    /**
     * Delete the approvals by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Approvals : {}", id);
        approvalsRepository.deleteById(id);
    }
}
