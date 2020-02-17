package io.github.e1s.components.product;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Disabled
@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnOneProduct() throws Exception {
        this.mockMvc.perform(get("/products").param("product", "1")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value("100.0"))
                .andExpect(content().contentType("application/json"));
    }

    @Test
    public void shouldReturnProductWithDiscountThreePercent() throws Exception {
        this.mockMvc.perform(get("/products").param("product", "4")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value("970.0"))
                .andExpect(content().contentType("application/json"));
    }

    @Test
    public void shouldReturnProductWithDiscountFivePercent() throws Exception {
        this.mockMvc.perform(get("/products").param("product", "6")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value("1995.0"))
                .andExpect(content().contentType("application/json"));
    }

    @Test
    public void shouldReturnNotFound() throws Exception {
        this.mockMvc.perform(get("/products").param("product", "10")).andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().contentType("text/plain;charset=UTF-8"));
    }

    @Test
    public void shouldReturnIdIsNull() throws Exception {
        this.mockMvc.perform(get("/products").param("product", "")).andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("text/plain;charset=UTF-8"));
    }
}
