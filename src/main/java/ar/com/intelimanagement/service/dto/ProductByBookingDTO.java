package ar.com.intelimanagement.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the ProductByBooking entity.
 */
public class ProductByBookingDTO implements Serializable {

    private Long id;

    private Long bookingId;

    private String idReserveLocatorJuniperProduct;
    
    private String idReserveLocatorJuniperProvider;
    
    private String idReserveLocatorJuniper;
    
    private String idReserveLocatorExternal;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
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

	
	public String getIdReserveLocatorJuniperProduct() {
		return idReserveLocatorJuniperProduct;
	}

	public void setIdReserveLocatorJuniperProduct(String idReserveLocatorJuniperProduct) {
		this.idReserveLocatorJuniperProduct = idReserveLocatorJuniperProduct;
	}

	public String getIdReserveLocatorJuniperProvider() {
		return idReserveLocatorJuniperProvider;
	}

	public void setIdReserveLocatorJuniperProvider(String idReserveLocatorJuniperProvider) {
		this.idReserveLocatorJuniperProvider = idReserveLocatorJuniperProvider;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProductByBookingDTO productByBookingDTO = (ProductByBookingDTO) o;
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
		return "ProductByBookingDTO [id=" + id + ", bookingId=" + bookingId
				+ ", idReserveLocatorJuniperProduct=" + idReserveLocatorJuniperProduct + ", idReserveLocatorJuniper="
				+ idReserveLocatorJuniper + ", idReserveLocatorExternal=" + idReserveLocatorExternal + "]";
	}

}
