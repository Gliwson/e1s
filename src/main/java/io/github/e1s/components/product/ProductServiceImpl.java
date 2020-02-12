package io.github.e1s.components.product;

import io.github.e1s.components.views.ViewsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private ViewsService viewsService;

    public ProductServiceImpl(ProductRepository productRepository, ViewsService viewsService) {
        this.productRepository = productRepository;
        this.viewsService = viewsService;
    }

    @Override
    @Transactional
    public List<ProductDTO> findAllProducts() {
        List<Product> products = productRepository.findAll();
        products.forEach(product -> viewsService.increaseViews(product.getViews().getId()));

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
            viewsService.increaseViews(product.getViews().getId());
            return ProductMapper.productToProductDto(product);
        }).orElseThrow(() -> new ProductNotFoundException(id));
    }

}
