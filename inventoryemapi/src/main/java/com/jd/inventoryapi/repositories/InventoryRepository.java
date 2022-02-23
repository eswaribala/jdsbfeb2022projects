package com.jd.inventoryapi.repositories;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class InventoryRepository {
    @Autowired
	private EntityManager entityManager;
}
