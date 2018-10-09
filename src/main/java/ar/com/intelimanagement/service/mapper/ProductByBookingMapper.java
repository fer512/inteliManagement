package ar.com.intelimanagement.service.mapper;

import ar.com.intelimanagement.domain.*;
import ar.com.intelimanagement.service.dto.ProductByBookingDTO;

import java.util.Set;

import org.mapstruct.*;

/**
 * Mapper for the entity ProductByBooking and its DTO ProductByBookingDTO.
 */
@Mapper(componentModel = "spring", uses = {ProductMapper.class, BookingMapper.class})
public interface ProductByBookingMapper extends EntityMapper<ProductByBookingDTO, ProductByBooking> {

    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "booking.id", target = "bookingId")
    ProductByBookingDTO toDto(ProductByBooking productByBooking);

    @Mapping(source = "productId", target = "product")
    @Mapping(source = "bookingId", target = "booking")
    ProductByBooking toEntity(ProductByBookingDTO productByBookingDTO);
    
    Set<ProductByBooking> productByBookingDTOToProductByBooking(Set<ProductByBookingDTO> products);
    
    default ProductByBooking fromId(Long id) {
        if (id == null) {
            return null;
        }
        ProductByBooking productByBooking = new ProductByBooking();
        productByBooking.setId(id);
        return productByBooking;
    }
}
