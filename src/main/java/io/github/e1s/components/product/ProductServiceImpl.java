package io.github.e1s.components.product;

import io.github.e1s.components.discount.DiscountService;
import io.github.e1s.components.product.errors.IdIsNullException;
import io.github.e1s.components.product.errors.ProductNotFoundException;
import io.github.e1s.components.views.ViewsService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private DiscountService discountService;
    private ViewsService viewsService;

    public ProductServiceImpl(ProductRepository productRepository,
                              @Qualifier(value = "discountScopeServiceImpl") DiscountService discountService,
                              ViewsService viewsService) {
        this.productRepository = productRepository;
        this.discountService = discountService;
        this.viewsService = viewsService;
    }

    @Override
    @Transactional
    public ProductDTO findProductById(Long id) {
        if (id == null) {
            throw new IdIsNullException();
        }
        ProductDTO productDTO = productRepository.findById(id)
                .map(ProductMapper::productToProductDto)
                .orElseThrow(() -> new ProductNotFoundException(id));
        ProductDTO productWithDiscount = discountService.addDiscount(productDTO);
        viewsService.increaseViews(productDTO.getId());

        return productWithDiscount;
    }

}
