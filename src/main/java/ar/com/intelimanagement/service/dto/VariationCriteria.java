package ar.com.intelimanagement.service.dto;

import java.io.Serializable;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;



import io.github.jhipster.service.filter.ZonedDateTimeFilter;


/**
 * Criteria class for the Variation entity. This class is used in VariationResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /variations?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class VariationCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private DoubleFilter extra_charge;

    private DoubleFilter new_charge;

    private DoubleFilter new_cost;

    private DoubleFilter new_benefit;

    private IntegerFilter new_external_locator_id;

    private StringFilter comments;

    private ZonedDateTimeFilter creation_date;

    private StringFilter creation_user;

    private StringFilter provider;

    private StringFilter product;

    private StringFilter area;

    private StringFilter campaing;

    private StringFilter reason;

    private BooleanFilter recoverable;

    private IntegerFilter refund_in_points;

    private DoubleFilter refund_in_cash;

    private BooleanFilter cacel;

    private LongFilter relationship_user_variationId;

    private LongFilter relationship_provider_variationId;

    private LongFilter relationship_product_variationId;

    public VariationCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public DoubleFilter getExtra_charge() {
        return extra_charge;
    }

    public void setExtra_charge(DoubleFilter extra_charge) {
        this.extra_charge = extra_charge;
    }

    public DoubleFilter getNew_charge() {
        return new_charge;
    }

    public void setNew_charge(DoubleFilter new_charge) {
        this.new_charge = new_charge;
    }

    public DoubleFilter getNew_cost() {
        return new_cost;
    }

    public void setNew_cost(DoubleFilter new_cost) {
        this.new_cost = new_cost;
    }

    public DoubleFilter getNew_benefit() {
        return new_benefit;
    }

    public void setNew_benefit(DoubleFilter new_benefit) {
        this.new_benefit = new_benefit;
    }

    public IntegerFilter getNew_external_locator_id() {
        return new_external_locator_id;
    }

    public void setNew_external_locator_id(IntegerFilter new_external_locator_id) {
        this.new_external_locator_id = new_external_locator_id;
    }

    public StringFilter getComments() {
        return comments;
    }

    public void setComments(StringFilter comments) {
        this.comments = comments;
    }

    public ZonedDateTimeFilter getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(ZonedDateTimeFilter creation_date) {
        this.creation_date = creation_date;
    }

    public StringFilter getCreation_user() {
        return creation_user;
    }

    public void setCreation_user(StringFilter creation_user) {
        this.creation_user = creation_user;
    }

    public StringFilter getProvider() {
        return provider;
    }

    public void setProvider(StringFilter provider) {
        this.provider = provider;
    }

    public StringFilter getProduct() {
        return product;
    }

    public void setProduct(StringFilter product) {
        this.product = product;
    }

    public StringFilter getArea() {
        return area;
    }

    public void setArea(StringFilter area) {
        this.area = area;
    }

    public StringFilter getCampaing() {
        return campaing;
    }

    public void setCampaing(StringFilter campaing) {
        this.campaing = campaing;
    }

    public StringFilter getReason() {
        return reason;
    }

    public void setReason(StringFilter reason) {
        this.reason = reason;
    }

    public BooleanFilter getRecoverable() {
        return recoverable;
    }

    public void setRecoverable(BooleanFilter recoverable) {
        this.recoverable = recoverable;
    }

    public IntegerFilter getRefund_in_points() {
        return refund_in_points;
    }

    public void setRefund_in_points(IntegerFilter refund_in_points) {
        this.refund_in_points = refund_in_points;
    }

    public DoubleFilter getRefund_in_cash() {
        return refund_in_cash;
    }

    public void setRefund_in_cash(DoubleFilter refund_in_cash) {
        this.refund_in_cash = refund_in_cash;
    }

    public BooleanFilter getCacel() {
        return cacel;
    }

    public void setCacel(BooleanFilter cacel) {
        this.cacel = cacel;
    }

    public LongFilter getRelationship_user_variationId() {
        return relationship_user_variationId;
    }

    public void setRelationship_user_variationId(LongFilter relationship_user_variationId) {
        this.relationship_user_variationId = relationship_user_variationId;
    }

    public LongFilter getRelationship_provider_variationId() {
        return relationship_provider_variationId;
    }

    public void setRelationship_provider_variationId(LongFilter relationship_provider_variationId) {
        this.relationship_provider_variationId = relationship_provider_variationId;
    }

    public LongFilter getRelationship_product_variationId() {
        return relationship_product_variationId;
    }

    public void setRelationship_product_variationId(LongFilter relationship_product_variationId) {
        this.relationship_product_variationId = relationship_product_variationId;
    }

    @Override
    public String toString() {
        return "VariationCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (extra_charge != null ? "extra_charge=" + extra_charge + ", " : "") +
                (new_charge != null ? "new_charge=" + new_charge + ", " : "") +
                (new_cost != null ? "new_cost=" + new_cost + ", " : "") +
                (new_benefit != null ? "new_benefit=" + new_benefit + ", " : "") +
                (new_external_locator_id != null ? "new_external_locator_id=" + new_external_locator_id + ", " : "") +
                (comments != null ? "comments=" + comments + ", " : "") +
                (creation_date != null ? "creation_date=" + creation_date + ", " : "") +
                (creation_user != null ? "creation_user=" + creation_user + ", " : "") +
                (provider != null ? "provider=" + provider + ", " : "") +
                (product != null ? "product=" + product + ", " : "") +
                (area != null ? "area=" + area + ", " : "") +
                (campaing != null ? "campaing=" + campaing + ", " : "") +
                (reason != null ? "reason=" + reason + ", " : "") +
                (recoverable != null ? "recoverable=" + recoverable + ", " : "") +
                (refund_in_points != null ? "refund_in_points=" + refund_in_points + ", " : "") +
                (refund_in_cash != null ? "refund_in_cash=" + refund_in_cash + ", " : "") +
                (cacel != null ? "cacel=" + cacel + ", " : "") +
                (relationship_user_variationId != null ? "relationship_user_variationId=" + relationship_user_variationId + ", " : "") +
                (relationship_provider_variationId != null ? "relationship_provider_variationId=" + relationship_provider_variationId + ", " : "") +
                (relationship_product_variationId != null ? "relationship_product_variationId=" + relationship_product_variationId + ", " : "") +
            "}";
    }

}
