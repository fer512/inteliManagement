package ar.com.intelimanagement.domain;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Variation.
 */
@Entity
@Table(name = "variation")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Variation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "extra_charge")
    private Double extraCharge;

    @Column(name = "new_charge")
    private Double newCharge;

    @Column(name = "new_cost")
    private Double newCost;

    @Column(name = "new_benefit")
    private Double newBenefit;

    @Column(name = "new_external_locator_id")
    private Integer newExternalLocatorId;

    @Column(name = "comments")
    private String comments;

    @NotNull
    @Column(name = "creation_date", nullable = false)
    private ZonedDateTime creationDate;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "creation_user")
    private User creationUser;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductByBooking product;

    @NotNull
    @Column(name = "area", nullable = false)
    private String area;

    @NotNull
    @Column(name = "campaing", nullable = false)
    private String campaing;

    @NotNull
    @Column(name = "reason", nullable = false)
    private String reason;

    @Column(name = "jhi_recoverable")
    private Boolean recoverable;

    @Column(name = "refund_in_points")
    private Integer refundInPoints;

    @Column(name = "refund_in_cash")
    private Double refundInCash;

    @Column(name = "cacel")
    private Boolean cacel;

    @OneToOne
    @JoinColumn(unique = true)
    private Approvals approvals;
    
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

	public User getCreationUser() {
		return creationUser;
	}

	public void setCreationUser(User creationUser) {
		this.creationUser = creationUser;
	}

	public ProductByBooking getProduct() {
		return product;
	}

	public void setProduct(ProductByBooking product) {
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


    public Approvals getApprovals() {
		return approvals;
	}

	public void setApprovals(Approvals approvals) {
		this.approvals = approvals;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Variation variation = (Variation) o;
        if (variation.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), variation.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

	@Override
	public String toString() {
		return "Variation [id=" + id + ", extraCharge=" + extraCharge + ", newCharge=" + newCharge + ", newCost="
				+ newCost + ", newBenefit=" + newBenefit + ", newExternalLocatorId=" + newExternalLocatorId
				+ ", comments=" + comments + ", creationDate=" + creationDate + ", creationUser=" + creationUser
				+ ", product=" + product + ", area=" + area + ", campaing=" + campaing + ", reason=" + reason
				+ ", recoverable=" + recoverable + ", refundInPoints=" + refundInPoints + ", refundInCash="
				+ refundInCash + ", cacel=" + cacel + ", approvals=" + approvals + "]";
	}

    
}
