package ar.com.intelimanagement.service.dto;

import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Variation entity.
 */
public class VariationListDTO implements Serializable {

    private Long id;

    private Double extraCharge;

    private Double newCharge;

    private Double newCost;

    private Double newBenefit;

    private Integer newExternalLocatorId;

    private String comments;

    private ZonedDateTime creationDate;

    private UserMinDTO creationUser;

    @NotNull
    private ProductBookingByVariationDTO product;

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

    private ApprovalsDTO approvals;
    
    
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

	public UserMinDTO getCreationUser() {
		return creationUser;
	}

	public void setCreationUser(UserMinDTO creationUser) {
		this.creationUser = creationUser;
	}

	public ProductBookingByVariationDTO getProduct() {
		return product;
	}

	public void setProduct(ProductBookingByVariationDTO product) {
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

        VariationListDTO variationDTO = (VariationListDTO) o;
        if (variationDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), variationDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

	public ApprovalsDTO getApprovals() {
		return approvals;
	}

	public void setApprovals(ApprovalsDTO approvalsId) {
		this.approvals = approvalsId;
	}

	@Override
	public String toString() {
		return "VariationDTO [id=" + id + ", extraCharge=" + extraCharge + ", newCharge=" + newCharge + ", newCost="
				+ newCost + ", newBenefit=" + newBenefit + ", newExternalLocatorId=" + newExternalLocatorId
				+ ", comments=" + comments + ", creationDate=" + creationDate + ", creationUser=" + creationUser
				+ ", product=" + product + ", area=" + area + ", campaing=" + campaing + ", reason=" + reason
				+ ", recoverable=" + recoverable + ", refundInPoints=" + refundInPoints + ", refundInCash="
				+ refundInCash + ", cacel=" + cacel + ", approvals=" + approvals + "]";
	}
	
	
}
