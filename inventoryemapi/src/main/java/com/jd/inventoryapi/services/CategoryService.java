package com.jd.inventoryapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jd.inventoryapi.models.Category;
import com.jd.inventoryapi.models.Product;
import com.jd.inventoryapi.repositories.CategoryRepository;

@Service
public class CategoryService {
    @Autowired
	private CategoryRepository categoryRepo;
    
    //insert
    
    public Category addCategory(Category category) {
    	
    	if((category!=null)&&(category.getProductList().size()>0)){
    		for(Product product:category.getProductList()) {
    			product.setCategory(category);
    		}
    	}
    	return this.categoryRepo.save(category);
    	
    }
    //select all
    public List<Category> getAllCategories(){
    	return this.categoryRepo.findAll();
    }
	
    //select where
    
    public Category getCategoryById(long categoryId) {
    	return this.categoryRepo.findById(categoryId).orElse(null);
    }
    
    //delete 
    
    public boolean deleteCategoryById(long categoryId) {
    	boolean status=false;
    	this.categoryRepo.deleteById(categoryId);
    	if(this.getCategoryById(categoryId)==null)
    		status=true;
    	return status;
    }
    
}
