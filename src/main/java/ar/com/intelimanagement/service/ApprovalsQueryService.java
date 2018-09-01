package ar.com.intelimanagement.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import ar.com.intelimanagement.domain.Approvals;
import ar.com.intelimanagement.domain.*; // for static metamodels
import ar.com.intelimanagement.repository.ApprovalsRepository;
import ar.com.intelimanagement.service.dto.ApprovalsCriteria;

import ar.com.intelimanagement.service.dto.ApprovalsDTO;
import ar.com.intelimanagement.service.mapper.ApprovalsMapper;

/**
 * Service for executing complex queries for Approvals entities in the database.
 * The main input is a {@link ApprovalsCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ApprovalsDTO} or a {@link Page} of {@link ApprovalsDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ApprovalsQueryService extends QueryService<Approvals> {

    private final Logger log = LoggerFactory.getLogger(ApprovalsQueryService.class);

    private final ApprovalsRepository approvalsRepository;

    private final ApprovalsMapper approvalsMapper;

    public ApprovalsQueryService(ApprovalsRepository approvalsRepository, ApprovalsMapper approvalsMapper) {
        this.approvalsRepository = approvalsRepository;
        this.approvalsMapper = approvalsMapper;
    }

    /**
     * Return a {@link List} of {@link ApprovalsDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ApprovalsDTO> findByCriteria(ApprovalsCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Approvals> specification = createSpecification(criteria);
        return approvalsMapper.toDto(approvalsRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ApprovalsDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ApprovalsDTO> findByCriteria(ApprovalsCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Approvals> specification = createSpecification(criteria);
        return approvalsRepository.findAll(specification, page)
            .map(approvalsMapper::toDto);
    }

    /**
     * Function to convert ApprovalsCriteria to a {@link Specification}
     */
    private Specification<Approvals> createSpecification(ApprovalsCriteria criteria) {
        Specification<Approvals> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Approvals_.id));
            }
            if (criteria.getStastDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStastDate(), Approvals_.stastDate));
            }
            if (criteria.getEndDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEndDate(), Approvals_.endDate));
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildSpecification(criteria.getStatus(), Approvals_.status));
            }
        }
        return specification;
    }

}
