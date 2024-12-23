package com.gabrielcarvalho.apiproducts.services;

import com.gabrielcarvalho.apiproducts.dto.ProductsRecordDto;
import com.gabrielcarvalho.apiproducts.models.ProductsModel;
import com.gabrielcarvalho.apiproducts.repositories.ProductsRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductsService {

    private final ProductsRepository productsRepository;


    @Autowired
    public ProductsService(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    public ResponseEntity<ProductsModel> saveProduct(ProductsRecordDto productsRecordDto) {
        var productsModel = new ProductsModel();
        BeanUtils.copyProperties(productsRecordDto, productsModel);// Conversão de DTO para Model
        ProductsModel savedProduct = productsRepository.save(productsModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(productsRepository.save(productsModel));
    }

    public ResponseEntity<List<ProductsModel>> getAllProducts() {
        return ResponseEntity.status(HttpStatus.OK).body(productsRepository.findAll());
    }

    public ResponseEntity<Object> getOneProduct(UUID id) {
        Optional<ProductsModel> product = productsRepository.findById(id);

      if (productsRepository.existsById(id)){
          return ResponseEntity.status(HttpStatus.OK).body(product.get());
      }
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");

    }

    public ResponseEntity<Object> updateProduct(UUID id, ProductsRecordDto productRecordDto) {
        Optional<ProductsModel> product = productsRepository.findById(id);

        if (product.isPresent()) {
            ProductsModel productsModel = product.get();

            productsModel.setName(productRecordDto.name());
            productsModel.setValue(productRecordDto.value());
            productsRepository.save(productsModel);
            System.out.println("Product updated successfully.");
            return ResponseEntity.status(HttpStatus.OK).body(productsRepository.save(productsModel));

        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }
    }


    public ResponseEntity<Object> deleteProduct(UUID id) {
        Optional<ProductsModel> product = productsRepository.findById(id);

        if (product.isPresent()) {
            productsRepository.delete(product.get());
            return ResponseEntity.status(HttpStatus.OK).body("Product deleted successfully.");
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }

    }

}
