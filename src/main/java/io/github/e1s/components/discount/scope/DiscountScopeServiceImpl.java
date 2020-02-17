package io.github.e1s.components.discount.scope;

import io.github.e1s.components.discount.DiscountService;
import io.github.e1s.components.product.ProductDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DiscountScopeServiceImpl implements DiscountService {

    private DiscountScopeRepository discountTypeRepository;

    public DiscountScopeServiceImpl(DiscountScopeRepository discountTypeRepository) {
        this.discountTypeRepository = discountTypeRepository;
    }

    @Override
    @Transactional
    public ProductDTO addDiscount(ProductDTO productDTO) {

        List<DiscountScope> discountScopeList = discountTypeRepository.findAll();
        System.out.println("wykonało się scope");
        return productDTO;
    }
}
