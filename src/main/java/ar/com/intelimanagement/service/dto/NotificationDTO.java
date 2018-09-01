package ar.com.intelimanagement.service.dto;

import java.time.Instant;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Notification entity.
 */
public class NotificationDTO implements Serializable {

    private Long id;

    private String name;

    private Instant stastDate;

    private Instant endDate;

    private Boolean view;

    private Long employeeId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
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
            ", name='" + getName() + "'" +
            ", stastDate='" + getStastDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", view='" + isView() + "'" +
            ", employee=" + getEmployeeId() +
            "}";
    }
}
