package com.project.springapistudy.domains.product.ui;

import com.project.springapistudy.domains.product.application.ProductService;
import com.project.springapistudy.domains.product.application.request.ProductCreateRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.project.springapistudy.domains.product.domain.ProductType.COFFEE;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProductService productService;

    @DisplayName("신규 상품을 등록한다.")
    @Test
    void createProduct() throws Exception {
        // given
        ProductCreateRequest request = ProductCreateRequest.builder()
            .type(COFFEE)
            .name("아메리카노")
            .price(4000)
            .build();

        String json = objectMapper.writeValueAsString(request);

        // expected
        mockMvc.perform(
                post("/products")
                    .contentType(APPLICATION_JSON)
                    .content(json)
            )
            .andDo(print())
            .andExpect(status().isCreated());
    }

    @DisplayName("신규 상품 등록 시 상품 타입은 필수다.")
    @Test
    void createProductWithNoType() throws Exception {
        // given
        ProductCreateRequest request = ProductCreateRequest.builder()
            .name("아메리카노")
            .price(4000)
            .build();

        String json = objectMapper.writeValueAsString(request);

        // when
        mockMvc.perform(
                post("/products")
                    .contentType(APPLICATION_JSON)
                    .content(json)
            )
            .andDo(print())
            .andExpect(status().isBadRequest());

        // then

    }

    @DisplayName("신규 상품 등록 시 상품 이름은 필수다.")
    @Test
    void createProductWithNoName() throws Exception {
        // given
        ProductCreateRequest request = ProductCreateRequest.builder()
            .type(COFFEE)
            .price(4000)
            .build();

        String json = objectMapper.writeValueAsString(request);

        // expected
        mockMvc.perform(
                post("/products")
                    .contentType(APPLICATION_JSON)
                    .content(json)
            )
            .andDo(print())
            .andExpect(status().isBadRequest());
    }

    @DisplayName("신규 상품 등록 시 상품 가격은 필수다.")
    @Test
    void createProductWithNoPrice() throws Exception {
        // given
        ProductCreateRequest request = ProductCreateRequest.builder()
            .type(COFFEE)
            .name("아메리카노")
            .build();

        String json = objectMapper.writeValueAsString(request);

        // expected
        mockMvc.perform(
                post("/products")
                    .contentType(APPLICATION_JSON)
                    .content(json)
            )
            .andDo(print())
            .andExpect(status().isBadRequest());
    }

    @DisplayName("신규 상품 등록 시 상품 가격은 0일 수 없다.")
    @Test
    void createProductWithZeroPrice() throws Exception {
        // given
        ProductCreateRequest request = ProductCreateRequest.builder()
            .type(COFFEE)
            .price(0)
            .name("아메리카노")
            .build();

        String json = objectMapper.writeValueAsString(request);

        // expected
        mockMvc.perform(
                post("/products")
                    .contentType(APPLICATION_JSON)
                    .content(json)
            )
            .andDo(print())
            .andExpect(status().isBadRequest());
    }

    @DisplayName("신규 상품 등록 시 상품 가격은 0보다 큰 양수다.")
    @Test
    void createProductWithMinusPrice() throws Exception {
        // given
        ProductCreateRequest request = ProductCreateRequest.builder()
            .type(COFFEE)
            .price(-1)
            .name("아메리카노")
            .build();

        String json = objectMapper.writeValueAsString(request);

        // expected
        mockMvc.perform(
                post("/products")
                    .contentType(APPLICATION_JSON)
                    .content(json)
            )
            .andDo(print())
            .andExpect(status().isBadRequest());
    }
}