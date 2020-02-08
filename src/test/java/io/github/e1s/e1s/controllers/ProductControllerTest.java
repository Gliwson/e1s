package io.github.e1s.e1s.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnProducts() throws Exception {
        this.mockMvc.perform(get("/products")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    public void shouldReturnOneProduct() throws Exception {
        this.mockMvc.perform(get("/product/1")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType("application/json"));
    }

    @Test
    public void shouldReturnNotFound() throws Exception {
        this.mockMvc.perform(get("/product/10")).andDo(print()).andExpect(status().isNotFound())
                .andExpect(content().contentType("text/plain;charset=UTF-8"));
    }
}
