package com.spring.boot.exploration.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;

@Service
public class DatabaseUtilityService<T> {
    @PersistenceContext
    private EntityManager entityManager;

    public boolean isTableEmpty(Class<T> entityClass) {
        try {
            T entity = entityManager.find(entityClass, 1L);
            return entity == null;
        } catch (Exception e) {
            System.err.println("Error checking if table is empty for entity: " + entityClass.getName());
            System.err.println("Exception: " + e.getClass().getName() + " - " + e.getMessage());
            return true; // Assuming true if an error occurs
        }
    }

    public boolean isJoinTableEmpty(String tableName) {
        String query = "SELECT COUNT(*) FROM " + tableName;
        try {
            Long count = (Long) entityManager.createNativeQuery(query).getSingleResult();
            return count == 0;
        } catch (Exception e) {
            System.err.println("Error checking if table '" + tableName + "' is empty.");
            System.err.println("Exception: " + e.getClass().getName() + " - " + e.getMessage());
            return true; // Assume empty if an error occurs
        }
    }
}
