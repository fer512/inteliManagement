package ar.com.intelimanagement.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.intelimanagement.domain.Booking;
import ar.com.intelimanagement.domain.ProductByBooking;
import ar.com.intelimanagement.repository.BookingCustomRepository;
import ar.com.intelimanagement.repository.BookingRepository;
import ar.com.intelimanagement.repository.ProductByBookingRepository;
import ar.com.intelimanagement.service.BookingService;
import ar.com.intelimanagement.service.dto.BookingDTO;
import ar.com.intelimanagement.service.dto.BookingFullDTO;
import ar.com.intelimanagement.service.dto.BookingMinDTO;
import ar.com.intelimanagement.service.mapper.BookingMapper;
import ar.com.intelimanagement.service.mapper.ProductByBookingMapper;
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
    
    private final ProductByBookingMapper productByBookingMapper;

	private BookingCustomRepository bookingCustomRepository;

    public BookingServiceImpl(BookingRepository bookingRepository,  ProductByBookingRepository productByBookingRepository, BookingMapper bookingMapper,ProductByBookingMapper productByBookingMapper,BookingCustomRepository bookingCustomRepository) {
        this.bookingRepository = bookingRepository;
        this.bookingMapper = bookingMapper;
        this.productByBookingRepository = productByBookingRepository;
        this.productByBookingMapper = productByBookingMapper;
        this.bookingCustomRepository = bookingCustomRepository;
    }

    /**
     * Save a booking.
     *
     * @param bookingDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public BookingDTO save(BookingFullDTO bookingDTO) {
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
    public Optional<BookingFullDTO> findOne(Long id) {
        log.debug("Request to get Booking : {}", id);
        return bookingRepository.findById(id)
            .map(bookingMapper::toFullDto);
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

	@Override
	public List<BookingMinDTO> find(String value) {
		List<Booking> l =  this.bookingCustomRepository.find(value);
		List<BookingMinDTO> result =  new ArrayList<>();
		for (Booking booking : l) {
			result.add(this.bookingMapper.toMinDto(booking));
		}
		return result;
	}
}
