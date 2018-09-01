package ar.com.intelimanagement.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

import ar.com.intelimanagement.domain.enumeration.PhoneType;

/**
 * A Phone.
 */
@Entity
@Table(name = "phone")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Phone implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "jhi_type")
    private PhoneType type;

    @Column(name = "numpber")
    private String numpber;

    @ManyToOne
    @JsonIgnoreProperties("phones")
    private Employee employee;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PhoneType getType() {
        return type;
    }

    public Phone type(PhoneType type) {
        this.type = type;
        return this;
    }

    public void setType(PhoneType type) {
        this.type = type;
    }

    public String getNumpber() {
        return numpber;
    }

    public Phone numpber(String numpber) {
        this.numpber = numpber;
        return this;
    }

    public void setNumpber(String numpber) {
        this.numpber = numpber;
    }

    public Employee getEmployee() {
        return employee;
    }

    public Phone employee(Employee employee) {
        this.employee = employee;
        return this;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
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
        Phone phone = (Phone) o;
        if (phone.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), phone.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Phone{" +
            "id=" + getId() +
            ", type='" + getType() + "'" +
            ", numpber='" + getNumpber() + "'" +
            "}";
    }
}
