package ar.com.intelimanagement.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the ProductByBooking entity.
 */
public class ProductByBookingFullDTO implements Serializable {

    private Long id;

    private BookingMinDTO booking;

    private ProductDTO reserveLocatorJuniperProduct;
    
    private ProviderDTO reserveLocatorJuniperProvider;
    
    private String idReserveLocatorJuniper;
    
    private String idReserveLocatorExternal;
  
    
    public ProductDTO getReserveLocatorJuniperProduct() {
		return reserveLocatorJuniperProduct;
	}

	public void setReserveLocatorJuniperProduct(ProductDTO idReserveLocatorJuniperProduct) {
		this.reserveLocatorJuniperProduct = idReserveLocatorJuniperProduct;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BookingMinDTO getBooking() {
        return booking;
    }

    public void setBooking(BookingMinDTO bookingId) {
        this.booking = bookingId;
    }
    
	public String getIdReserveLocatorJuniper() {
		return idReserveLocatorJuniper;
	}

	public void setIdReserveLocatorJuniper(String idReserveLocatorJuniper) {
		this.idReserveLocatorJuniper = idReserveLocatorJuniper;
	}

	public String getIdReserveLocatorExternal() {
		return idReserveLocatorExternal;
	}

	public void setIdReserveLocatorExternal(String idReserveLocatorExternal) {
		this.idReserveLocatorExternal = idReserveLocatorExternal;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProductByBookingFullDTO productByBookingDTO = (ProductByBookingFullDTO) o;
        if (productByBookingDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), productByBookingDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

	@Override
	public String toString() {
		return "ProductByBookingDTO [id=" + id + ", booking=" + booking
				+ ", idReserveLocatorJuniperProduct=" + reserveLocatorJuniperProduct + ", idReserveLocatorJuniper="
				+ idReserveLocatorJuniper + ", idReserveLocatorExternal=" + idReserveLocatorExternal + "]";
	}

	public ProviderDTO getReserveLocatorJuniperProvider() {
		return reserveLocatorJuniperProvider;
	}

	public void setReserveLocatorJuniperProvider(ProviderDTO idReserveLocatorJuniperProvider) {
		this.reserveLocatorJuniperProvider = idReserveLocatorJuniperProvider;
	}

}
