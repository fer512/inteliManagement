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

import ar.com.intelimanagement.domain.Provider;
import ar.com.intelimanagement.domain.*; // for static metamodels
import ar.com.intelimanagement.repository.ProviderRepository;
import ar.com.intelimanagement.service.dto.ProviderCriteria;

import ar.com.intelimanagement.service.dto.ProviderDTO;
import ar.com.intelimanagement.service.mapper.ProviderMapper;

/**
 * Service for executing complex queries for Provider entities in the database.
 * The main input is a {@link ProviderCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ProviderDTO} or a {@link Page} of {@link ProviderDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ProviderQueryService extends QueryService<Provider> {

    private final Logger log = LoggerFactory.getLogger(ProviderQueryService.class);

    private final ProviderRepository providerRepository;

    private final ProviderMapper providerMapper;

    public ProviderQueryService(ProviderRepository providerRepository, ProviderMapper providerMapper) {
        this.providerRepository = providerRepository;
        this.providerMapper = providerMapper;
    }

    /**
     * Return a {@link List} of {@link ProviderDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ProviderDTO> findByCriteria(ProviderCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Provider> specification = createSpecification(criteria);
        return providerMapper.toDto(providerRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ProviderDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ProviderDTO> findByCriteria(ProviderCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Provider> specification = createSpecification(criteria);
        return providerRepository.findAll(specification, page)
            .map(providerMapper::toDto);
    }

    /**
     * Function to convert ProviderCriteria to a {@link Specification}
     */
    private Specification<Provider> createSpecification(ProviderCriteria criteria) {
        Specification<Provider> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Provider_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), Provider_.name));
            }
            if (criteria.getCompanyId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getCompanyId(), Provider_.company, Company_.id));
            }
        }
        return specification;
    }

}
