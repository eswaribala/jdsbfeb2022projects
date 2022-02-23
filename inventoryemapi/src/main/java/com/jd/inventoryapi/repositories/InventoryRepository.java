package com.jd.inventoryapi.repositories;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class InventoryRepository {
    @PersistenceContext
	private EntityManager entityManager;
}
