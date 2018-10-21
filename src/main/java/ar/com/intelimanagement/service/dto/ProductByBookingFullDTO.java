package ar.com.intelimanagement.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the ProductByBooking entity.
 */
public class ProductByBookingFullDTO implements Serializable {

    private Long id;

    private ProductDTO product;

    private BookingMinDTO booking;

    private String idReserveLocatorJuniperProduct;
    
    private String idReserveLocatorJuniper;
    
    private String idReserveLocatorExternal;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO productId) {
        this.product = productId;
    }

    public BookingMinDTO getBooking() {
        return booking;
    }

    public void setBooking(BookingMinDTO bookingId) {
        this.booking = bookingId;
    }
    
    public String getIdReserveLocatorJuniperProduct() {
		return idReserveLocatorJuniperProduct;
	}

	public void setIdReserveLocatorJuniperProduct(String idReserveLocatorJuniperProduct) {
		this.idReserveLocatorJuniperProduct = idReserveLocatorJuniperProduct;
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
		return "ProductByBookingDTO [id=" + id + ", product=" + product + ", booking=" + booking
				+ ", idReserveLocatorJuniperProduct=" + idReserveLocatorJuniperProduct + ", idReserveLocatorJuniper="
				+ idReserveLocatorJuniper + ", idReserveLocatorExternal=" + idReserveLocatorExternal + "]";
	}

}
