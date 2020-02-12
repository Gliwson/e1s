package io.github.e1s.components.product.service.implementation;

import io.github.e1s.components.discount.Discount;
import io.github.e1s.components.discount.DiscountRepository;
import io.github.e1s.components.discount.DiscountServiceImpl;
import io.github.e1s.components.discount.NotFoundDiscountException;
import io.github.e1s.components.product.controllers.dtos.ProductDTO;
import io.github.e1s.components.product.domain.enums.TypeMaleFemaleKid;
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
class DiscountServiceImplTest {

    @InjectMocks
    private DiscountServiceImpl discountService;

    @Mock
    private DiscountRepository discountRepository;

    private ProductDTO productDTO;
    private Discount discountZeroPercent;
    private Discount discountFivePercent;
    private Discount discountMinusFivePercent;

    @BeforeEach
    public void init() {
        discountZeroPercent = new Discount();
        discountZeroPercent.setId(1L);
        discountZeroPercent.setPercent(0L);
        discountZeroPercent.setType(TypeMaleFemaleKid.MALE);

        discountFivePercent = new Discount();
        discountFivePercent.setId(1L);
        discountFivePercent.setPercent(5L);
        discountFivePercent.setType(TypeMaleFemaleKid.MALE);

        discountMinusFivePercent = new Discount();
        discountMinusFivePercent.setId(1L);
        discountMinusFivePercent.setPercent(-5L);
        discountMinusFivePercent.setType(TypeMaleFemaleKid.MALE);

        productDTO = new ProductDTO();
        productDTO.setId(1L);
        productDTO.setName("Pants");
        productDTO.setDescription("Used to wear");
        productDTO.setPrice(new BigDecimal("100"));
        productDTO.setTypeMaleFemaleKid(TypeMaleFemaleKid.MALE);
        productDTO.setViews(10L);
    }

    @Test
    void ifNotFoundTypeProductShouldThrowException() {
        //given
        //when
        //then
        assertThrows(NotFoundDiscountException.class, () -> discountService.addDiscount(productDTO));
        then(discountRepository).should().findByType(any());
    }

    @Test
    void shouldBeAbleReturnSameValue() {
        //given
        given(discountRepository.findByType(discountZeroPercent.getType()))
                .willReturn(Optional.ofNullable(discountZeroPercent));
        //when
        ProductDTO result = discountService.addDiscount(this.productDTO);
        //then
        then(discountRepository).should().findByType(any());
        assertThat(result.getPrice(), is(new BigDecimal("100")));

    }

    @Test
    void shouldBeAbleReturnFivePercentLess() {
        //given
        given(discountRepository.findByType(discountFivePercent.getType()))
                .willReturn(Optional.ofNullable(discountFivePercent));
        //when
        ProductDTO result = discountService.addDiscount(this.productDTO);
        //then
        then(discountRepository).should().findByType(any());
        assertThat(result.getPrice(), is(new BigDecimal("95.00")));

    }

    @Test
    void shouldBeAbleReturnFivePercentMore() {
        //given
        given(discountRepository.findByType(discountMinusFivePercent.getType()))
                .willReturn(Optional.ofNullable(discountMinusFivePercent));
        //when
        ProductDTO result = discountService.addDiscount(this.productDTO);
        //then
        then(discountRepository).should().findByType(any());
        assertThat(result.getPrice(), is(new BigDecimal("105.00")));

    }
}
