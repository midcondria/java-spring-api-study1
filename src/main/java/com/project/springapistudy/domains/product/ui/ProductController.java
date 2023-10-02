package com.project.springapistudy.domains.product.ui;

import com.project.springapistudy.domains.product.application.ProductService;
import com.project.springapistudy.domains.product.application.request.ProductCreateRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/products")
    public ResponseEntity createProduct(@RequestBody @Valid ProductCreateRequest request) {
        String productNumber = productService.createProduct(request);
        return ResponseEntity.created(
                ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{productNumber}")
                    .buildAndExpand(productNumber)
                    .toUri()
            )
            .build();
    }
}
