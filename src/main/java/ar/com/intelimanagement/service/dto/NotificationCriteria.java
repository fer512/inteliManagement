package ar.com.intelimanagement.service.dto;

import java.io.Serializable;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

import io.github.jhipster.service.filter.InstantFilter;




/**
 * Criteria class for the Notification entity. This class is used in NotificationResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /notifications?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class NotificationCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private LongFilter id;

    private StringFilter name;

    private InstantFilter stastDate;

    private InstantFilter endDate;

    private BooleanFilter view;

    private LongFilter userId;

    public NotificationCriteria() {
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

    public BooleanFilter getView() {
        return view;
    }

    public void setView(BooleanFilter view) {
        this.view = view;
    }


    @Override
    public String toString() {
        return "NotificationCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (name != null ? "name=" + name + ", " : "") +
                (stastDate != null ? "stastDate=" + stastDate + ", " : "") +
                (endDate != null ? "endDate=" + endDate + ", " : "") +
                (view != null ? "view=" + view + ", " : "") +
                (userId != null ? "userId=" + userId + ", " : "") +
            "}";
    }

	public LongFilter getUserId() {
		return userId;
	}

	public void setUserId(LongFilter userId) {
		this.userId = userId;
	}

}
