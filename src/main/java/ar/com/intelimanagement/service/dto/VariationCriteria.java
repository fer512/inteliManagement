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

    private DoubleFilter extraCharge;

    private DoubleFilter newCharge;

    private DoubleFilter newCost;

    private DoubleFilter newBenefit;

    private IntegerFilter newExternalLocatorId;

    private StringFilter comments;

    private ZonedDateTimeFilter creationDate;

    private LongFilter creationUser;

    private LongFilter product;

    private StringFilter area;

    private StringFilter campaing;

    private StringFilter reason;

    private BooleanFilter recoverable;

    private IntegerFilter refundInPoints;

    private DoubleFilter refundInCash;

    private BooleanFilter cacel;

    public VariationCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getComments() {
        return comments;
    }

    public void setComments(StringFilter comments) {
        this.comments = comments;
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

    public BooleanFilter getCacel() {
        return cacel;
    }

    public void setCacel(BooleanFilter cacel) {
        this.cacel = cacel;
    }

	public DoubleFilter getExtraCharge() {
		return extraCharge;
	}

	public void setExtraCharge(DoubleFilter extraCharge) {
		this.extraCharge = extraCharge;
	}

	public DoubleFilter getNewCharge() {
		return newCharge;
	}

	public void setNewCharge(DoubleFilter newCharge) {
		this.newCharge = newCharge;
	}

	public DoubleFilter getNewCost() {
		return newCost;
	}

	public void setNewCost(DoubleFilter newCost) {
		this.newCost = newCost;
	}

	public DoubleFilter getNewBenefit() {
		return newBenefit;
	}

	public void setNewBenefit(DoubleFilter newBenefit) {
		this.newBenefit = newBenefit;
	}

	public IntegerFilter getNewExternalLocatorId() {
		return newExternalLocatorId;
	}

	public void setNewExternalLocatorId(IntegerFilter newExternalLocatorId) {
		this.newExternalLocatorId = newExternalLocatorId;
	}

	public ZonedDateTimeFilter getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(ZonedDateTimeFilter creationDate) {
		this.creationDate = creationDate;
	}

	public LongFilter getCreationUser() {
		return creationUser;
	}

	public void setCreationUser(LongFilter creationUser) {
		this.creationUser = creationUser;
	}

	public LongFilter getProduct() {
		return product;
	}

	public void setProduct(LongFilter product) {
		this.product = product;
	}

	public IntegerFilter getRefundInPoints() {
		return refundInPoints;
	}

	public void setRefundInPoints(IntegerFilter refundInPoints) {
		this.refundInPoints = refundInPoints;
	}

	public DoubleFilter getRefundInCash() {
		return refundInCash;
	}

	public void setRefundInCash(DoubleFilter refundInCash) {
		this.refundInCash = refundInCash;
	}

	@Override
	public String toString() {
		return "VariationCriteria [id=" + id + ", extraCharge=" + extraCharge + ", newCharge=" + newCharge
				+ ", newCost=" + newCost + ", newBenefit=" + newBenefit + ", newExternalLocatorId="
				+ newExternalLocatorId + ", comments=" + comments + ", creationDate=" + creationDate + ", creationUser="
				+ creationUser + ", product=" + product + ", area=" + area + ", campaing=" + campaing + ", reason="
				+ reason + ", recoverable=" + recoverable + ", refundInPoints=" + refundInPoints + ", refundInCash="
				+ refundInCash + ", cacel=" + cacel + "]";
	}

    
}
