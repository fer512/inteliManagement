package ar.com.intelimanagement.service.mapper;

import ar.com.intelimanagement.domain.*;
import ar.com.intelimanagement.service.dto.ProductByBookingDTO;
import ar.com.intelimanagement.service.dto.ProductByBookingFullDTO;

import java.util.Set;

import org.mapstruct.*;

/**
 * Mapper for the entity ProductByBooking and its DTO ProductByBookingDTO.
 */
@Mapper(componentModel = "spring", uses = {ProductMapper.class,ProviderMapper.class, BookingMapper.class})
public interface ProductByBookingMapper extends EntityMapper<ProductByBookingDTO, ProductByBooking> {

    @Mapping(source = "product.id", target = "idReserveLocatorJuniperProduct")
    @Mapping(source = "provider.id", target = "idReserveLocatorJuniperProvider")
    @Mapping(source = "booking.id", target = "bookingId")
    ProductByBookingDTO toDto(ProductByBooking productByBooking);

    @Mapping(source = "product", target = "reserveLocatorJuniperProduct")
    @Mapping(source = "provider", target = "reserveLocatorJuniperProvider")
    @Mapping(source = "booking", target = "booking")
    ProductByBookingFullDTO toFullDto(ProductByBooking productByBooking);
    
    @Mapping(source = "idReserveLocatorJuniperProduct", target = "product")
    @Mapping(source = "idReserveLocatorJuniperProvider", target = "provider")
    @Mapping(source = "bookingId", target = "booking")
    ProductByBooking toEntity(ProductByBookingDTO productByBookingDTO);
    
    @Mapping(source = "reserveLocatorJuniperProduct", target = "product")
    @Mapping(source = "reserveLocatorJuniperProvider", target = "provider")
    @Mapping(source = "booking", target = "booking")
    ProductByBooking toEntity(ProductByBookingFullDTO productByBookingDTO);  
    
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
