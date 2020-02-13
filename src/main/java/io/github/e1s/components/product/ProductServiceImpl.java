package io.github.e1s.components.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public List<ProductDTO> findAllProducts(Pageable pageable) {
        Page<Product> products = productRepository.findAll(pageable);
        return products.stream()
                .map(ProductMapper::productToProductDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ProductDTO findProductById(Long id) {
        if (id == null) {
            return null;
        }
        return productRepository.findById(id)
                .map(ProductMapper::productToProductDto)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

}
