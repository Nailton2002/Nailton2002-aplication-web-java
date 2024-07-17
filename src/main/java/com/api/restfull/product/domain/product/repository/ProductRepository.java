package com.api.restfull.product.domain.product.repository;

import com.api.restfull.product.domain.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
