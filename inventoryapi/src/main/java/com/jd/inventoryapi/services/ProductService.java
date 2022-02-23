package com.jd.inventoryapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jd.inventoryapi.models.Category;
import com.jd.inventoryapi.models.Product;
import com.jd.inventoryapi.repositories.ProductRepository;

@Service
public class ProductService {
    @Autowired
	private ProductRepository productRepository;
    @Autowired
    private CategoryService categoryService;
    
    public Product addProduct(Product product, long categoryId) {
    	
    	Category category=this.categoryService.getCategoryById(categoryId);
    	product.setCategory(category);
    	return this.productRepository.save(product);
    }
	
}
