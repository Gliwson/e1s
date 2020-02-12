package io.github.e1s.components.product;

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
    public List<ProductDTO> findAllProducts() {
        List<Product> products = productRepository.findAll();
        products.forEach(product -> productRepository.findByIdAndIncreaseViewsByOne(product.getId()));

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
        return productRepository.findById(id).map(product -> {
            productRepository.findByIdAndIncreaseViewsByOne(product.getId());
            return ProductMapper.productToProductDto(product);
        }).orElseThrow(() -> new ProductNotFoundException(id));
    }

}
