package ar.com.intelimanagement.repository;

import ar.com.intelimanagement.domain.ProductByBooking;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ProductByBooking entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProductByBookingRepository extends JpaRepository<ProductByBooking, Long> {

}
