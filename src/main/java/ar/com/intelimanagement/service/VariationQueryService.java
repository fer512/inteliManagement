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
import ar.com.intelimanagement.domain.*; // for static metamodels
import ar.com.intelimanagement.repository.VariationRepository;
import ar.com.intelimanagement.service.dto.VariationCriteria;

import ar.com.intelimanagement.service.dto.VariationDTO;
import ar.com.intelimanagement.service.dto.VariationFullDTO;
import ar.com.intelimanagement.service.dto.VariationListDTO;
import ar.com.intelimanagement.service.mapper.VariationMapper;

/**
 * Service for executing complex queries for Variation entities in the database.
 * The main input is a {@link VariationCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link VariationDTO} or a {@link Page} of {@link VariationDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class VariationQueryService extends QueryService<Variation> {

    private final Logger log = LoggerFactory.getLogger(VariationQueryService.class);

    private final VariationRepository variationRepository;

    private final VariationMapper variationMapper;

    public VariationQueryService(VariationRepository variationRepository, VariationMapper variationMapper) {
        this.variationRepository = variationRepository;
        this.variationMapper = variationMapper;
    }

    /**
     * Return a {@link List} of {@link VariationDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<VariationDTO> findByCriteria(VariationCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Variation> specification = createSpecification(criteria);
        return variationMapper.toDto(variationRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link VariationDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<VariationListDTO> findByCriteria(VariationCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Variation> specification = createSpecification(criteria);
        return variationRepository.findAll(specification, page)
            .map(variationMapper::toDtoPending);
    }

    /**
     * Function to convert VariationCriteria to a {@link Specification}
     */
    private Specification<Variation> createSpecification(VariationCriteria criteria) {
        Specification<Variation> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Variation_.id));
            }
            if (criteria.getExtraCharge() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getExtraCharge(), Variation_.extraCharge));
            }
            if (criteria.getNewCharge() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNewCharge(), Variation_.newCharge));
            }
            if (criteria.getNewCost() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNewCost(), Variation_.newCost));
            }
            if (criteria.getNewBenefit() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNewBenefit(), Variation_.newBenefit));
            }
            if (criteria.getNewExternalLocatorId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNewExternalLocatorId(), Variation_.newExternalLocatorId));
            }
            if (criteria.getComments() != null) {
                specification = specification.and(buildStringSpecification(criteria.getComments(), Variation_.comments));
            }
            if (criteria.getCreationDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreationDate(), Variation_.creationDate));
            }
            if (criteria.getCreationUser() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getCreationUser(), Variation_.creationUser, User_.id));
            }

            if (criteria.getProduct() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getProduct(), Variation_.product, ProductByBooking_.id));
            }
            if (criteria.getArea() != null) {
                specification = specification.and(buildStringSpecification(criteria.getArea(), Variation_.area));
            }
            if (criteria.getCampaing() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCampaing(), Variation_.campaing));
            }
            if (criteria.getReason() != null) {
                specification = specification.and(buildStringSpecification(criteria.getReason(), Variation_.reason));
            }
            if (criteria.getRecoverable() != null) {
                specification = specification.and(buildSpecification(criteria.getRecoverable(), Variation_.recoverable));
            }
       
            if (criteria.getCacel() != null) {
                specification = specification.and(buildSpecification(criteria.getCacel(), Variation_.cacel));
            }
           
        }
        return specification;
    }

}
