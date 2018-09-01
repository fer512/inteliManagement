package ar.com.intelimanagement.service;

import ar.com.intelimanagement.service.dto.PhoneDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Phone.
 */
public interface PhoneService {

    /**
     * Save a phone.
     *
     * @param phoneDTO the entity to save
     * @return the persisted entity
     */
    PhoneDTO save(PhoneDTO phoneDTO);

    /**
     * Get all the phones.
     *
     * @return the list of entities
     */
    List<PhoneDTO> findAll();


    /**
     * Get the "id" phone.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<PhoneDTO> findOne(Long id);

    /**
     * Delete the "id" phone.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
