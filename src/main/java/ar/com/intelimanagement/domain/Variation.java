package ar.com.intelimanagement.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Variation.
 */
@Entity
@Table(name = "variation")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Variation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "extra_charge")
    private Double extra_charge;

    @Column(name = "new_charge")
    private Double new_charge;

    @Column(name = "new_cost")
    private Double new_cost;

    @Column(name = "new_benefit")
    private Double new_benefit;

    @Column(name = "new_external_locator_id")
    private Integer new_external_locator_id;

    @Column(name = "comments")
    private String comments;

    @NotNull
    @Column(name = "creation_date", nullable = false)
    private ZonedDateTime creation_date;

    @NotNull
    @Column(name = "creation_user", nullable = false)
    private String creation_user;

    @NotNull
    @Column(name = "provider", nullable = false)
    private String provider;

    @NotNull
    @Column(name = "product", nullable = false)
    private String product;

    @NotNull
    @Column(name = "area", nullable = false)
    private String area;

    @NotNull
    @Column(name = "campaing", nullable = false)
    private String campaing;

    @NotNull
    @Column(name = "reason", nullable = false)
    private String reason;

    @Column(name = "jhi_recoverable")
    private Boolean recoverable;

    @Column(name = "refund_in_points")
    private Integer refund_in_points;

    @Column(name = "refund_in_cash")
    private Double refund_in_cash;

    @Column(name = "cacel")
    private Boolean cacel;

    @OneToOne(optional = false)
    @NotNull
    @JoinColumn(unique = true)
    private User relationship_user_variation;

    @OneToMany(mappedBy = "variation")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Provider> relationship_provider_variations = new HashSet<>();

    @OneToMany(mappedBy = "variation")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Product> relationship_product_variations = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getExtra_charge() {
        return extra_charge;
    }

    public Variation extra_charge(Double extra_charge) {
        this.extra_charge = extra_charge;
        return this;
    }

    public void setExtra_charge(Double extra_charge) {
        this.extra_charge = extra_charge;
    }

    public Double getNew_charge() {
        return new_charge;
    }

    public Variation new_charge(Double new_charge) {
        this.new_charge = new_charge;
        return this;
    }

    public void setNew_charge(Double new_charge) {
        this.new_charge = new_charge;
    }

    public Double getNew_cost() {
        return new_cost;
    }

    public Variation new_cost(Double new_cost) {
        this.new_cost = new_cost;
        return this;
    }

    public void setNew_cost(Double new_cost) {
        this.new_cost = new_cost;
    }

    public Double getNew_benefit() {
        return new_benefit;
    }

    public Variation new_benefit(Double new_benefit) {
        this.new_benefit = new_benefit;
        return this;
    }

    public void setNew_benefit(Double new_benefit) {
        this.new_benefit = new_benefit;
    }

    public Integer getNew_external_locator_id() {
        return new_external_locator_id;
    }

    public Variation new_external_locator_id(Integer new_external_locator_id) {
        this.new_external_locator_id = new_external_locator_id;
        return this;
    }

    public void setNew_external_locator_id(Integer new_external_locator_id) {
        this.new_external_locator_id = new_external_locator_id;
    }

    public String getComments() {
        return comments;
    }

    public Variation comments(String comments) {
        this.comments = comments;
        return this;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public ZonedDateTime getCreation_date() {
        return creation_date;
    }

    public Variation creation_date(ZonedDateTime creation_date) {
        this.creation_date = creation_date;
        return this;
    }

    public void setCreation_date(ZonedDateTime creation_date) {
        this.creation_date = creation_date;
    }

    public String getCreation_user() {
        return creation_user;
    }

    public Variation creation_user(String creation_user) {
        this.creation_user = creation_user;
        return this;
    }

    public void setCreation_user(String creation_user) {
        this.creation_user = creation_user;
    }

    public String getProvider() {
        return provider;
    }

    public Variation provider(String provider) {
        this.provider = provider;
        return this;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getProduct() {
        return product;
    }

    public Variation product(String product) {
        this.product = product;
        return this;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getArea() {
        return area;
    }

    public Variation area(String area) {
        this.area = area;
        return this;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCampaing() {
        return campaing;
    }

    public Variation campaing(String campaing) {
        this.campaing = campaing;
        return this;
    }

    public void setCampaing(String campaing) {
        this.campaing = campaing;
    }

    public String getReason() {
        return reason;
    }

    public Variation reason(String reason) {
        this.reason = reason;
        return this;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Boolean isRecoverable() {
        return recoverable;
    }

    public Variation recoverable(Boolean recoverable) {
        this.recoverable = recoverable;
        return this;
    }

    public void setRecoverable(Boolean recoverable) {
        this.recoverable = recoverable;
    }

    public Integer getRefund_in_points() {
        return refund_in_points;
    }

    public Variation refund_in_points(Integer refund_in_points) {
        this.refund_in_points = refund_in_points;
        return this;
    }

    public void setRefund_in_points(Integer refund_in_points) {
        this.refund_in_points = refund_in_points;
    }

    public Double getRefund_in_cash() {
        return refund_in_cash;
    }

    public Variation refund_in_cash(Double refund_in_cash) {
        this.refund_in_cash = refund_in_cash;
        return this;
    }

    public void setRefund_in_cash(Double refund_in_cash) {
        this.refund_in_cash = refund_in_cash;
    }

    public Boolean isCacel() {
        return cacel;
    }

    public Variation cacel(Boolean cacel) {
        this.cacel = cacel;
        return this;
    }

    public void setCacel(Boolean cacel) {
        this.cacel = cacel;
    }

    public User getRelationship_user_variation() {
        return relationship_user_variation;
    }

    public Variation relationship_user_variation(User user) {
        this.relationship_user_variation = user;
        return this;
    }

    public void setRelationship_user_variation(User user) {
        this.relationship_user_variation = user;
    }

    public Set<Provider> getRelationship_provider_variations() {
        return relationship_provider_variations;
    }

    public Variation relationship_provider_variations(Set<Provider> providers) {
        this.relationship_provider_variations = providers;
        return this;
    }

    public Variation addRelationship_provider_variation(Provider provider) {
        this.relationship_provider_variations.add(provider);
        provider.setVariation(this);
        return this;
    }

    public Variation removeRelationship_provider_variation(Provider provider) {
        this.relationship_provider_variations.remove(provider);
        provider.setVariation(null);
        return this;
    }

    public void setRelationship_provider_variations(Set<Provider> providers) {
        this.relationship_provider_variations = providers;
    }

    public Set<Product> getRelationship_product_variations() {
        return relationship_product_variations;
    }

    public Variation relationship_product_variations(Set<Product> products) {
        this.relationship_product_variations = products;
        return this;
    }

    public Variation addRelationship_product_variation(Product product) {
        this.relationship_product_variations.add(product);
        product.setVariation(this);
        return this;
    }

    public Variation removeRelationship_product_variation(Product product) {
        this.relationship_product_variations.remove(product);
        product.setVariation(null);
        return this;
    }

    public void setRelationship_product_variations(Set<Product> products) {
        this.relationship_product_variations = products;
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
        Variation variation = (Variation) o;
        if (variation.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), variation.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Variation{" +
            "id=" + getId() +
            ", extra_charge=" + getExtra_charge() +
            ", new_charge=" + getNew_charge() +
            ", new_cost=" + getNew_cost() +
            ", new_benefit=" + getNew_benefit() +
            ", new_external_locator_id=" + getNew_external_locator_id() +
            ", comments='" + getComments() + "'" +
            ", creation_date='" + getCreation_date() + "'" +
            ", creation_user='" + getCreation_user() + "'" +
            ", provider='" + getProvider() + "'" +
            ", product='" + getProduct() + "'" +
            ", area='" + getArea() + "'" +
            ", campaing='" + getCampaing() + "'" +
            ", reason='" + getReason() + "'" +
            ", recoverable='" + isRecoverable() + "'" +
            ", refund_in_points=" + getRefund_in_points() +
            ", refund_in_cash=" + getRefund_in_cash() +
            ", cacel='" + isCacel() + "'" +
            "}";
    }
}
