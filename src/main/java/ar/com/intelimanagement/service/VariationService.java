package ar.com.intelimanagement.service;

import ar.com.intelimanagement.domain.Variation;
import ar.com.intelimanagement.repository.VariationRepository;
import ar.com.intelimanagement.service.dto.VariationDTO;
import ar.com.intelimanagement.service.mapper.VariationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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

    public VariationService(VariationRepository variationRepository, VariationMapper variationMapper) {
        this.variationRepository = variationRepository;
        this.variationMapper = variationMapper;
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
}
