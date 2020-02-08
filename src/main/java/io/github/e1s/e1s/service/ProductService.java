package io.github.e1s.e1s.service;


import io.github.e1s.e1s.controllers.dtos.ProductDTO;

import java.util.List;

public interface ProductService {

    List<ProductDTO> findAllProducts();

    ProductDTO findProductById(Long id);
}
