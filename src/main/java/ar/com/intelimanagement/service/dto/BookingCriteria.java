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

    private LongFilter companyId;

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

    public LongFilter getCompanyId() {
        return companyId;
    }

    public void setCompanyId(LongFilter companyId) {
        this.companyId = companyId;
    }

    @Override
    public String toString() {
        return "BookingCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (idTransaction != null ? "idTransaction=" + idTransaction + ", " : "") +
                (idReserveLocatorJuniper != null ? "idReserveLocatorJuniper=" + idReserveLocatorJuniper + ", " : "") +
                (idReserveLocatorExternal != null ? "idReserveLocatorExternal=" + idReserveLocatorExternal + ", " : "") +
                (detail != null ? "detail=" + detail + ", " : "") +
                (companyId != null ? "companyId=" + companyId + ", " : "") +
            "}";
    }

}
