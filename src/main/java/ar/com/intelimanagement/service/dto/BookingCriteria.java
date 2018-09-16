package ar.com.intelimanagement.service.dto;

import java.io.Serializable;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;






/**
 * Criteria class for the Booking entity. This class is used in BookingResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /bookings?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class BookingCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private StringFilter idTransaction;

    private StringFilter idReserveLocatorJuniper;

    private StringFilter idReserveLocatorExternal;

    private StringFilter detail;

    private StringFilter paymentType;

    private DoubleFilter paymentCreditCard;

    private IntegerFilter paymentPointsInUSD;

    private DoubleFilter juniperSalePrice;

    private DoubleFilter juniperReservationCost;

    private DoubleFilter benefitInReservation;

    private LongFilter companyId;

    private LongFilter productsId;

    public BookingCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getIdTransaction() {
        return idTransaction;
    }

    public void setIdTransaction(StringFilter idTransaction) {
        this.idTransaction = idTransaction;
    }

    public StringFilter getIdReserveLocatorJuniper() {
        return idReserveLocatorJuniper;
    }

    public void setIdReserveLocatorJuniper(StringFilter idReserveLocatorJuniper) {
        this.idReserveLocatorJuniper = idReserveLocatorJuniper;
    }

    public StringFilter getIdReserveLocatorExternal() {
        return idReserveLocatorExternal;
    }

    public void setIdReserveLocatorExternal(StringFilter idReserveLocatorExternal) {
        this.idReserveLocatorExternal = idReserveLocatorExternal;
    }

    public StringFilter getDetail() {
        return detail;
    }

    public void setDetail(StringFilter detail) {
        this.detail = detail;
    }

    public StringFilter getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(StringFilter paymentType) {
        this.paymentType = paymentType;
    }

    public DoubleFilter getPaymentCreditCard() {
        return paymentCreditCard;
    }

    public void setPaymentCreditCard(DoubleFilter paymentCreditCard) {
        this.paymentCreditCard = paymentCreditCard;
    }

    public IntegerFilter getPaymentPointsInUSD() {
        return paymentPointsInUSD;
    }

    public void setPaymentPointsInUSD(IntegerFilter paymentPointsInUSD) {
        this.paymentPointsInUSD = paymentPointsInUSD;
    }

    public DoubleFilter getJuniperSalePrice() {
        return juniperSalePrice;
    }

    public void setJuniperSalePrice(DoubleFilter juniperSalePrice) {
        this.juniperSalePrice = juniperSalePrice;
    }

    public DoubleFilter getJuniperReservationCost() {
        return juniperReservationCost;
    }

    public void setJuniperReservationCost(DoubleFilter juniperReservationCost) {
        this.juniperReservationCost = juniperReservationCost;
    }

    public DoubleFilter getBenefitInReservation() {
        return benefitInReservation;
    }

    public void setBenefitInReservation(DoubleFilter benefitInReservation) {
        this.benefitInReservation = benefitInReservation;
    }

    public LongFilter getCompanyId() {
        return companyId;
    }

    public void setCompanyId(LongFilter companyId) {
        this.companyId = companyId;
    }

    public LongFilter getProductsId() {
        return productsId;
    }

    public void setProductsId(LongFilter productsId) {
        this.productsId = productsId;
    }

    @Override
    public String toString() {
        return "BookingCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (idTransaction != null ? "idTransaction=" + idTransaction + ", " : "") +
                (idReserveLocatorJuniper != null ? "idReserveLocatorJuniper=" + idReserveLocatorJuniper + ", " : "") +
                (idReserveLocatorExternal != null ? "idReserveLocatorExternal=" + idReserveLocatorExternal + ", " : "") +
                (detail != null ? "detail=" + detail + ", " : "") +
                (paymentType != null ? "paymentType=" + paymentType + ", " : "") +
                (paymentCreditCard != null ? "paymentCreditCard=" + paymentCreditCard + ", " : "") +
                (paymentPointsInUSD != null ? "paymentPointsInUSD=" + paymentPointsInUSD + ", " : "") +
                (juniperSalePrice != null ? "juniperSalePrice=" + juniperSalePrice + ", " : "") +
                (juniperReservationCost != null ? "juniperReservationCost=" + juniperReservationCost + ", " : "") +
                (benefitInReservation != null ? "benefitInReservation=" + benefitInReservation + ", " : "") +
                (companyId != null ? "companyId=" + companyId + ", " : "") +
                (productsId != null ? "productsId=" + productsId + ", " : "") +
            "}";
    }

}
