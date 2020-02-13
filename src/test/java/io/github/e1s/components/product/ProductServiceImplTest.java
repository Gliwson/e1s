package io.github.e1s.components.product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
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
    private Pageable pageableMock;

    private Product product;


    @BeforeEach
    public void init() {
        product = new Product();
        product.setId(1L);
        product.setName("Pants");
        product.setDescription("Used to wear");
        product.setPrice(new BigDecimal("100"));
        product.setProductType(ProductType.MALE);
        product.setDiscount(null);

    }

    @Test
    void theFindAllProductMethodShouldReturnAllProducts() {
//        given
        Pageable pageable = pageableMock.first();
        given(productRepository.findAll(pageable)).willReturn(new PageImpl<>(Collections.singletonList(product)));
        //when
        List<ProductDTO> result = productService.findAllProducts(any());
        //then
        then(productRepository).should().findAll(pageable);
        assertThat(result, hasSize(1));
        assertThat(result.get(0).getName(), equalTo("Pants"));
        assertThat(result.get(0).getPrice(), is(new BigDecimal(100)));
        assertThat(result.get(0).getDescription(), equalTo("Used to wear"));
    }

    @Test
    void shouldFindProductById() {
        //given
        given(productRepository.findById(1L)).willReturn(Optional.of(product));
        //when
        ProductDTO result = productService.findProductById(1L);
        //then
        then(productRepository).should().findById(1L);
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
    }

    @Test
    void ifIdIsNullShouldReturnNull() {
        //given
        //when
        ProductDTO result = productService.findProductById(null);
        //then
        then(productRepository).should(never()).findById(any());
        assertNull(result);

    }
}
