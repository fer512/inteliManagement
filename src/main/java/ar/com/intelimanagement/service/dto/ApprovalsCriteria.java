package ar.com.intelimanagement.service.dto;

import java.io.Serializable;
import ar.com.intelimanagement.domain.enumeration.ApprovalsStatusType;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

import io.github.jhipster.service.filter.InstantFilter;




/**
 * Criteria class for the Approvals entity. This class is used in ApprovalsResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /approvals?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class ApprovalsCriteria implements Serializable {
    /**
     * Class for filtering ApprovalsStatusType
     */
    public static class ApprovalsStatusTypeFilter extends Filter<ApprovalsStatusType> {
    }

    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private InstantFilter stastDate;

    private InstantFilter endDate;

    private ApprovalsStatusTypeFilter status;

    public ApprovalsCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public InstantFilter getStastDate() {
        return stastDate;
    }

    public void setStastDate(InstantFilter stastDate) {
        this.stastDate = stastDate;
    }

    public InstantFilter getEndDate() {
        return endDate;
    }

    public void setEndDate(InstantFilter endDate) {
        this.endDate = endDate;
    }

    public ApprovalsStatusTypeFilter getStatus() {
        return status;
    }

    public void setStatus(ApprovalsStatusTypeFilter status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ApprovalsCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (stastDate != null ? "stastDate=" + stastDate + ", " : "") +
                (endDate != null ? "endDate=" + endDate + ", " : "") +
                (status != null ? "status=" + status + ", " : "") +
            "}";
    }

}
