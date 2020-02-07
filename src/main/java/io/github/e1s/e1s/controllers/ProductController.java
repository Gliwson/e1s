package io.github.e1s.e1s.controllers;

import io.github.e1s.e1s.controllers.dtos.ProductDTO;
import io.github.e1s.e1s.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public List<ProductDTO> findAllProducts() {
        return productService.findAllProducts();
    }


}
