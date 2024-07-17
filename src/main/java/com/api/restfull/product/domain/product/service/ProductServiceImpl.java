package com.api.restfull.product.domain.product.service;

import com.api.restfull.product.domain.product.dto.request.ProductRequest;
import com.api.restfull.product.domain.product.dto.response.ProductResponse;
import com.api.restfull.product.domain.product.entity.Product;
import com.api.restfull.product.domain.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ProductResponse createProduct(ProductRequest request) {
        Product product = Product.fromRequestToEntity(request);
        Product obj = productRepository.save(product);
        return ProductResponse.fromEntityToDto(obj);
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        List<Product> list = productRepository.findAll();
        List<ProductResponse> responseList = list.stream().map(product -> ProductResponse.fromEntityToDto(product)).collect(Collectors.toList());
        return responseList;
    }
}
