package ar.com.intelimanagement.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import ar.com.intelimanagement.domain.enumeration.ApprovalsStatusType;
import ar.com.intelimanagement.domain.enumeration.NotificationType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A Notification.
 */
@Entity
@Table(name = "notification")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Notification implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private NotificationType type;

    @Column(name = "detail")
    private String detail;
    
    @Column(name = "id_reference")
    private Long idReference;
    
    @Column(name = "creation_date")
    private Instant creationDate;
    
    @Column(name = "stast_date")
    private Instant stastDate;

    @Column(name = "end_date")
    private Instant endDate;

    @Column(name = "jhi_view")
    private Boolean view;

    @ManyToOne
    @JsonIgnoreProperties("notifications")
    private User user;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "creation_user")
    private User userCreation;
    
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

    public Notification stastDate(Instant stastDate) {
        this.stastDate = stastDate;
        return this;
    }

    public void setStastDate(Instant stastDate) {
        this.stastDate = stastDate;
    }

    public Instant getEndDate() {
        return endDate;
    }

    public Notification endDate(Instant endDate) {
        this.endDate = endDate;
        return this;
    }

    public void setEndDate(Instant endDate) {
        this.endDate = endDate;
    }

    public Boolean isView() {
        return view;
    }

    public Notification view(Boolean view) {
        this.view = view;
        return this;
    }

    public void setView(Boolean view) {
        this.view = view;
    }

    public User getUser() {
        return user;
    }

    public Notification user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    public NotificationType getType() {
		return type;
	}

	public void setType(NotificationType type) {
		this.type = type;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Long getIdReference() {
		return idReference;
	}

	public void setIdReference(Long idReference) {
		this.idReference = idReference;
	}

	public User getUserCreation() {
		return userCreation;
	}

	public void setUserCreation(User userCreation) {
		this.userCreation = userCreation;
	}

	public Boolean getView() {
		return view;
	}

	public Instant getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Instant creationDate) {
		this.creationDate = creationDate;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Notification notification = (Notification) o;
        if (notification.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), notification.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Notification{" +
            "id=" + getId() +
            ", type='" + getType() + "'" +
            ", stastDate='" + getStastDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", view='" + isView() + "'" +
            "}";
    }

	public NotificationType getTypeByAppovalsStatus(ApprovalsStatusType status) {
		switch (status) {
		case CREATE:
			return NotificationType.VARIATION_PENDING;
		case PENDING:
			return NotificationType.VARIATION_PENDING;
		case APPOVED:
			return NotificationType.VARIATION_APPROVED;
		default:
			return null;
		}
	}
}
