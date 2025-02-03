package com.gabrielcarvalho.apiproducts.controllers;

import com.gabrielcarvalho.apiproducts.dto.ProductsRecordDto;
;
import com.gabrielcarvalho.apiproducts.models.ProductsModel;
import com.gabrielcarvalho.apiproducts.repositories.ProductsRepository;
import com.gabrielcarvalho.apiproducts.services.ProductsService;
import jakarta.validation.Valid;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductsController{

    private final ProductsService productsService;

    public ProductsController(ProductsService productsService, ProductsRepository productsRepository){
        this.productsService = productsService;
    }

    @PostMapping
    public ResponseEntity<Object> saveProduct(@RequestBody @Valid ProductsRecordDto productsRecordDto){
        if (productsService.existsByName(productsRecordDto.name())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product name already exists");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(productsService.saveProduct(productsRecordDto));
    }

    @GetMapping
    public ResponseEntity<List<ProductsModel>> getAllProducts() {
        return ResponseEntity.status(HttpStatus.OK).body(productsService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneProduct(@PathVariable(value = "id") UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(productsService.getOneProduct(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable(value = "id") UUID id,
                                                @RequestBody @Valid ProductsRecordDto productRecordDto) {
        if (productsService.existsByName(productRecordDto.name())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product name already exists");
        }
        return ResponseEntity.status(HttpStatus.OK).body
                (productsService.updateProduct(productRecordDto, productsService.getOneProduct(id).get()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable(value = "id") UUID id) {
        productsService.deleteProduct(productsService.getOneProduct(id).get());
        return ResponseEntity.status(HttpStatus.OK).body("Product deleted successfully");
    }


}




