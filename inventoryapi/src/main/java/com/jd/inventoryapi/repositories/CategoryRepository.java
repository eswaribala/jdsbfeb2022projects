package com.jd.inventoryapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jd.inventoryapi.models.Category;

public interface CategoryRepository extends JpaRepository<Category,Long>{

}
