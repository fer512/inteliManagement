package ar.com.intelimanagement.service;

import ar.com.intelimanagement.domain.ApprovalHistory;
import ar.com.intelimanagement.domain.Approvals;
import ar.com.intelimanagement.domain.SupervisorApprovals;
import ar.com.intelimanagement.domain.User;
import ar.com.intelimanagement.domain.Variation;
import ar.com.intelimanagement.domain.enumeration.ApprovalsStatusType;
import ar.com.intelimanagement.repository.VariationRepository;
import ar.com.intelimanagement.service.dto.VariationDTO;
import ar.com.intelimanagement.service.dto.VariationFullDTO;
import ar.com.intelimanagement.service.mapper.VariationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
/**
 * Service Implementation for managing Variation.
 */
@Service
@Transactional
public class VariationService {

    private final Logger log = LoggerFactory.getLogger(VariationService.class);

    private final VariationRepository variationRepository;

    private final VariationMapper variationMapper;

    private final UserService userService;
    
    private final ApprovalsService approvalsService;

    public VariationService(VariationRepository variationRepository, VariationMapper variationMapper,UserService userService,ApprovalsService approvalsService) {
        this.variationRepository = variationRepository;
        this.variationMapper = variationMapper;
        this.userService = userService;
        this.approvalsService = approvalsService;
    }

    /**
     * Save a variation.
     *
     * @param variationDTO the entity to save
     * @return the persisted entity
     */
    public VariationDTO save(VariationDTO variationDTO) {
        log.debug("Request to save Variation : {}", variationDTO);
        Variation variation = variationMapper.toEntity(variationDTO);
        variation = variationRepository.save(variation);
        return variationMapper.toDto(variation);
    }

    public Variation saveOk(VariationDTO variationDTO) {
        log.debug("Request to save Variation : {}", variationDTO);
        Variation variation = variationMapper.toEntity(variationDTO);
        variation = variationRepository.save(variation);
        return variation;
    }
    
    public Variation save(Variation variation) {
        variation = variationRepository.save(variation);
        return variation;
    }
    
    /**
     * Get all the variations.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<VariationDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Variations");
        return variationRepository.findAll(pageable)
            .map(variationMapper::toDto);
    }


    /**
     * Get one variation by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<VariationDTO> findOne(Long id) {
        log.debug("Request to get Variation : {}", id);
        return variationRepository.findById(id)
            .map(variationMapper::toDto);
    }

    /**
     * Delete the variation by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Variation : {}", id);
        variationRepository.deleteById(id);
    }
    
    /**
     * Save a variation.
     *
     * @param variationDTO the entity to save
     * @return the persisted entity
     */
    public Variation create(VariationDTO variationDTO) {   	
    	User u = this.userService.getUserWithAuthorities().get();
    	   	    	
    	SupervisorApprovals ap = new SupervisorApprovals();
    	ap.setApproveLevel(1);
    	ap.setCreationUser(u);
    	ap.setEndDate(null);
    	ap.setStastDate(Instant.now());
    	ap.setStatus(ApprovalsStatusType.CREATE);
    	
    	List<ApprovalHistory> history = new ArrayList<ApprovalHistory>();
    	ApprovalHistory h = new ApprovalHistory();
    	h.setArea("");
    	h.setRole("");
    	h.setStatus(ApprovalsStatusType.CREATE);
    	h.setUser(u);
    	h.setApprovals(ap);
    	history.add(h);
    	
    	ap.setHistory(history);
    	
    	
        if(ap.pointOfNoReturn()){
        	ap.approve(u);
        }
        
    	Approvals approvals = approvalsService.save(ap);
    	
    	variationDTO.setCreationUser(u.getId());
    	variationDTO.setCreationDate(ZonedDateTime.now());
    	variationDTO.setApprovalsId(approvals.getId());
    	Variation v =  this.saveOk(variationDTO);
    	v.setApprovals(approvals);
    	
    	return v;
    }

	public Variation findById(Long id) {
		  log.debug("Request to get Variation : {}", id);
	        return variationRepository.findById(id).get();
	}

	public VariationDTO getDTO(Variation variation) {
		return this.variationMapper.toDto(variation);
	}

	public UserService getUserService() {
		return userService;
	}

	public ApprovalsService getApprovalsService() {
		return approvalsService;
	}

	public Page<VariationFullDTO> getPending(Pageable pageable) {
		  log.debug("Request to get pending Variations");
		  User u = this.userService.getUserWithAuthorities().get();
	        return variationRepository.getPending(pageable,u)
	            .map(variationMapper::toFullDto);
	}

}
