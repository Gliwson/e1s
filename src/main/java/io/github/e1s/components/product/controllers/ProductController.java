package io.github.e1s.components.product.controllers;

import io.github.e1s.components.discount.DiscountService;
import io.github.e1s.components.product.controllers.dtos.ProductDTO;
import io.github.e1s.components.product.service.ProductService;
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

    public ProductController(ProductService productService, DiscountService discountService) {
        this.productService = productService;
        this.discountService = discountService;
    }

    @GetMapping("/products")
    public List<ProductDTO> findAllProductsWithDiscount() {
        List<ProductDTO> allProducts = productService.findAllProducts();
        return allProducts.stream()
                .map(productDTO -> discountService.addDiscount(productDTO))
                .collect(Collectors.toList());
    }

    @GetMapping("/product/{id}")
    public ProductDTO findProductByIdWithDiscount(@PathVariable Long id) {
        ProductDTO productDTO = productService.findProductById(id);
        return discountService.addDiscount(productDTO);

    }
}
