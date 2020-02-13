package io.github.e1s.components.product;

import io.github.e1s.components.discount.DiscountService;
import io.github.e1s.components.views.ViewsService;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    private ProductService productService;
    private DiscountService discountService;
    private ViewsService viewsService;

    public ProductController(ProductService productService, DiscountService discountService, ViewsService viewsService) {
        this.productService = productService;
        this.discountService = discountService;
        this.viewsService = viewsService;
    }

    @GetMapping("")
    public List<ProductDTO> findAll(@RequestParam(name = "product", required = false) Long id, Pageable pageable) {
        if (id != null) {
            List<ProductDTO> list = new ArrayList<>();

            ProductDTO productDTO = productService.findProductById(id);
            ProductDTO productDTO1 = discountService.addDiscount(productDTO);
            list.add(productDTO1);

            viewsService.increaseViews(productDTO.getId());
            return list;
        } else {
            List<ProductDTO> allProducts = productService.findAllProducts(pageable);
            allProducts.forEach(product -> viewsService.increaseViews(product.getId()));
            return allProducts.stream()
                    .map(productDTO -> discountService.addDiscount(productDTO))
                    .collect(Collectors.toList());
        }
    }


}
