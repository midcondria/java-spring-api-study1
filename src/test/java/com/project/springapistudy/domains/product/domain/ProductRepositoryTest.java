package com.project.springapistudy.domains.product.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static com.project.springapistudy.domains.product.domain.ProductType.*;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @DisplayName("가장 마지막에 저장한 상품의 상품번호를 읽어온다.")
    @Test
    void findLatestProductNumber() {
        // given
        String targetNumber = "003";

        Product product1 = createProduct("001", COFFEE, "아메리카노", 4000);
        Product product2 = createProduct("002", BAKERY, "티라미수", 500);
        Product product3 = createProduct(targetNumber, BEVERAGE, "레모네이드", 4500);
        productRepository.saveAll(List.of(product1, product2, product3));

        // when
        String latestProductNumber = productRepository.findLatestProductNumber();

        // then
        assertThat(latestProductNumber).isEqualTo(targetNumber);
    }

    @DisplayName("등록된 상품이 없을 시 null 을 반환한다.")
    @Test
    void findLatestProductNumberWhenProductIsEmpty() {
        // when
        String latestProductNumber = productRepository.findLatestProductNumber();

        // then
        assertThat(latestProductNumber).isNull();
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