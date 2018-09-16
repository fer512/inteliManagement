package ar.com.intelimanagement.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the ProductByBooking entity.
 */
public class ProductByBookingDTO implements Serializable {

    private Long id;

    private Long productId;

    private Long bookingId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
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
        return "ProductByBookingDTO{" +
            "id=" + getId() +
            ", product=" + getProductId() +
            ", booking=" + getBookingId() +
            "}";
    }
}
