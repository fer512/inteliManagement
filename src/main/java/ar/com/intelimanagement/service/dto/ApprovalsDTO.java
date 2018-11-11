package ar.com.intelimanagement.service.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import ar.com.intelimanagement.domain.enumeration.ApprovalsStatusType;

/**
 * A DTO for the Approvals entity.
 */
public class ApprovalsDTO implements Serializable {

    private Long id;

    private Instant stastDate;

    private Instant endDate;

    private ApprovalsStatusType status;
    
    private List<ApprovalHistoryDTO> history;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getStastDate() {
        return stastDate;
    }

    public void setStastDate(Instant stastDate) {
        this.stastDate = stastDate;
    }

    public Instant getEndDate() {
        return endDate;
    }

    public void setEndDate(Instant endDate) {
        this.endDate = endDate;
    }

    public ApprovalsStatusType getStatus() {
        return status;
    }

    public void setStatus(ApprovalsStatusType status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ApprovalsDTO approvalsDTO = (ApprovalsDTO) o;
        if (approvalsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), approvalsDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ApprovalsDTO{" +
            "id=" + getId() +
            ", stastDate='" + getStastDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }

	public List<ApprovalHistoryDTO> getHistory() {
		return history;
	}

	public void setHistory(List<ApprovalHistoryDTO> history) {
		this.history = history;
	}

}
