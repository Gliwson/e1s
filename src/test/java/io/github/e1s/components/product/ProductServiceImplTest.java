package io.github.e1s.components.product;

import io.github.e1s.components.discount.DiscountService;
import io.github.e1s.components.product.errors.IdIsNullException;
import io.github.e1s.components.product.errors.ProductNotFoundException;
import io.github.e1s.components.views.ViewsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.never;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {


    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private DiscountService discountService;

    @Mock
    private ViewsService viewsService;

    private Product product;

    private ProductDTO productDTO;


    @BeforeEach
    public void init() {
        product = new Product();
        product.setId(1L);
        product.setName("Pants");
        product.setDescription("Used to wear");
        product.setPrice(new BigDecimal("100"));
        product.setProductType(ProductType.MALE);
        product.setDiscount(null);

        productDTO = ProductMapper.productToProductDto(product);

    }

    @Test
    void shouldFindProductById() {
        //given
        given(productRepository.findById(1L)).willReturn(Optional.of(product));
        given(discountService.addDiscount(any(ProductDTO.class))).willReturn(productDTO);
        //when
        ProductDTO result = productService.findProductById(1L);
        //then
        then(productRepository).should().findById(1L);
        then(discountService).should().addDiscount(any());
        then(viewsService).should().increaseViews(anyLong());
        assertThat(result.getName(), equalTo("Pants"));
        assertThat(result.getPrice(), is(new BigDecimal(100)));
        assertThat(result.getDescription(), equalTo("Used to wear"));
    }

    @Test
    void ifIdIsNotFoundShouldThrowException() {
        //given
        //when
        //then
        assertThrows(ProductNotFoundException.class, () -> productService.findProductById(2L));
        then(productRepository).should().findById(2L);
        then(discountService).should(never()).addDiscount(any());
        then(viewsService).should(never()).increaseViews(anyLong());
    }

    @Test
    void ifIdIsNullShouldThrowException() {
        //given
        //when
        //then
        assertThrows(IdIsNullException.class, () -> productService.findProductById(null));
        then(productRepository).should(never()).findById(anyLong());
        then(discountService).should(never()).addDiscount(any());
        then(viewsService).should(never()).increaseViews(anyLong());

    }
}
