package io.github.e1s.e1s.service.implementation;

import io.github.e1s.e1s.controllers.dtos.ProductDTO;
import io.github.e1s.e1s.controllers.errors.NotFoundDiscountException;
import io.github.e1s.e1s.domain.Discount;
import io.github.e1s.e1s.repository.DiscountRepository;
import io.github.e1s.e1s.service.DiscountService;
import io.github.e1s.e1s.service.calculators.DiscountCalculate;
import org.springframework.stereotype.Service;

@Service
public class DiscountServiceImpl implements DiscountService {

    private DiscountRepository discountRepository;

    public DiscountServiceImpl(DiscountRepository discountRepository) {
        this.discountRepository = discountRepository;
    }

    @Override
    public ProductDTO addDiscount(ProductDTO productDTO) {
        Long percent = discountRepository.findByType(productDTO.getTypeMaleFemaleKid())
                .map(Discount::getPercent)
                .orElseThrow(() -> new NotFoundDiscountException(productDTO.getTypeMaleFemaleKid().toString()));
        return DiscountCalculate.countDiscount(productDTO, percent);
    }
}
