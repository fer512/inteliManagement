package ar.com.intelimanagement.service.dto;

import java.io.Serializable;
import java.util.Objects;

import ar.com.intelimanagement.domain.enumeration.PhoneType;

/**
 * A DTO for the Phone entity.
 */
public class PhoneDTO implements Serializable {

    private Long id;

    private PhoneType type;

    private String numpber;

    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PhoneType getType() {
        return type;
    }

    public void setType(PhoneType type) {
        this.type = type;
    }

    public String getNumpber() {
        return numpber;
    }

    public void setNumpber(String numpber) {
        this.numpber = numpber;
    }
  
    public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PhoneDTO phoneDTO = (PhoneDTO) o;
        if (phoneDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), phoneDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PhoneDTO{" +
            "id=" + getId() +
            ", type='" + getType() + "'" +
            ", numpber='" + getNumpber() + "'" +
            ", user=" + getUserId() +
            "}";
    }
}
