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

import ar.com.intelimanagement.domain.Variation;
import ar.com.intelimanagement.domain.*; // for static metamodels
import ar.com.intelimanagement.repository.VariationRepository;
import ar.com.intelimanagement.service.dto.VariationCriteria;

import ar.com.intelimanagement.service.dto.VariationDTO;
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
    public Page<VariationDTO> findByCriteria(VariationCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Variation> specification = createSpecification(criteria);
        return variationRepository.findAll(specification, page)
            .map(variationMapper::toDto);
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
            if (criteria.getExtra_charge() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getExtra_charge(), Variation_.extra_charge));
            }
            if (criteria.getNew_charge() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNew_charge(), Variation_.new_charge));
            }
            if (criteria.getNew_cost() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNew_cost(), Variation_.new_cost));
            }
            if (criteria.getNew_benefit() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNew_benefit(), Variation_.new_benefit));
            }
            if (criteria.getNew_external_locator_id() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNew_external_locator_id(), Variation_.new_external_locator_id));
            }
            if (criteria.getComments() != null) {
                specification = specification.and(buildStringSpecification(criteria.getComments(), Variation_.comments));
            }
            if (criteria.getCreation_date() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreation_date(), Variation_.creation_date));
            }
            if (criteria.getCreation_user() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreation_user(), Variation_.creation_user));
            }
            if (criteria.getProvider() != null) {
                specification = specification.and(buildStringSpecification(criteria.getProvider(), Variation_.provider));
            }
            if (criteria.getProduct() != null) {
                specification = specification.and(buildStringSpecification(criteria.getProduct(), Variation_.product));
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
            if (criteria.getRefund_in_points() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRefund_in_points(), Variation_.refund_in_points));
            }
            if (criteria.getRefund_in_cash() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRefund_in_cash(), Variation_.refund_in_cash));
            }
            if (criteria.getCacel() != null) {
                specification = specification.and(buildSpecification(criteria.getCacel(), Variation_.cacel));
            }
            if (criteria.getRelationship_user_variationId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getRelationship_user_variationId(), Variation_.relationship_user_variation, User_.id));
            }
            if (criteria.getRelationship_provider_variationId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getRelationship_provider_variationId(), Variation_.relationship_provider_variations, Provider_.id));
            }
            if (criteria.getRelationship_product_variationId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getRelationship_product_variationId(), Variation_.relationship_product_variations, Product_.id));
            }
        }
        return specification;
    }

}
