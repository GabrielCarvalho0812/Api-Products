package com.gabrielcarvalho.apiproducts.controllers;

import com.gabrielcarvalho.apiproducts.dto.ProductsRecordDto;
import com.gabrielcarvalho.apiproducts.models.ProductsModel;
import com.gabrielcarvalho.apiproducts.repositories.ProductsRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class ProductsController {

    ProductsRepository productsRepository;

    public ProductsController(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }


    @PostMapping("/products")
    public ResponseEntity<ProductsModel> saveProduct(@RequestBody @Valid ProductsRecordDto productsRecordDto){
        var productsModel = new ProductsModel();
        BeanUtils.copyProperties(productsRecordDto,productsModel);//fazer a cpnversão de DTO para Model
        // fazer tratamento de exeção para o nome
        return ResponseEntity.status(HttpStatus.CREATED).body(productsRepository.save(productsModel));
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductsModel>> getallProducts(){
        return  ResponseEntity.status(HttpStatus.OK).body(productsRepository.findAll());
    }


    @GetMapping("/products/{id}")
    public ResponseEntity<Object> getOneProduct(@PathVariable(value = "id") UUID id){
        Optional<ProductsModel> product0 = productsRepository.findById(id);

        if (product0.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("product not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(product0.get());
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable(value = "id") UUID id,
                                                @RequestBody @Valid ProductsRecordDto productRecordDto){
        Optional<ProductsModel> product0 = productsRepository.findById(id);

        if (product0.isEmpty()){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        var productModel = product0.get();
        BeanUtils.copyProperties(productRecordDto, productModel);
        return ResponseEntity.status(HttpStatus.OK).body(productsRepository.save(productModel));
    }


    @DeleteMapping("/products/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable (value = "id") UUID id){
        Optional<ProductsModel> produtc0 = productsRepository.findById(id);
        if (produtc0.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
        productsRepository.delete(produtc0.get());
        return ResponseEntity.status(HttpStatus.OK).body("Product delete successfully");
    }

}


