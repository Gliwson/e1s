package io.github.e1s.components.discount.scope;

import io.github.e1s.components.discount.DiscountCalculate;
import io.github.e1s.components.discount.DiscountService;
import io.github.e1s.components.product.ProductDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

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
        ifSecondNumberIsNullSetMaxValue(discountScopeList);
        Optional<DiscountScope> scope = filterToSearchRange(productDTO, discountScopeList);

        return scope.map(discountScope -> DiscountCalculate.countDiscount(productDTO, discountScope.getPercent()))
                .orElse(productDTO);
    }

    private Optional<DiscountScope> filterToSearchRange(ProductDTO productDTO, List<DiscountScope> discountScopeList) {
        return discountScopeList.stream()
                .filter(e -> (e.getFirsNumber().compareTo(productDTO.getPrice()) <= 0
                        && e.getSecondNumber().compareTo(productDTO.getPrice()) > 0)).findFirst();
    }

    private void ifSecondNumberIsNullSetMaxValue(List<DiscountScope> discountScopeList) {
        discountScopeList.stream().filter(d -> d.getSecondNumber() == null)
                .forEach(d -> d.setSecondNumber(BigDecimal.valueOf(Double.MAX_VALUE)));
    }
}
