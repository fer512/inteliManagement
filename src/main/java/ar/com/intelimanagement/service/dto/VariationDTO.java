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

    private Double extraCharge;

    private Double newCharge;

    private Double newCost;

    private Double newBenefit;

    private Integer newExternalLocatorId;

    private String comments;

    private ZonedDateTime creationDate;

    private Long creationUser;

    @NotNull
    private Long product;

    @NotNull
    private String area;

    @NotNull
    private String campaing;

    @NotNull
    private String reason;

    private Boolean recoverable;

    private Integer refundInPoints;

    private Double refundInCash;

    private Boolean cacel;

    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getExtraCharge() {
		return extraCharge;
	}

	public void setExtraCharge(Double extraCharge) {
		this.extraCharge = extraCharge;
	}

	public Double getNewCharge() {
		return newCharge;
	}

	public void setNewCharge(Double newCharge) {
		this.newCharge = newCharge;
	}

	public Double getNewCost() {
		return newCost;
	}

	public void setNewCost(Double newCost) {
		this.newCost = newCost;
	}

	public Double getNewBenefit() {
		return newBenefit;
	}

	public void setNewBenefit(Double newBenefit) {
		this.newBenefit = newBenefit;
	}

	public Integer getNewExternalLocatorId() {
		return newExternalLocatorId;
	}

	public void setNewExternalLocatorId(Integer newExternalLocatorId) {
		this.newExternalLocatorId = newExternalLocatorId;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public ZonedDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(ZonedDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public Long getCreationUser() {
		return creationUser;
	}

	public void setCreationUser(Long creationUser) {
		this.creationUser = creationUser;
	}

	public Long getProduct() {
		return product;
	}

	public void setProduct(Long product) {
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

	public Boolean getRecoverable() {
		return recoverable;
	}

	public void setRecoverable(Boolean recoverable) {
		this.recoverable = recoverable;
	}

	public Integer getRefundInPoints() {
		return refundInPoints;
	}

	public void setRefundInPoints(Integer refundInPoints) {
		this.refundInPoints = refundInPoints;
	}

	public Double getRefundInCash() {
		return refundInCash;
	}

	public void setRefundInCash(Double refundInCash) {
		this.refundInCash = refundInCash;
	}

	public Boolean getCacel() {
		return cacel;
	}

	public void setCacel(Boolean cacel) {
		this.cacel = cacel;
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
}
