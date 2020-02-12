package io.github.e1s.components.discount;

import io.github.e1s.components.product.ProductDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DiscountServiceImpl implements DiscountService {

    private DiscountRepository discountRepository;

    public DiscountServiceImpl(DiscountRepository discountRepository) {
        this.discountRepository = discountRepository;
    }

    @Override
    @Transactional
    public ProductDTO addDiscount(ProductDTO productDTO) {
        Long percent = discountRepository.findByType(productDTO.getTypeMaleFemaleKid())
                .map(Discount::getPercent)
                .orElseThrow(() -> new NotFoundDiscountException(productDTO.getTypeMaleFemaleKid().toString()));
        return DiscountCalculate.countDiscount(productDTO, percent);
    }
}
