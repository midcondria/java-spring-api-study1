package com.project.springapistudy.domains.product.domain;

import com.project.springapistudy.domains.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productNumber;

    @Enumerated(EnumType.STRING)
    private ProductType type;

    private boolean isDeleted = false;

    private String name;

    private int price;

    @Builder
    public Product(String productNumber, ProductType type, boolean isDeleted, String name, int price) {
        this.productNumber = productNumber;
        this.type = type;
        this.isDeleted = isDeleted;
        this.name = name;
        this.price = price;
    }
}
