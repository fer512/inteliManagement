package ar.com.intelimanagement.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Product.
 */
@Entity
@Table(name = "product")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "code", nullable = false)
    private String code;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @NotNull
    @JoinTable(name = "product_product_by_provider",
               joinColumns = @JoinColumn(name = "products_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "product_by_providers_id", referencedColumnName = "id"))
    private Set<Provider> product_by_providers = new HashSet<>();

    @OneToMany(mappedBy = "product")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ProductByBooking> bookings = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Product name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public Product code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Set<Provider> getProduct_by_providers() {
        return product_by_providers;
    }

    public Product product_by_providers(Set<Provider> providers) {
        this.product_by_providers = providers;
        return this;
    }

    public Product addProduct_by_provider(Provider provider) {
        this.product_by_providers.add(provider);
        return this;
    }

    public Product removeProduct_by_provider(Provider provider) {
        this.product_by_providers.remove(provider);
        return this;
    }

    public void setProduct_by_providers(Set<Provider> providers) {
        this.product_by_providers = providers;
    }

    public Set<ProductByBooking> getBookings() {
        return bookings;
    }

    public Product bookings(Set<ProductByBooking> productByBookings) {
        this.bookings = productByBookings;
        return this;
    }

    public Product addBookings(ProductByBooking productByBooking) {
        this.bookings.add(productByBooking);
        productByBooking.setProduct(this);
        return this;
    }

    public Product removeBookings(ProductByBooking productByBooking) {
        this.bookings.remove(productByBooking);
        productByBooking.setProduct(null);
        return this;
    }

    public void setBookings(Set<ProductByBooking> productByBookings) {
        this.bookings = productByBookings;
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
        Product product = (Product) o;
        if (product.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), product.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Product{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", code='" + getCode() + "'" +
            "}";
    }
}
