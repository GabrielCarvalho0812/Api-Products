package com.gabrielcarvalho.apiproducts.services;

import com.gabrielcarvalho.apiproducts.exceptions.NotFoudExeption;
import com.gabrielcarvalho.apiproducts.models.ProductsModel;
import com.gabrielcarvalho.apiproducts.repositories.ProductsRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.*;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductsServiceTest {

    @InjectMocks
    private ProductsService productsService;

    @Mock
    private ProductsRepository productsRepository;


    @Test
    public void deveRetornarListaDeProducts() {
            // Criando um mock da lista de produtos

            ProductsModel product1 = new ProductsModel();
            product1.setValue(BigDecimal.valueOf(50));
            product1.setName("Product1");
            List<ProductsModel> mockProductList = Arrays.asList(product1);

            when(productsRepository.findAll()).thenReturn(mockProductList);

            List<ProductsModel> allProducts = productsService.getAllProducts();

            verify(productsRepository, times(1)).findAll();
            Assertions.assertEquals(mockProductList, allProducts);
    }

    @Test
    public void deveRetornarProdutoQuandoEncontrado() {
        UUID id = UUID.randomUUID();
        ProductsModel product1 = new ProductsModel();
        product1.setIdProduct(id);
        product1.setName("Product1");

        when(productsRepository.findById(id)).thenReturn(Optional.of(product1));

        Optional<ProductsModel> result = productsService.getOneProduct(id);

        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals( id, result.get().getIdProduct());
        verify(productsRepository, times(1)).findById(id);

    }


    @Test
    public void deveLancarExcecaoQuandoProdutoNaoEncontrado() {
        UUID id = UUID.randomUUID();

        when(productsRepository.findById(id)).thenReturn(Optional.empty());

        Assertions.assertThrows(NotFoudExeption.class, () -> productsService.getOneProduct(id));
        verify(productsRepository, times(1)).findById(id);

    }


}
