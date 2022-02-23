package com.jd.inventoryapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jd.inventoryapi.models.Category;
import com.jd.inventoryapi.services.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
	private CategoryService categoryService;
    
    @PostMapping({"/v1.0", "/v1.1"})
    public ResponseEntity<?> addCategory(@RequestBody Category category){
    	
    	Category categoryObj=this.categoryService.addCategory(category);
    	if(categoryObj!=null)
    		return ResponseEntity.status(HttpStatus.ACCEPTED).body(categoryObj);
    	else
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Category Not Created");
    	    	
    }
    @GetMapping({"/v1.0", "/v1.1"})
    public List<Category> getAllCategories(){
    	return this.categoryService.getAllCategories();
    }
    
    @GetMapping({"/v1.0/{categoryId}", "/v1.1/{categoryId}"})
    public Category getCategoryById(@PathVariable("categoryId") 
    long categoryId){
    	return this.categoryService.getCategoryById(categoryId);
    }
     
    @DeleteMapping({"/v1.0/{categoryId}", "/v1.1/{categoryId}"})
    public ResponseEntity<?> deleteCategoryById(@PathVariable("categoryId") 
    long categoryId){
    	boolean status= this.categoryService.deleteCategoryById(categoryId);
    	if(status)
    		return ResponseEntity.status(HttpStatus.ACCEPTED).body("Category with "
    				+ "Id"+categoryId+"deleted");
    	else
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Category with "
    				+ "Id"+categoryId+ "Not Deleted");
    	
    }
    
	
}
