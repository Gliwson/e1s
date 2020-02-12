package io.github.e1s.components.discount;

import io.github.e1s.components.product.controllers.dtos.ProductDTO;

public interface DiscountService {

    ProductDTO addDiscount(ProductDTO productDTO);
}
