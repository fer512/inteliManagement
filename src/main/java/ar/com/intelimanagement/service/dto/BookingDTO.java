package ar.com.intelimanagement.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Booking entity.
 */
public class BookingDTO implements Serializable {

    private Long id;

    private String idTransaction;

    private String idReserveLocatorJuniper;

    private String idReserveLocatorExternal;

    private String detail;

    private Long companyId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdTransaction() {
        return idTransaction;
    }

    public void setIdTransaction(String idTransaction) {
        this.idTransaction = idTransaction;
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

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BookingDTO bookingDTO = (BookingDTO) o;
        if (bookingDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), bookingDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BookingDTO{" +
            "id=" + getId() +
            ", idTransaction='" + getIdTransaction() + "'" +
            ", idReserveLocatorJuniper='" + getIdReserveLocatorJuniper() + "'" +
            ", idReserveLocatorExternal='" + getIdReserveLocatorExternal() + "'" +
            ", detail='" + getDetail() + "'" +
            ", company=" + getCompanyId() +
            "}";
    }
}
