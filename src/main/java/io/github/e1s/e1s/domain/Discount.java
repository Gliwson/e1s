package io.github.e1s.e1s.domain;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "discount")
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "male", nullable = false)
    private Long male;

    @NotNull
    @Column(name = "female", nullable = false)
    private Long female;

    @NotNull
    @Column(name = "kid", nullable = false)
    private Long kid;

    //Problem n + 1 queries in Hibernate
    @LazyCollection(value = LazyCollectionOption.EXTRA)
    @OneToMany(mappedBy = "discount")
    private Set<Product> products = new HashSet<>();

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMale() {
        return male;
    }

    public void setMale(Long male) {
        this.male = male;
    }

    public Long getFemale() {
        return female;
    }

    public void setFemale(Long female) {
        this.female = female;
    }

    public Long getKid() {
        return kid;
    }

    public void setKid(Long kid) {
        this.kid = kid;
    }

    @Override
    public String toString() {
        return "Discount{" +
                "id=" + id +
                ", male=" + male +
                ", female=" + female +
                ", kid=" + kid +
                '}';
    }
}
