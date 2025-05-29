package com.gabrielcarvalho.apiproducts.integration;

import com.gabrielcarvalho.apiproducts.dto.ProductsRecordDto;
import com.gabrielcarvalho.apiproducts.models.ProductsModel;
import com.gabrielcarvalho.apiproducts.repositories.ProductsRepository;
import com.gabrielcarvalho.apiproducts.services.ProductsService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProductsServiceIntegrationTest {

    @Autowired
    private ProductsService productsService;

    @Autowired
    private ProductsRepository productsRepository;

    @Test
    void deveSalvarProduto() {
        ProductsRecordDto dto = new ProductsRecordDto("teste", BigDecimal.valueOf(100));

        ProductsModel salvar = productsService.saveProduct(dto);

        Assertions.assertNotNull(salvar.getIdProduct());
        Assertions.assertEquals("teste", salvar.getName());
        Assertions.assertEquals(BigDecimal.valueOf(100), salvar.getValue());
    }


}
