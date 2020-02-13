package io.github.e1s.components.views;

import io.github.e1s.components.product.Product;

import javax.persistence.*;

@Entity
@Table(name = "views")
public class Views {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "counter")
    private Long counter;

    @OneToOne
    @MapsId
    private Product product;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCounter() {
        return counter;
    }

    public void setCounter(Long counter) {
        this.counter = counter;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "Views{" +
                "id=" + id +
                ", counter=" + counter +
                ", product=" + product +
                '}';
    }
}
