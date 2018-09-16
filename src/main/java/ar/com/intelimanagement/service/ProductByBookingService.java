package ar.com.intelimanagement.service;

import ar.com.intelimanagement.domain.ProductByBooking;
import ar.com.intelimanagement.repository.ProductByBookingRepository;
import ar.com.intelimanagement.service.dto.ProductByBookingDTO;
import ar.com.intelimanagement.service.mapper.ProductByBookingMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/**
 * Service Implementation for managing ProductByBooking.
 */
@Service
@Transactional
public class ProductByBookingService {

    private final Logger log = LoggerFactory.getLogger(ProductByBookingService.class);

    private final ProductByBookingRepository productByBookingRepository;

    private final ProductByBookingMapper productByBookingMapper;

    public ProductByBookingService(ProductByBookingRepository productByBookingRepository, ProductByBookingMapper productByBookingMapper) {
        this.productByBookingRepository = productByBookingRepository;
        this.productByBookingMapper = productByBookingMapper;
    }

    /**
     * Save a productByBooking.
     *
     * @param productByBookingDTO the entity to save
     * @return the persisted entity
     */
    public ProductByBookingDTO save(ProductByBookingDTO productByBookingDTO) {
        log.debug("Request to save ProductByBooking : {}", productByBookingDTO);
        ProductByBooking productByBooking = productByBookingMapper.toEntity(productByBookingDTO);
        productByBooking = productByBookingRepository.save(productByBooking);
        return productByBookingMapper.toDto(productByBooking);
    }

    /**
     * Get all the productByBookings.
     *
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public List<ProductByBookingDTO> findAll() {
        log.debug("Request to get all ProductByBookings");
        return productByBookingRepository.findAll().stream()
            .map(productByBookingMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one productByBooking by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Transactional(readOnly = true)
    public Optional<ProductByBookingDTO> findOne(Long id) {
        log.debug("Request to get ProductByBooking : {}", id);
        return productByBookingRepository.findById(id)
            .map(productByBookingMapper::toDto);
    }

    /**
     * Delete the productByBooking by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete ProductByBooking : {}", id);
        productByBookingRepository.deleteById(id);
    }
}
