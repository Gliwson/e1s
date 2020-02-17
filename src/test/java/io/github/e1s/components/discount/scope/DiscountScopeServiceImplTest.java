package io.github.e1s.components.discount.scope;

import io.github.e1s.components.product.ProductDTO;
import io.github.e1s.components.product.ProductType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class DiscountScopeServiceImplTest {

    @InjectMocks
    private DiscountScopeServiceImpl discountScopeService;

    @Mock
    private DiscountScopeRepository discountScopeRepository;

    private ProductDTO pants;
    private ProductDTO phone;
    private ProductDTO shirt;
    private DiscountScope discountThreePercent;
    private DiscountScope discountFivePercent;
    private List<DiscountScope> scopeList = new ArrayList<>();

    @BeforeEach
    public void init() {
        discountThreePercent = new DiscountScope();
        discountThreePercent.setId(1L);
        discountThreePercent.setPercent(3L);
        discountThreePercent.setFirsNumber(new BigDecimal("500"));
        discountThreePercent.setSecondNumber(new BigDecimal("2000"));

        discountFivePercent = new DiscountScope();
        discountFivePercent.setId(2L);
        discountFivePercent.setPercent(5L);
        discountFivePercent.setFirsNumber(new BigDecimal("1999.99"));
        discountFivePercent.setSecondNumber(null);

        scopeList.add(discountThreePercent);
        scopeList.add(discountFivePercent);

        pants = new ProductDTO();
        pants.setId(1L);
        pants.setName("Pants");
        pants.setDescription("Used to wear");
        pants.setPrice(new BigDecimal(499));
        pants.setProductType(ProductType.MALE);

        phone = new ProductDTO();
        phone.setId(2L);
        phone.setName("Phone");
        phone.setDescription("Used to wear");
        phone.setPrice(new BigDecimal("1999.99"));
        phone.setProductType(ProductType.MALE);

        shirt = new ProductDTO();
        shirt.setId(3L);
        shirt.setName("Shirt");
        shirt.setDescription("Used to wear");
        shirt.setPrice(new BigDecimal("2000"));
        shirt.setProductType(ProductType.MALE);
    }

    @Test
    void shouldBeAbleReturnSameValue() {
        //given
        given(discountScopeRepository.findAll()).willReturn(scopeList);
        //when
        ProductDTO result = discountScopeService.addDiscount(pants);
        //then
        then(discountScopeRepository).should().findAll();
        assertThat(result.getPrice(), is(new BigDecimal("499")));

    }

    @Test
    void shouldBeAbleReturnFivePercentLess() {
        //given
        given(discountScopeRepository.findAll()).willReturn(scopeList);
        //when
        ProductDTO result = discountScopeService.addDiscount(phone);
        //then
        then(discountScopeRepository).should().findAll();
        assertThat(result.getPrice(), is(new BigDecimal("1939.99")));
    }

    @Test
    void shouldBeAbleReturnThreePercentLess() {
        //given
        given(discountScopeRepository.findAll()).willReturn(scopeList);
        //when
        ProductDTO result = discountScopeService.addDiscount(shirt);
        //then
        then(discountScopeRepository).should().findAll();
        assertThat(result.getPrice(), is(new BigDecimal("1900.00")));
    }
}
