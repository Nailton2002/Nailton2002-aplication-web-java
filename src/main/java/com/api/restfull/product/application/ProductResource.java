package com.api.restfull.product.application;

import com.api.restfull.product.domain.product.dto.request.ProductRequest;
import com.api.restfull.product.domain.product.dto.response.ProductResponse;
import com.api.restfull.product.domain.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Profile("dev")
@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductResource {

    private final ProductService service;

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest request) {
        ProductResponse response = service.createProduct(request);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(request.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        List<ProductResponse> list = service.getAllProducts();
        return ResponseEntity.ok().body(list);
    }
}
