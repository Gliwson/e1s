package io.github.e1s.components.product;

import io.github.e1s.components.discount.DiscountService;
import io.github.e1s.components.views.ViewsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/")
public class ProductController {

    private ProductService productService;
    private DiscountService discountService;
    private ViewsService viewsService;

    public ProductController(ProductService productService, DiscountService discountService, ViewsService viewsService) {
        this.productService = productService;
        this.discountService = discountService;
        this.viewsService = viewsService;
    }

    @GetMapping("/products")
    public List<ProductDTO> findAllProductsWithDiscount() {
        List<ProductDTO> allProducts = productService.findAllProducts();
        allProducts.forEach(product -> viewsService.increaseViews(product.getId()));
        return allProducts.stream()
                .map(productDTO -> discountService.addDiscount(productDTO))
                .collect(Collectors.toList());
    }

    @GetMapping("/product/{id}")
    public ProductDTO findProductByIdWithDiscount(@PathVariable Long id) {
        ProductDTO productDTO = productService.findProductById(id);
        viewsService.increaseViews(productDTO.getId());
        return discountService.addDiscount(productDTO);

    }
}
