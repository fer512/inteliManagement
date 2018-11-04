package ar.com.intelimanagement.service.mapper;

import ar.com.intelimanagement.domain.*;
import ar.com.intelimanagement.service.dto.BookingDTO;
import ar.com.intelimanagement.service.dto.BookingFullDTO;
import ar.com.intelimanagement.service.dto.BookingMinDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Booking and its DTO BookingDTO.
 */
@Mapper(componentModel = "spring", uses = {CompanyMapper.class,ProductByBookingMapper.class})
public interface BookingMapper extends EntityMapper<BookingDTO, Booking> {

    @Mapping(source = "company.id", target = "companyId")
    BookingDTO toDto(Booking booking);

    BookingFullDTO toFullDto(Booking booking);
    
    BookingMinDTO toMinDto(Booking booking);
    
    @Mapping(source = "companyId", target = "company")
    Booking toEntity(BookingDTO bookingDTO);

    @Mapping(source = "companyId", target = "company")
    Booking toEntity(BookingFullDTO bookingDTO);
    
    default Booking fromId(Long id) {
        if (id == null) {
            return null;
        }
        Booking booking = new Booking();
        booking.setId(id);
        return booking;
    }
}
