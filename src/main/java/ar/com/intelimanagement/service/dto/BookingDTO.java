package ar.com.intelimanagement.service.dto;

import javax.validation.constraints.*;

import ar.com.intelimanagement.domain.Variation;

import java.io.Serializable;
import java.util.List;
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

    @NotNull
    private String paymentType;

    private Double paymentCreditCard;

    private Integer paymentPointsInUSD;

    @NotNull
    private Double juniperSalePrice;

    @NotNull
    private Double juniperReservationCost;

    private Double benefitInReservation;

    private Long companyId;

    private List<VariationDTO> variations;
    
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

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public Double getPaymentCreditCard() {
        return paymentCreditCard;
    }

    public void setPaymentCreditCard(Double paymentCreditCard) {
        this.paymentCreditCard = paymentCreditCard;
    }

    public Integer getPaymentPointsInUSD() {
        return paymentPointsInUSD;
    }

    public void setPaymentPointsInUSD(Integer paymentPointsInUSD) {
        this.paymentPointsInUSD = paymentPointsInUSD;
    }

    public Double getJuniperSalePrice() {
        return juniperSalePrice;
    }

    public void setJuniperSalePrice(Double juniperSalePrice) {
        this.juniperSalePrice = juniperSalePrice;
    }

    public Double getJuniperReservationCost() {
        return juniperReservationCost;
    }

    public void setJuniperReservationCost(Double juniperReservationCost) {
        this.juniperReservationCost = juniperReservationCost;
    }

    public Double getBenefitInReservation() {
        return benefitInReservation;
    }

    public void setBenefitInReservation(Double benefitInReservation) {
        this.benefitInReservation = benefitInReservation;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public List<VariationDTO> getVariations() {
		return variations;
	}

	public void setVariations(List<VariationDTO> variations) {
		this.variations = variations;
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
            ", paymentType='" + getPaymentType() + "'" +
            ", paymentCreditCard=" + getPaymentCreditCard() +
            ", paymentPointsInUSD=" + getPaymentPointsInUSD() +
            ", juniperSalePrice=" + getJuniperSalePrice() +
            ", juniperReservationCost=" + getJuniperReservationCost() +
            ", benefitInReservation=" + getBenefitInReservation() +
            ", company=" + getCompanyId() +
            "}";
    }

}
