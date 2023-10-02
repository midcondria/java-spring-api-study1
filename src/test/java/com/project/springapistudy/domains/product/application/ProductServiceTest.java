package com.project.springapistudy.domains.product.application;

import com.project.springapistudy.domains.product.application.request.ProductCreateRequest;
import com.project.springapistudy.domains.product.domain.Product;
import com.project.springapistudy.domains.product.domain.ProductRepository;
import com.project.springapistudy.domains.product.domain.ProductType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static com.project.springapistudy.domains.product.domain.ProductType.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ProductServiceTest {

    @Autowired
    ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @AfterEach
    void tearDown() {
        productRepository.deleteAllInBatch();
    }

    @DisplayName("신규 상품 등록 시 상품 번호는 현재까지 등록된 상품 번호에서 +1 한 번호이다.")
    @Test
    void createProduct() {
        // given
        Product product1 = createProduct("001", COFFEE, "아메리카노", 4000);
        Product product2 = createProduct("002", BAKERY, "티라미수", 5000);
        productRepository.saveAll(List.of(product1, product2));

        ProductCreateRequest request = ProductCreateRequest.builder()
            .type(BEVERAGE)
            .name("레모네이드")
            .price(4500)
            .build();

        // when
        String response = productService.createProduct(request);

        // then
        assertThat(response).isEqualTo("003");
    }

    @DisplayName("등록된 상품이 없을 때 신규 상품을 등록하면 상품번호는 001이다.")
    @Test
    void createProductWhenProductIsEmpty() {
        // given
        ProductCreateRequest request = ProductCreateRequest.builder()
            .type(COFFEE)
            .name("아메리카노")
            .price(4000)
            .build();

        // when
        String response = productService.createProduct(request);

        // then
        assertThat(response).isEqualTo("001");
    }

    private static Product createProduct(String productNumber, ProductType type, String name, int price) {
        return Product.builder()
            .productNumber(productNumber)
            .type(type)
            .name(name)
            .price(price)
            .build();
    }
}