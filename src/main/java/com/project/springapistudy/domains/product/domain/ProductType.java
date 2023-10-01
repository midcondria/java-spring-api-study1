package com.project.springapistudy.domains.product.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ProductType {

    COFFEE("커피"),
    BEVERAGE("음료"),
    BAKERY("빵류");

    private final String text;
}
