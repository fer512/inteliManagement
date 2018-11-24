package ar.com.intelimanagement.repository;

import ar.com.intelimanagement.domain.Booking;
import ar.com.intelimanagement.service.dto.BookingFullDTO;

import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Booking entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BookingRepository extends JpaRepository<Booking, Long>, JpaSpecificationExecutor<Booking> {

}
