package io.github.e1s.components.discount;


import io.github.e1s.components.product.ProductDTO;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DiscountCalculate {

    public static ProductDTO countDiscount(ProductDTO productDTO, Long percent) {
        BigDecimal price = productDTO.getPrice();
        if (percent == 0) {
            return productDTO;
        } else if (price.signum() < 0) {
            price = price.negate();
            BigDecimal amountDiscount = price.multiply(BigDecimal.valueOf((double) percent / 100));
            BigDecimal roundAmountDiscount = amountDiscount.setScale(2, RoundingMode.HALF_UP);
            productDTO.setPrice(price.add(roundAmountDiscount));
        } else {
            BigDecimal amountDiscount = price.multiply(BigDecimal.valueOf((double) percent / 100));
            BigDecimal roundAmountDiscount = amountDiscount.setScale(2, RoundingMode.HALF_UP);
            productDTO.setPrice(price.subtract(roundAmountDiscount));
        }
        return productDTO;
    }
}
