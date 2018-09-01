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
 * Criteria class for the Company entity. This class is used in CompanyResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /companies?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class CompanyCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private StringFilter name;

    private StringFilter email;

    private StringFilter actived;

    private StringFilter img;

    private LongFilter addressId;

    private LongFilter employeeId;

    private LongFilter bookingsId;

    private LongFilter providersId;

    public CompanyCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getName() {
        return name;
    }

    public void setName(StringFilter name) {
        this.name = name;
    }

    public StringFilter getEmail() {
        return email;
    }

    public void setEmail(StringFilter email) {
        this.email = email;
    }

    public StringFilter getActived() {
        return actived;
    }

    public void setActived(StringFilter actived) {
        this.actived = actived;
    }

    public StringFilter getImg() {
        return img;
    }

    public void setImg(StringFilter img) {
        this.img = img;
    }

    public LongFilter getAddressId() {
        return addressId;
    }

    public void setAddressId(LongFilter addressId) {
        this.addressId = addressId;
    }

    public LongFilter getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(LongFilter employeeId) {
        this.employeeId = employeeId;
    }

    public LongFilter getBookingsId() {
        return bookingsId;
    }

    public void setBookingsId(LongFilter bookingsId) {
        this.bookingsId = bookingsId;
    }

    public LongFilter getProvidersId() {
        return providersId;
    }

    public void setProvidersId(LongFilter providersId) {
        this.providersId = providersId;
    }

    @Override
    public String toString() {
        return "CompanyCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (name != null ? "name=" + name + ", " : "") +
                (email != null ? "email=" + email + ", " : "") +
                (actived != null ? "actived=" + actived + ", " : "") +
                (img != null ? "img=" + img + ", " : "") +
                (addressId != null ? "addressId=" + addressId + ", " : "") +
                (employeeId != null ? "employeeId=" + employeeId + ", " : "") +
                (bookingsId != null ? "bookingsId=" + bookingsId + ", " : "") +
                (providersId != null ? "providersId=" + providersId + ", " : "") +
            "}";
    }

}
