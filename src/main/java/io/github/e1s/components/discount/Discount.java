package io.github.e1s.components.discount;

import io.github.e1s.components.product.Product;
import io.github.e1s.components.product.TypeMaleFemaleKid;
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

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private TypeMaleFemaleKid type;

    @NotNull
    @Column(name = "percent", nullable = false)
    private Long percent;

    //Problem n + 1 queries in Hibernate
    @LazyCollection(value = LazyCollectionOption.EXTRA)
    @OneToMany(mappedBy = "discount")
    private Set<Product> products = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TypeMaleFemaleKid getType() {
        return type;
    }

    public void setType(TypeMaleFemaleKid type) {
        this.type = type;
    }

    public Long getPercent() {
        return percent;
    }

    public void setPercent(Long percent) {
        this.percent = percent;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
