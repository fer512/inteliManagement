package ar.com.intelimanagement.service.dto;

import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Variation entity.
 */
public class VariationDTO implements Serializable {

    private Long id;

    private Double extra_charge;

    private Double new_charge;

    private Double new_cost;

    private Double new_benefit;

    private Integer new_external_locator_id;

    private String comments;

    @NotNull
    private ZonedDateTime creation_date;

    @NotNull
    private String creation_user;

    @NotNull
    private String provider;

    @NotNull
    private String product;

    @NotNull
    private String area;

    @NotNull
    private String campaing;

    @NotNull
    private String reason;

    private Boolean recoverable;

    private Integer refund_in_points;

    private Double refund_in_cash;

    private Boolean cacel;

    private Long relationship_user_variationId;

    private String relationship_user_variationLogin;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getExtra_charge() {
        return extra_charge;
    }

    public void setExtra_charge(Double extra_charge) {
        this.extra_charge = extra_charge;
    }

    public Double getNew_charge() {
        return new_charge;
    }

    public void setNew_charge(Double new_charge) {
        this.new_charge = new_charge;
    }

    public Double getNew_cost() {
        return new_cost;
    }

    public void setNew_cost(Double new_cost) {
        this.new_cost = new_cost;
    }

    public Double getNew_benefit() {
        return new_benefit;
    }

    public void setNew_benefit(Double new_benefit) {
        this.new_benefit = new_benefit;
    }

    public Integer getNew_external_locator_id() {
        return new_external_locator_id;
    }

    public void setNew_external_locator_id(Integer new_external_locator_id) {
        this.new_external_locator_id = new_external_locator_id;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public ZonedDateTime getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(ZonedDateTime creation_date) {
        this.creation_date = creation_date;
    }

    public String getCreation_user() {
        return creation_user;
    }

    public void setCreation_user(String creation_user) {
        this.creation_user = creation_user;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCampaing() {
        return campaing;
    }

    public void setCampaing(String campaing) {
        this.campaing = campaing;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Boolean isRecoverable() {
        return recoverable;
    }

    public void setRecoverable(Boolean recoverable) {
        this.recoverable = recoverable;
    }

    public Integer getRefund_in_points() {
        return refund_in_points;
    }

    public void setRefund_in_points(Integer refund_in_points) {
        this.refund_in_points = refund_in_points;
    }

    public Double getRefund_in_cash() {
        return refund_in_cash;
    }

    public void setRefund_in_cash(Double refund_in_cash) {
        this.refund_in_cash = refund_in_cash;
    }

    public Boolean isCacel() {
        return cacel;
    }

    public void setCacel(Boolean cacel) {
        this.cacel = cacel;
    }

    public Long getRelationship_user_variationId() {
        return relationship_user_variationId;
    }

    public void setRelationship_user_variationId(Long userId) {
        this.relationship_user_variationId = userId;
    }

    public String getRelationship_user_variationLogin() {
        return relationship_user_variationLogin;
    }

    public void setRelationship_user_variationLogin(String userLogin) {
        this.relationship_user_variationLogin = userLogin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        VariationDTO variationDTO = (VariationDTO) o;
        if (variationDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), variationDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "VariationDTO{" +
            "id=" + getId() +
            ", extra_charge=" + getExtra_charge() +
            ", new_charge=" + getNew_charge() +
            ", new_cost=" + getNew_cost() +
            ", new_benefit=" + getNew_benefit() +
            ", new_external_locator_id=" + getNew_external_locator_id() +
            ", comments='" + getComments() + "'" +
            ", creation_date='" + getCreation_date() + "'" +
            ", creation_user='" + getCreation_user() + "'" +
            ", provider='" + getProvider() + "'" +
            ", product='" + getProduct() + "'" +
            ", area='" + getArea() + "'" +
            ", campaing='" + getCampaing() + "'" +
            ", reason='" + getReason() + "'" +
            ", recoverable='" + isRecoverable() + "'" +
            ", refund_in_points=" + getRefund_in_points() +
            ", refund_in_cash=" + getRefund_in_cash() +
            ", cacel='" + isCacel() + "'" +
            ", relationship_user_variation=" + getRelationship_user_variationId() +
            ", relationship_user_variation='" + getRelationship_user_variationLogin() + "'" +
            "}";
    }
}
