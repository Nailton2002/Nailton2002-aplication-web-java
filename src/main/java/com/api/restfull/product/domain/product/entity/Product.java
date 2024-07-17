package com.api.restfull.product.domain.product.entity;

import com.api.restfull.product.domain.product.dto.request.ProductRequest;
import com.api.restfull.product.domain.product.dto.response.ProductResponse;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_product")
@Entity(name = "Product")
@EqualsAndHashCode(of = "id")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Integer quantidade;
    private Double valor;

    public static Product fromRequestToEntity(ProductRequest dto) {
        return Product.builder()
                .id(dto.getId())
                .nome(dto.getNome())
                .quantidade(dto.getQuantidade())
                .valor(dto.getValor())
                .build();
    }

    public static Product fromResponseToEntity(ProductResponse dto) {
        return Product.builder()
                .id(dto.getId())
                .nome(dto.getNome())
                .quantidade(dto.getQuantidade())
                .valor(dto.getValor())
                .build();
    }
    public void updateProduct(ProductRequest request) {
        if (request.getNome() != null) {
            this.nome = request.getNome();
        }
        if (request.getQuantidade() != null) {
            this.quantidade = request.getQuantidade();
        }
        if (request.getValor() != null) {
            this.valor = request.getValor();
        }
    }
}
