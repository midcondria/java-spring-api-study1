package com.project.springapistudy.domains.product.application.request;

import com.project.springapistudy.domains.product.domain.Product;
import com.project.springapistudy.domains.product.domain.ProductType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@NoArgsConstructor
public class ProductCreateRequest {

    @NotNull(message = "상품 타입은 필수입니다.")
    private ProductType type;

    @NotBlank(message = "상품 이름은 필수입니다.")
    private String name;

    @Positive(message = "상품 가격은 양수여야합니다.")
    private int price;

    @Builder
    public ProductCreateRequest(ProductType type, String name, int price) {
        this.type = type;
        this.name = name;
        this.price = price;
    }

    public Product toEntity(String nextProductNumber) {
        return Product.builder()
            .productNumber(nextProductNumber)
            .type(type)
            .name(name)
            .price(price)
            .build();
    }
}
