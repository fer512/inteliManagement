package ar.com.intelimanagement.service.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * A DTO for the ProductByBooking entity.
 */
public class ProductBookingDTO implements Serializable {

    private Long id;

    private ProductDTO reserveLocatorJuniperProduct;
    
    private ProviderDTO reserveLocatorJuniperProvider;
    
    private String idReserveLocatorJuniper;
    
    private String idReserveLocatorExternal;
  
    private List<VariationMiddleDTO> variations;
    
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

        ProductBookingDTO productByBookingDTO = (ProductBookingDTO) o;
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
		return "ProductByBookingDTO [id=" + id + ", idReserveLocatorJuniperProduct=" + reserveLocatorJuniperProduct + ", idReserveLocatorJuniper="
				+ idReserveLocatorJuniper + ", idReserveLocatorExternal=" + idReserveLocatorExternal + "]";
	}

	public ProviderDTO getReserveLocatorJuniperProvider() {
		return reserveLocatorJuniperProvider;
	}

	public void setReserveLocatorJuniperProvider(ProviderDTO idReserveLocatorJuniperProvider) {
		this.reserveLocatorJuniperProvider = idReserveLocatorJuniperProvider;
	}

	public List<VariationMiddleDTO> getVariations() {
		return variations;
	}

	public void setVariations(List<VariationMiddleDTO> variations) {
		this.variations = variations;
	}

}
