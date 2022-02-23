package com.jd.inventoryapi.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.jd.inventoryapi.models.Category;
import com.jd.inventoryapi.models.Product;


@Repository
public class InventoryRepository {
    @PersistenceContext
	private EntityManager entityManager;
    @Value("${select-categories}")
    private String selectCategories;
    
 //insert
    //commit and rollback
    @Transactional
    public void addCategory(Category category) {
    	
    	if((category!=null)&&(category.getProductList().size()>0)){
    		for(Product product:category.getProductList()) {
    			product.setCategory(category);
    		}
    	}
    	this.entityManager.persist(category);
    	
    }
    
  //select all
    public List<Category> getAllCategories(){
    	
    	return this.entityManager.createQuery(selectCategories,Category.class).getResultList();
    }
	
    //select where
    
    public Category getCategoryById(long categoryId) {
    	return this.entityManager.find(Category.class, categoryId);
    }
    
    //delete 
    
    public boolean deleteCategoryById(long categoryId) {
    	boolean status=false;
    	Category category=this.entityManager.find(Category.class,categoryId);
    	this.entityManager.remove(category);
    	if(this.entityManager.find(Category.class,categoryId)==null)
    		status=true;
    	return status;	
    }
}
