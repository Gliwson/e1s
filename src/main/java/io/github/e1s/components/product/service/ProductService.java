package io.github.e1s.components.product.service;


import io.github.e1s.components.product.controllers.dtos.ProductDTO;

import java.util.List;

public interface ProductService {

    List<ProductDTO> findAllProducts();

    ProductDTO findProductById(Long id);
}
