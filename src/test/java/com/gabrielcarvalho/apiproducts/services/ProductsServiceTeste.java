package com.gabrielcarvalho.apiproducts.services;

import com.gabrielcarvalho.apiproducts.models.ProductsModel;
import com.gabrielcarvalho.apiproducts.repositories.ProductsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductsServiceTeste {

    @InjectMocks
    private ProductsService productsService;

    @Mock
    private ProductsRepository productsRepository;

    @BeforeEach
    void setUp() {
        // Inicializando o mock do repositório e a instância da classe de serviço
        MockitoAnnotations.openMocks(this);
        productsService = new ProductsService(productsRepository);
    }



    @Test
    public void deveRetornarListaDeProducts(){

        ProductsModel product1 = new ProductsModel();
        product1.setName("product1");
        product1.setValue(BigDecimal.valueOf(55.50));

        ProductsModel product2 = new ProductsModel();
        product2.setName("product2");
        product2.setValue(BigDecimal.valueOf(55.55));

        //cria uma lista imutável contendo esses dois objetos (product1 e product2).
        List<ProductsModel> productsList = List.of(product1, product2);

        //setup  //execução  //verificação

        //setup
        when(productsRepository.findAll()).thenReturn(productsList);

        //execuçaõ
        ResponseEntity<List<ProductsModel>> response = productsService.getAllProducts();

        //verificação
        assertEquals(2, productsList.size());
        verify(productsRepository).findAll();
        verifyNoMoreInteractions(productsRepository);



    }



}
