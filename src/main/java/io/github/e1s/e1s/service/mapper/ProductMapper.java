package io.github.e1s.e1s.service.mapper;

import io.github.e1s.e1s.controllers.dtos.ProductDTO;
import io.github.e1s.e1s.domain.Product;

public class ProductMapper {

    public static ProductDTO productToProductDto(Product product) {
        ProductDTO productDTO = new ProductDTO();
        if (product == null) {
            return null;
        } else {
            productDTO.setId(product.getId());
            productDTO.setName(product.getName());
            productDTO.setDescription(product.getDescription());
            productDTO.setPrice(product.getPrice());
            productDTO.setTypeMaleFemaleKid(product.getTypeMaleFemaleKid());
            productDTO.setViews(product.getViews());
        }
        return productDTO;
    }
}
