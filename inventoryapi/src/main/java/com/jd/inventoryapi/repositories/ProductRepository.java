package com.jd.inventoryapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jd.inventoryapi.models.Product;

public interface ProductRepository extends JpaRepository<Product,Long>{

}
