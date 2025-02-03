package com.gabrielcarvalho.apiproducts.services;

import com.gabrielcarvalho.apiproducts.dto.ProductsRecordDto;
import com.gabrielcarvalho.apiproducts.exceptions.NotFoudExeption;
import com.gabrielcarvalho.apiproducts.models.ProductsModel;
import com.gabrielcarvalho.apiproducts.repositories.ProductsRepository;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductsService {

    private final ProductsRepository productsRepository;

    public ProductsService(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    public ProductsModel saveProduct(ProductsRecordDto productsRecordDto) {
        var productsModel = new ProductsModel();
        BeanUtils.copyProperties(productsRecordDto, productsModel);// Conversão de DTO para Model
        return productsRepository.save(productsModel);
    }


    public List<ProductsModel> getAllProducts() {
        return productsRepository.findAll();
    }


    public Optional<ProductsModel> getOneProduct(UUID id) {
        Optional<ProductsModel> productsModelOptional = productsRepository.findById(id);
      if (productsModelOptional.isEmpty()){
          throw new NotFoudExeption("Products Not Found");
      }
        return productsModelOptional;
    }


    public ProductsModel updateProduct( ProductsRecordDto productRecordDto, ProductsModel productsModel) {
        BeanUtils.copyProperties(productRecordDto, productsModel);
        return productsRepository.save(productsModel);
    }

    public void deleteProduct(ProductsModel productsModel) {
        productsRepository.delete(productsModel);
    }

    public boolean existsByName(String name) {
        return productsRepository.existsByName(name);
    }
}
