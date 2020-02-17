package io.github.e1s.components.discount.type;

import io.github.e1s.components.discount.Discount;
import io.github.e1s.components.discount.DiscountCalculate;
import io.github.e1s.components.discount.DiscountService;
import io.github.e1s.components.discount.errors.NotFoundDiscountException;
import io.github.e1s.components.product.ProductDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DiscountTypeServiceImpl implements DiscountService {

    private DiscountTypeRepository discountTypeRepository;

    public DiscountTypeServiceImpl(DiscountTypeRepository discountTypeRepository) {
        this.discountTypeRepository = discountTypeRepository;
    }

    @Override
    @Transactional
    public ProductDTO addDiscount(ProductDTO productDTO) {
        System.out.println("wykonało się type");
        Long percent = discountTypeRepository.findByType(productDTO.getProductType())
                .map(Discount::getPercent)
                .orElseThrow(() -> new NotFoundDiscountException(productDTO.getProductType().toString()));
        return DiscountCalculate.countDiscount(productDTO, percent);
    }
}
