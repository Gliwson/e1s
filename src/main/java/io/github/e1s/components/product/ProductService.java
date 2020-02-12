package io.github.e1s.components.product;


import java.util.List;

public interface ProductService {

    List<ProductDTO> findAllProducts();

    ProductDTO findProductById(Long id);
}
