package ar.com.intelimanagement.service.impl;

import ar.com.intelimanagement.service.PhoneService;
import ar.com.intelimanagement.domain.Phone;
import ar.com.intelimanagement.repository.PhoneRepository;
import ar.com.intelimanagement.service.dto.PhoneDTO;
import ar.com.intelimanagement.service.mapper.PhoneMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/**
 * Service Implementation for managing Phone.
 */
@Service
@Transactional
public class PhoneServiceImpl implements PhoneService {

    private final Logger log = LoggerFactory.getLogger(PhoneServiceImpl.class);

    private final PhoneRepository phoneRepository;

    private final PhoneMapper phoneMapper;

    public PhoneServiceImpl(PhoneRepository phoneRepository, PhoneMapper phoneMapper) {
        this.phoneRepository = phoneRepository;
        this.phoneMapper = phoneMapper;
    }

    /**
     * Save a phone.
     *
     * @param phoneDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public PhoneDTO save(PhoneDTO phoneDTO) {
        log.debug("Request to save Phone : {}", phoneDTO);
        Phone phone = phoneMapper.toEntity(phoneDTO);
        phone = phoneRepository.save(phone);
        return phoneMapper.toDto(phone);
    }

    /**
     * Get all the phones.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<PhoneDTO> findAll() {
        log.debug("Request to get all Phones");
        return phoneRepository.findAll().stream()
            .map(phoneMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one phone by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PhoneDTO> findOne(Long id) {
        log.debug("Request to get Phone : {}", id);
        return phoneRepository.findById(id)
            .map(phoneMapper::toDto);
    }

    /**
     * Delete the phone by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Phone : {}", id);
        phoneRepository.deleteById(id);
    }
}
