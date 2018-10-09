package ar.com.intelimanagement.service.impl;

import ar.com.intelimanagement.service.BookingService;
import ar.com.intelimanagement.domain.Booking;
import ar.com.intelimanagement.domain.ProductByBooking;
import ar.com.intelimanagement.repository.BookingRepository;
import ar.com.intelimanagement.repository.ProductByBookingRepository;
import ar.com.intelimanagement.service.dto.BookingDTO;
import ar.com.intelimanagement.service.mapper.BookingMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
/**
 * Service Implementation for managing Booking.
 */
@Service
@Transactional
public class BookingServiceImpl implements BookingService {

    private final Logger log = LoggerFactory.getLogger(BookingServiceImpl.class);

    private final BookingRepository bookingRepository;

    private final ProductByBookingRepository productByBookingRepository;
    
    private final BookingMapper bookingMapper;

    public BookingServiceImpl(BookingRepository bookingRepository,  ProductByBookingRepository productByBookingRepository, BookingMapper bookingMapper) {
        this.bookingRepository = bookingRepository;
        this.bookingMapper = bookingMapper;
        this.productByBookingRepository = productByBookingRepository;
    }

    /**
     * Save a booking.
     *
     * @param bookingDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public BookingDTO save(BookingDTO bookingDTO) {
        log.debug("Request to save Booking : {}", bookingDTO);
        Booking booking = bookingMapper.toEntity(bookingDTO);
        Set<ProductByBooking> products = booking.getProducts();
        booking.setProducts(null);
        booking = bookingRepository.save(booking);
        for (ProductByBooking productByBooking : products) {
        	productByBooking.setBooking(booking);
		}
        productByBookingRepository.saveAll(products);
        booking.setProducts(products);
        return bookingMapper.toDto(booking);
    }

    /**
     * Get all the bookings.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<BookingDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Bookings");
        return bookingRepository.findAll(pageable)
            .map(bookingMapper::toDto);
    }


    /**
     * Get one booking by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<BookingDTO> findOne(Long id) {
        log.debug("Request to get Booking : {}", id);
        return bookingRepository.findById(id)
            .map(bookingMapper::toDto);
    }

    /**
     * Delete the booking by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Booking : {}", id);
        bookingRepository.deleteById(id);
    }

	public ProductByBookingRepository getProductByBookingRepository() {
		return productByBookingRepository;
	}
}
