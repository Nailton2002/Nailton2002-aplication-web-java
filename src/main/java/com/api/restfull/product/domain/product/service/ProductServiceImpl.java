package com.api.restfull.product.domain.product.service;

import com.api.restfull.product.domain.product.dto.request.ProductRequest;
import com.api.restfull.product.domain.product.dto.response.ProductResponse;
import com.api.restfull.product.domain.product.entity.Product;
import com.api.restfull.product.domain.product.repository.ProductRepository;
import com.api.restfull.product.infra.validations.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

    @Override
    public ProductResponse getProductById(Long id) {
        Product obj = productRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(
            "Objeto não encontrado! Id: " + id + ", Tipo: " + ProductResponse.class.getName()));
        return ProductResponse.fromEntityToDto(obj);
    }

    @Override
    public ProductResponse updateProduct(Long id, ProductRequest request) {
        Optional<Product> productOptional = productRepository.findById(request.getId());
        if (productOptional.isPresent()) {
            Product obj = productOptional.get();
            obj.updateProduct(request);
            Product productUpdate = productRepository.save(obj);
            return ProductResponse.fromEntityToDto(productUpdate);
        } else {
            throw new ObjectNotFoundException("Usuário não encontrado com o ID: " + request.getId());
        }
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
