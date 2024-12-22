package com.gabrielcarvalho.apiproducts.controllers;

import com.gabrielcarvalho.apiproducts.dto.ProductsRecordDto;
;
import com.gabrielcarvalho.apiproducts.models.ProductsModel;
import com.gabrielcarvalho.apiproducts.services.ProductsService;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductsController {

    private final ProductsService productsService;

    public ProductsController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @PostMapping
    public ResponseEntity<ProductsModel> saveProduct(@RequestBody @Valid ProductsRecordDto productsRecordDto) {
        return productsService.saveProduct(productsRecordDto);
    }

    @GetMapping
    public ResponseEntity<List<ProductsModel>> getAllProducts() {
        return productsService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneProduct(@PathVariable(value = "id") UUID id) {
        return productsService.getOneProduct(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable(value = "id") UUID id,
                                                @RequestBody @Valid ProductsRecordDto productRecordDto) {
        return productsService.updateProduct(id, productRecordDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable(value = "id") UUID id) {
        return productsService.deleteProduct(id);
    }

    @GetMapping("/search")
    public ResponseEntity<Object> getProductsByName(@RequestParam String name) {
        return productsService.getProductsByName(name);
    }


}


