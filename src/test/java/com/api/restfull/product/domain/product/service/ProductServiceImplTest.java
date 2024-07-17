package com.api.restfull.product.domain.product.service;

import com.api.restfull.product.domain.product.dto.request.ProductRequest;
import com.api.restfull.product.domain.product.dto.response.ProductResponse;
import com.api.restfull.product.domain.product.entity.Product;
import com.api.restfull.product.domain.product.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

//Configura o Mockito para ser usado com JUnit 5.
@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    // Cria um mock do ProductRepository
    @Mock
    private ProductRepository productRepository;

    //Injeta os mocks na instância do ProductServiceImpl
    @InjectMocks
    private ProductServiceImpl productService;

    //Inicializa os objetos de teste
    private Product product;
    private ProductRequest productRequest;
    private ProductResponse productResponse;

    @BeforeEach
    public void setUp() {

        product = new Product();
        product.setId(1L);
        product.setNome("Test Product");
        product.setQuantidade(3);
        product.setValor(100.0);

        productRequest = new ProductRequest();
        productRequest.setNome("Teste Product");
        productRequest.setQuantidade(3);
        productRequest.setValor(100.0);

        productResponse = new ProductResponse();
        productResponse.setId(1L);
        productResponse.setNome("Test Product");
        productResponse.setQuantidade(3);
        productResponse.setValor(100.0);
    }

    @Test
    void testCreateProduct_Success() {

        //Configura o mock do productRepository para retornar um objeto Product quando o método save for chamado.
        when(productRepository.save(any(Product.class))).thenReturn(product);

        //Chama o método createProduct e verifica se a resposta é igual ao esperado.
        ProductResponse response = productService.createProduct(productRequest);

        assertEquals(productResponse.getId(), response.getId());
        assertEquals(productResponse.getNome(), response.getNome());
        assertEquals(productResponse.getQuantidade(), response.getQuantidade());
        assertEquals(productResponse.getValor(), response.getValor());

        //Verifica se o método save do repositório foi chamado uma vez.
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    void testCreateProduct_Error() {

        //Configura o mock do productRepository para lançar uma exceção quando o método save for chamado.
        when(productRepository.save(any(Product.class))).thenThrow(new RuntimeException("Database Error"));

        //Chama o método createProduct e verifica se a exceção lançada é a esperada.
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            productService.createProduct(productRequest);
        });
        assertEquals("Database Error", exception.getMessage());

        //Verifica se o método save do repositório foi chamado uma vez.
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    void getAllProducts() {
    }

    @Test
    void getProductById() {
    }

    @Test
    void updateProduct() {
    }

    @Test
    void deleteProduct() {
    }
}