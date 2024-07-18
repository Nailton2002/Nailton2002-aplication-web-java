package com.api.restfull.product.domain.product.dto.request;

import com.api.restfull.product.domain.product.entity.Product;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {

    private Long id;
    private String nome;
    private Integer quantidade;
    private Double valor;

    public static ProductRequest fromEntityToDto(Product obj) {
        return ProductRequest.builder()
                .id(obj.getId())
                .nome(obj.getNome())
                .quantidade(obj.getQuantidade())
                .valor(obj.getValor())
                .build();
    }
}
