package com.api.restfull.product.domain.product.service;

import com.api.restfull.product.domain.product.dto.request.ProductRequest;
import com.api.restfull.product.domain.product.dto.response.ProductResponse;
import com.api.restfull.product.domain.product.entity.Product;
import com.api.restfull.product.domain.product.repository.ProductRepository;
import com.api.restfull.product.infra.validations.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        product.setNome("Teste Product");
        product.setQuantidade(3);
        product.setValor(100.0);

        productRequest = new ProductRequest();
        productRequest.setNome("Teste Product");
        productRequest.setQuantidade(3);
        productRequest.setValor(100.0);

        productResponse = new ProductResponse();
        productResponse.setId(1L);
        productResponse.setNome("Teste Product");
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
    void testGetAllProducts() {

        // Configuração do mock para retornar uma lista de produtos
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        when(productRepository.findAll()).thenReturn(productList);

        // Chamada do método de serviço
        List<ProductResponse> responseList = productService.getAllProducts();

        // Verificação dos resultados
        assertEquals(1, responseList.size());
        assertEquals(productResponse.getId(), responseList.get(0).getId());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testGetProductById_Success() {

        // Configuração do mock para retornar um produto específico
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(product));

        // Chamada do método de serviço
        ProductResponse response = productService.getProductById(1L);

        // Verificação dos resultados
        assertEquals(productResponse.getId(), response.getId());
        verify(productRepository, times(1)).findById(anyLong());
    }

    @Test
    void testGetProductById_NotFound() {
        // Configuração do mock para retornar uma exceção quando o produto não for encontrado
        when(productRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Verificação de exceção
        Exception exception = assertThrows(ObjectNotFoundException.class, () -> {
            productService.getProductById(1L);
        });

        // Verificação da mensagem de exceção
        assertEquals("Service ou ID não encontrado -> Id Objeto não encontrado! Id: 1, Tipo: com.api.restfull.product.domain.product.dto.response.ProductResponse", exception.getMessage());
        verify(productRepository, times(1)).findById(anyLong());
    }

    @Test
    void testUpdateProduct_Success() {

        // Configuração do mock para retornar um produto existente

        // Chamada do método de serviço

        // Verificação dos resultados
    }

    @Test
    void testUpdateProduct_NotFound() {

        // Configuração do mock para retornar uma exceção quando o produto não for encontrado

        // Verificação de exceção

        // Verificação da mensagem de exceção
    }

    @Test
    void testDeleteProduct() {

        // Configuração do mock para verificar a chamada de exclusão
        doNothing().when(productRepository).deleteById(anyLong());

        // Chamada do método de serviço
        productService.deleteProduct(1L);

        // Verificação da chamada do método de exclusão
        verify(productRepository, times(1)).deleteById(anyLong());
    }
}