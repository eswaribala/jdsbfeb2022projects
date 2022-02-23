package com.jd.inventoryapi.repositories;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jd.inventoryapi.models.Category;
import com.jd.inventoryapi.models.Product;

@Repository
public class InventoryRepository {
    @PersistenceContext
	private EntityManager entityManager;
    
    
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
}
