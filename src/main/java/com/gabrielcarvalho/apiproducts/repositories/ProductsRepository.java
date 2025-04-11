package com.gabrielcarvalho.apiproducts.repositories;

import com.gabrielcarvalho.apiproducts.models.ProductsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface ProductsRepository extends JpaRepository<ProductsModel, UUID> {

    boolean existsByName(String name);
}
