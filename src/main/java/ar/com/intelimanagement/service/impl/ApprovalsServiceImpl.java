package ar.com.intelimanagement.service.impl;

import ar.com.intelimanagement.service.ApprovalsService;
import ar.com.intelimanagement.service.NotificationService;
import ar.com.intelimanagement.service.UserService;
import ar.com.intelimanagement.domain.Approvals;
import ar.com.intelimanagement.domain.User;
import ar.com.intelimanagement.repository.ApprovalsRepository;
import ar.com.intelimanagement.service.dto.ApprovalsDTO;
import ar.com.intelimanagement.service.dto.CanApproveRejectedDTO;
import ar.com.intelimanagement.service.dto.UserDTO;
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

    private final UserService userService;
    
    private final ApprovalsMapper approvalsMapper;

    
    public ApprovalsServiceImpl(ApprovalsRepository approvalsRepository, ApprovalsMapper approvalsMapper,UserService userService) {
        this.approvalsRepository = approvalsRepository;
        this.approvalsMapper = approvalsMapper;
        this.userService = userService;
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
        Optional<User> userLogin = userService.getUserWithAuthorities();
        approvals.setCreationUser(userLogin.get());
        approvals = approvalsRepository.save(approvals);
        return approvalsMapper.toDto(approvals);
    }

    @Override
    public Approvals save(Approvals approvals) {
        log.debug("Request to save Approvals : {}", approvals);
        Approvals newA = approvalsRepository.save(approvals);
        return newA;
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

	@Override
	@Transactional
	public Approvals approve(Long id) throws Exception {
		Optional<User> currentUser = this.userService.getUserWithAuthorities();
		Optional<Approvals> approvals = approvalsRepository.findById(id);
		return approvals.get().approve(currentUser.get());
	}

	@Override
	@Transactional
	public CanApproveRejectedDTO canApproveRejected(Approvals approval) {
		CanApproveRejectedDTO dto = new CanApproveRejectedDTO();
		Optional<User> currentUser = this.userService.getUserWithAuthorities();
		Boolean a = approval.canApprove(currentUser.get());
		Boolean r = approval.canRejected(currentUser.get());
		dto.setApprove(a);
		dto.setRejected(r);
		return dto;
	}
	
	@Override
	@Transactional
	public Approvals rejected(Long id) throws Exception {
		Optional<User> currentUser = this.userService.getUserWithAuthorities();
		Optional<Approvals> approvals = approvalsRepository.findById(id);
		return approvals.get().rejected(currentUser.get());
	}
}
