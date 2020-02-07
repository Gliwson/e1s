package io.github.e1s.e1s.service.calculators;


import io.github.e1s.e1s.domain.Product;

public class DiscountCalculate {

    public static Product increaseVisitByOne(Product product) {
        product.setViews(product.getViews() + 1L);
        return product;
    }
}
