package io.github.e1s.components.product;


import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    List<ProductDTO> findAllProducts(Pageable pageable);

    ProductDTO findProductById(Long id);
}
