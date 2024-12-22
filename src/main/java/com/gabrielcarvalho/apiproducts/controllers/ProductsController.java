package com.gabrielcarvalho.apiproducts.controllers;

import com.gabrielcarvalho.apiproducts.dto.ProductsRecordDto;
;
import com.gabrielcarvalho.apiproducts.services.ProductsService;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductsController {

    private final ProductsService productsService;

    public ProductsController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @PostMapping("/products")
    public ResponseEntity<?> saveProduct(@RequestBody@ Valid ProductsRecordDto productsRecordDto) {
        return productsService.saveProduct(productsRecordDto);
    }

    @GetMapping
    public ResponseEntity<?> getAllProducts() {
        return productsService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOneProduct(@PathVariable(value = "id") UUID id) {
        return productsService.getOneProduct(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable(value = "id") UUID id,
                                           @RequestBody @Valid ProductsRecordDto productRecordDto) {
        return productsService.updateProduct(id, productRecordDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable(value = "id") UUID id) {
        return productsService.deleteProduct(id);
    }

    @GetMapping("/search")
    public ResponseEntity<?> getProductsByName(@RequestParam String name) {
        return productsService.getProductsByName(name);
    }


}


