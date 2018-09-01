package ar.com.intelimanagement.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import ar.com.intelimanagement.domain.enumeration.ApprovalsStatusType;

/**
 * A Approvals.
 */
@Entity
@Table(name = "approvals")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Approvals implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "stast_date")
    private Instant stastDate;

    @Column(name = "end_date")
    private Instant endDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ApprovalsStatusType status;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getStastDate() {
        return stastDate;
    }

    public Approvals stastDate(Instant stastDate) {
        this.stastDate = stastDate;
        return this;
    }

    public void setStastDate(Instant stastDate) {
        this.stastDate = stastDate;
    }

    public Instant getEndDate() {
        return endDate;
    }

    public Approvals endDate(Instant endDate) {
        this.endDate = endDate;
        return this;
    }

    public void setEndDate(Instant endDate) {
        this.endDate = endDate;
    }

    public ApprovalsStatusType getStatus() {
        return status;
    }

    public Approvals status(ApprovalsStatusType status) {
        this.status = status;
        return this;
    }

    public void setStatus(ApprovalsStatusType status) {
        this.status = status;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Approvals approvals = (Approvals) o;
        if (approvals.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), approvals.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Approvals{" +
            "id=" + getId() +
            ", stastDate='" + getStastDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
