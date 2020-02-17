package io.github.e1s.components.discount.type;

import io.github.e1s.components.discount.errors.NotFoundDiscountException;
import io.github.e1s.components.product.ProductDTO;
import io.github.e1s.components.product.ProductType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class DiscountTypeServiceImplTest {

    @InjectMocks
    private DiscountTypeServiceImpl discountService;

    @Mock
    private DiscountTypeRepository discountTypeRepository;

    private ProductDTO productDTO;
    private DiscountType discountZeroPercent;
    private DiscountType discountFivePercent;
    private DiscountType discountMinusFivePercent;

    @BeforeEach
    public void init() {
        discountZeroPercent = new DiscountType();
        discountZeroPercent.setId(1L);
        discountZeroPercent.setPercent(0L);
        discountZeroPercent.setType(ProductType.MALE);

        discountFivePercent = new DiscountType();
        discountFivePercent.setId(1L);
        discountFivePercent.setPercent(5L);
        discountFivePercent.setType(ProductType.MALE);

        discountMinusFivePercent = new DiscountType();
        discountMinusFivePercent.setId(1L);
        discountMinusFivePercent.setPercent(-5L);
        discountMinusFivePercent.setType(ProductType.MALE);

        productDTO = new ProductDTO();
        productDTO.setId(1L);
        productDTO.setName("Pants");
        productDTO.setDescription("Used to wear");
        productDTO.setPrice(new BigDecimal("100"));
        productDTO.setProductType(ProductType.MALE);
    }

    @Test
    void ifNotFoundTypeProductShouldThrowException() {
        //given
        //when
        //then
        assertThrows(NotFoundDiscountException.class, () -> discountService.addDiscount(productDTO));
        then(discountTypeRepository).should().findByType(any());
    }

    @Test
    void shouldBeAbleReturnSameValue() {
        //given
        given(discountTypeRepository.findByType(discountZeroPercent.getType()))
                .willReturn(Optional.ofNullable(discountZeroPercent));
        //when
        ProductDTO result = discountService.addDiscount(this.productDTO);
        //then
        then(discountTypeRepository).should().findByType(any());
        assertThat(result.getPrice(), is(new BigDecimal("100")));

    }

    @Test
    void shouldBeAbleReturnFivePercentLess() {
        //given
        given(discountTypeRepository.findByType(discountFivePercent.getType()))
                .willReturn(Optional.ofNullable(discountFivePercent));
        //when
        ProductDTO result = discountService.addDiscount(this.productDTO);
        //then
        then(discountTypeRepository).should().findByType(any());
        assertThat(result.getPrice(), is(new BigDecimal("95.00")));

    }

    @Test
    void shouldBeAbleReturnFivePercentMore() {
        //given
        given(discountTypeRepository.findByType(discountMinusFivePercent.getType()))
                .willReturn(Optional.ofNullable(discountMinusFivePercent));
        //when
        ProductDTO result = discountService.addDiscount(this.productDTO);
        //then
        then(discountTypeRepository).should().findByType(any());
        assertThat(result.getPrice(), is(new BigDecimal("105.00")));

    }
}
