package ar.com.intelimanagement.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
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

    @NotNull
    @Column(name = "payment_type", nullable = false)
    private String paymentType;

    @Column(name = "payment_credit_card")
    private Double paymentCreditCard;

    @Column(name = "payment_points_in_usd")
    private Integer paymentPointsInUSD;

    @NotNull
    @Column(name = "juniper_sale_price", nullable = false)
    private Double juniperSalePrice;

    @NotNull
    @Column(name = "juniper_reservation_cost", nullable = false)
    private Double juniperReservationCost;

    @Column(name = "benefit_in_reservation")
    private Double benefitInReservation;

    @ManyToOne
    @JsonIgnoreProperties("bookings")
    private Company company;

    @OneToMany(mappedBy = "booking",cascade=CascadeType.ALL)
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ProductByBooking> products = new HashSet<>();

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

    public String getPaymentType() {
        return paymentType;
    }

    public Booking paymentType(String paymentType) {
        this.paymentType = paymentType;
        return this;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public Double getPaymentCreditCard() {
        return paymentCreditCard;
    }

    public Booking paymentCreditCard(Double paymentCreditCard) {
        this.paymentCreditCard = paymentCreditCard;
        return this;
    }

    public void setPaymentCreditCard(Double paymentCreditCard) {
        this.paymentCreditCard = paymentCreditCard;
    }

    public Integer getPaymentPointsInUSD() {
        return paymentPointsInUSD;
    }

    public Booking paymentPointsInUSD(Integer paymentPointsInUSD) {
        this.paymentPointsInUSD = paymentPointsInUSD;
        return this;
    }

    public void setPaymentPointsInUSD(Integer paymentPointsInUSD) {
        this.paymentPointsInUSD = paymentPointsInUSD;
    }

    public Double getJuniperSalePrice() {
        return juniperSalePrice;
    }

    public Booking juniperSalePrice(Double juniperSalePrice) {
        this.juniperSalePrice = juniperSalePrice;
        return this;
    }

    public void setJuniperSalePrice(Double juniperSalePrice) {
        this.juniperSalePrice = juniperSalePrice;
    }

    public Double getJuniperReservationCost() {
        return juniperReservationCost;
    }

    public Booking juniperReservationCost(Double juniperReservationCost) {
        this.juniperReservationCost = juniperReservationCost;
        return this;
    }

    public void setJuniperReservationCost(Double juniperReservationCost) {
        this.juniperReservationCost = juniperReservationCost;
    }

    public Double getBenefitInReservation() {
        return benefitInReservation;
    }

    public Booking benefitInReservation(Double benefitInReservation) {
        this.benefitInReservation = benefitInReservation;
        return this;
    }

    public void setBenefitInReservation(Double benefitInReservation) {
        this.benefitInReservation = benefitInReservation;
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

    public Set<ProductByBooking> getProducts() {
        return products;
    }

    public Booking products(Set<ProductByBooking> productByBookings) {
        this.products = productByBookings;
        return this;
    }

    public Booking addProducts(ProductByBooking productByBooking) {
        this.products.add(productByBooking);
        productByBooking.setBooking(this);
        return this;
    }

    public Booking removeProducts(ProductByBooking productByBooking) {
        this.products.remove(productByBooking);
        productByBooking.setBooking(null);
        return this;
    }

    public void setProducts(Set<ProductByBooking> productByBookings) {
        this.products = productByBookings;
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
            ", paymentType='" + getPaymentType() + "'" +
            ", paymentCreditCard=" + getPaymentCreditCard() +
            ", paymentPointsInUSD=" + getPaymentPointsInUSD() +
            ", juniperSalePrice=" + getJuniperSalePrice() +
            ", juniperReservationCost=" + getJuniperReservationCost() +
            ", benefitInReservation=" + getBenefitInReservation() +
            "}";
    }
}
