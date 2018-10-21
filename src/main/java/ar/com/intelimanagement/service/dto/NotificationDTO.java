package ar.com.intelimanagement.service.dto;

import java.time.Instant;
import java.io.Serializable;
import java.sql.Blob;
import java.util.Objects;

/**
 * A DTO for the Notification entity.
 */
public class NotificationDTO implements Serializable {

    private Long id;

    private String type;

    private byte[] detail;
    
    private String idReference;

    private Instant creationDate;

    private Instant stastDate;

    private Instant endDate;
    
    private Boolean view;

    private UserDTO userCreation;
    

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

    public Boolean isView() {
        return view;
    }

    public void setView(Boolean view) {
        this.view = view;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        NotificationDTO notificationDTO = (NotificationDTO) o;
        if (notificationDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), notificationDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "NotificationDTO{" +
            "id=" + getId() +
            ", type='" + getType() + "'" +
            ", stastDate='" + getStastDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", view='" + isView() + "'" +
            "}";
    }


	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public byte[] getDetail() {
		return detail;
	}

	public void setDetail(byte[] detail) {
		this.detail = detail;
	}

	public Instant getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Instant creationDate) {
		this.creationDate = creationDate;
	}

	public String getIdReference() {
		return idReference;
	}

	public void setIdReference(String idReference) {
		this.idReference = idReference;
	}

	public UserDTO getUserCreation() {
		return userCreation;
	}

	public void setUserCreation(UserDTO userCreation) {
		this.userCreation = userCreation;
	}
}
