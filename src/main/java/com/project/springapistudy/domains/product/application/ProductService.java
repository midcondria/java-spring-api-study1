package com.project.springapistudy.domains.product.application;

import com.project.springapistudy.domains.product.application.request.ProductCreateRequest;
import com.project.springapistudy.domains.product.domain.Product;
import com.project.springapistudy.domains.product.domain.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public String createProduct(ProductCreateRequest request) {
        String productNumber = createProductNumber();

        Product product = request.toEntity(productNumber);
        productRepository.save(product);

        return productNumber;
    }

    // 서비스 정책에 맞는 productNumber 생성 정책으로 변경가능
    private String createProductNumber() {
        String latestProductNumber = productRepository.findLatestProductNumber();
        if (latestProductNumber == null) {
            return "001";
        }

        int latestNumber = Integer.parseInt(latestProductNumber);
        int nexNumber = latestNumber +1;

        return String.format("%03d", nexNumber);
    }
}
