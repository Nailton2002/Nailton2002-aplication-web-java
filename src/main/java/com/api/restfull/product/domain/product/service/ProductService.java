package com.api.restfull.product.domain.product.service;

import com.api.restfull.product.domain.product.dto.request.ProductRequest;
import com.api.restfull.product.domain.product.dto.response.ProductResponse;
import com.api.restfull.product.domain.product.entity.Product;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

    @Transactional
    ProductResponse create(ProductRequest request);

}
