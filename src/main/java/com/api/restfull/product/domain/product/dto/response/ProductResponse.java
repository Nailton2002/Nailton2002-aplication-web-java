package com.api.restfull.product.domain.product.dto.response;

import com.api.restfull.product.domain.product.dto.request.ProductRequest;
import com.api.restfull.product.domain.product.entity.Product;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductResponse {

    private Long id;
    private String nome;
    private Integer quantidade;
    private Double valor;

    public static ProductResponse fromEntityToDto(Product obj) {
        return ProductResponse.builder()
                .id(obj.getId())
                .nome(obj.getNome())
                .quantidade(obj.getQuantidade())
                .valor(obj.getValor())
                .build();
    }
}
