package io.github.e1s.components.discount.scope;

import io.github.e1s.components.discount.Discount;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;

@Entity
@Table(name = "discount_scope")
@DiscriminatorValue("SCOPE")
public class DiscountScope extends Discount {

    @Enumerated(EnumType.STRING)
    @Column(name = "range_mark", nullable = false)
    private RangeMark rangeMark;

    @DecimalMin(value = "0")
    @Column(name = "first_number", precision = 21, scale = 2, nullable = false)
    private BigDecimal firsNumber;

    @DecimalMin(value = "0")
    @Column(name = "second_number", precision = 21, scale = 2)
    private BigDecimal secondNumber;

    public RangeMark getRangeMark() {
        return rangeMark;
    }

    public void setRangeMark(RangeMark rangeMark) {
        this.rangeMark = rangeMark;
    }

    public BigDecimal getFirsNumber() {
        return firsNumber;
    }

    public void setFirsNumber(BigDecimal firsNumber) {
        this.firsNumber = firsNumber;
    }

    public BigDecimal getSecondNumber() {
        return secondNumber;
    }

    public void setSecondNumber(BigDecimal secondNumber) {
        this.secondNumber = secondNumber;
    }

    @Override
    public String toString() {
        return "DiscountScope{" +
                "rangeMark=" + rangeMark +
                ", firsNumber=" + firsNumber +
                ", secondNumber=" + secondNumber +
                '}';
    }
}
