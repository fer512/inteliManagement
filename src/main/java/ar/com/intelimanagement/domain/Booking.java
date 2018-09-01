package ar.com.intelimanagement.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Booking.
 */
@Entity
@Table(name = "booking")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Booking implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_transaction")
    private String idTransaction;

    @Column(name = "id_reserve_locator_juniper")
    private String idReserveLocatorJuniper;

    @Column(name = "id_reserve_locator_external")
    private String idReserveLocatorExternal;

    @Column(name = "detail")
    private String detail;

    @ManyToOne
    @JsonIgnoreProperties("bookings")
    private Company company;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdTransaction() {
        return idTransaction;
    }

    public Booking idTransaction(String idTransaction) {
        this.idTransaction = idTransaction;
        return this;
    }

    public void setIdTransaction(String idTransaction) {
        this.idTransaction = idTransaction;
    }

    public String getIdReserveLocatorJuniper() {
        return idReserveLocatorJuniper;
    }

    public Booking idReserveLocatorJuniper(String idReserveLocatorJuniper) {
        this.idReserveLocatorJuniper = idReserveLocatorJuniper;
        return this;
    }

    public void setIdReserveLocatorJuniper(String idReserveLocatorJuniper) {
        this.idReserveLocatorJuniper = idReserveLocatorJuniper;
    }

    public String getIdReserveLocatorExternal() {
        return idReserveLocatorExternal;
    }

    public Booking idReserveLocatorExternal(String idReserveLocatorExternal) {
        this.idReserveLocatorExternal = idReserveLocatorExternal;
        return this;
    }

    public void setIdReserveLocatorExternal(String idReserveLocatorExternal) {
        this.idReserveLocatorExternal = idReserveLocatorExternal;
    }

    public String getDetail() {
        return detail;
    }

    public Booking detail(String detail) {
        this.detail = detail;
        return this;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Company getCompany() {
        return company;
    }

    public Booking company(Company company) {
        this.company = company;
        return this;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Booking booking = (Booking) o;
        if (booking.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), booking.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Booking{" +
            "id=" + getId() +
            ", idTransaction='" + getIdTransaction() + "'" +
            ", idReserveLocatorJuniper='" + getIdReserveLocatorJuniper() + "'" +
            ", idReserveLocatorExternal='" + getIdReserveLocatorExternal() + "'" +
            ", detail='" + getDetail() + "'" +
            "}";
    }
}
