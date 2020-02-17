package io.github.e1s.components.discount.type;

import io.github.e1s.components.discount.Discount;
import io.github.e1s.components.product.ProductType;

import javax.persistence.*;

@Entity
@Table(name = "discount_type")
@PrimaryKeyJoinColumn(name = "type_id")
public class DiscountType extends Discount {

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private ProductType type;

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "DiscountType{" +
                "type=" + type +
                '}';
    }
}
