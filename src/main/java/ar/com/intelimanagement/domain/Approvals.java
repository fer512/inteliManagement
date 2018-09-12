package ar.com.intelimanagement.domain;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import ar.com.intelimanagement.domain.enumeration.ApprovalsStatusType;

/**
 * A Approvals.
 */
@Entity
@Table(name = "approvals")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type", 
  discriminatorType = DiscriminatorType.STRING)
public class Approvals extends AbstractAuditingEntity implements Serializable {

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

    @NotNull
    @ManyToOne
    @JoinColumn(name = "creation_user")
    private User creationUser;
    
    @OneToMany
    @JoinColumn(name = "approval_id")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private List<ApprovalHistory> history;
    
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


	public List<ApprovalHistory> getHistory() {
		return history;
	}

	public void setHistory(List<ApprovalHistory> history) {
		this.history = history;
	}
	
	public User getCreationUser() {
		return creationUser;
	}

	public void setCreationUser(User creationUser) {
		this.creationUser = creationUser;
	}

	public Boolean approved(User user){
		return this.getHistory().stream().anyMatch(h-> h.getUser().getId().equals(user.getId()) && ApprovalsStatusType.APPOVED.equals(h.getStatus()));
		//throw
	}
	
	public Boolean validDate(){
		Instant now = Instant.now();
		return now.isAfter(this.getStastDate()) && now.isBefore(this.getEndDate());
	}
	
	public Boolean validStatus(){
		return ApprovalsStatusType.PENDING.equals(this.getStatus());//throw
	}
	
	public Approvals approve(User user) {
		if(this.validStatus() && this.validDate() && !this.approved(user)) {
			return this.approveOK(user);
		}
		return null;
	}

	private Approvals approveOK(User user) {
		return this.approveAny(user);
	}

	private Approvals approveAny(User user) {
		ApprovalHistory history = new ApprovalHistory();
		history.setStatus(ApprovalsStatusType.APPOVED);
		history.setUser(user);
		this.getHistory().add(history);
		this.setStatus(ApprovalsStatusType.APPOVED);
		return this;
	}
	
	@Override
	public String toString() {
		return "Approvals [id=" + id + ", stastDate=" + stastDate + ", endDate=" + endDate + ", status=" + status
				 + ", creationUser=" + creationUser + ", history=" + history + "]";
	}

	public List<User> getUserByNextLevel() {
		return new ArrayList<User>();
	}
	
	
}
