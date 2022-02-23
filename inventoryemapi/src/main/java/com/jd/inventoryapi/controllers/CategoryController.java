package com.jd.inventoryapi.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.bohnman.squiggly.Squiggly;
import com.github.bohnman.squiggly.util.SquigglyUtils;
import com.jd.inventoryapi.models.Category;
import com.jd.inventoryapi.repositories.InventoryRepository;


@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
	private InventoryRepository inventoryRepository;
    
    @PostMapping({"/v1.0", "/v1.1"})
    public ResponseEntity<?> addCategory(@RequestBody Category category){
    	
    	this.inventoryRepository.addCategory(category);
    	
    		return ResponseEntity.status(HttpStatus.ACCEPTED).body("Category Added");
    	
    }
    
    @GetMapping({"/v1.0", "/v1.1"})
    public List<Category> getAllCategories(){
    	return this.inventoryRepository.getAllCategories();
    }
    
    @GetMapping({"/v1.0/{categoryId}", "/v1.1/{categoryId}"})
    public Category getCategoryById(@PathVariable("categoryId") 
    long categoryId){
    	return this.inventoryRepository.getCategoryById(categoryId);
    }
     
    @DeleteMapping({"/v1.0/{categoryId}", "/v1.1/{categoryId}"})
    public ResponseEntity<?> deleteCategoryById(@PathVariable("categoryId") 
    long categoryId){
    	boolean status= this.inventoryRepository.deleteCategoryById(categoryId);
    	if(status)
    		return ResponseEntity.status(HttpStatus.ACCEPTED).body("Category with "
    				+ "Id"+categoryId+"deleted");
    	else
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Category with "
    				+ "Id"+categoryId+ "Not Deleted");
    	
    }
    
    @GetMapping({"/v1.0/filter/{categoryId}", "/v1.1/filter/{categoryId}"})
    public ResponseEntity<?> getFilteredCategoryById(@PathVariable("categoryId") 
    long categoryId,@RequestParam(name = "fields", required = false) String fields){
    	
    	Map<Object,Object> model=new HashMap<>();
    	
    	Category category= this.inventoryRepository.getCategoryById(categoryId);
    	
    	if(category!=null)
    	{
    		//fields refers to runtime selection
    		ObjectMapper mapper = Squiggly.init(new ObjectMapper(), fields);  		
			return ResponseEntity.ok(SquigglyUtils.stringify(mapper, category));

    	}
    	else
    	{
	         model.put("message", "category not existing");
		        
	         return ResponseEntity.ok(model);
    	}

    	
    }
    
	
}
