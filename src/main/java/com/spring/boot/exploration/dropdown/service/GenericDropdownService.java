package com.spring.boot.exploration.dropdown.service;

import com.spring.boot.exploration.dropdown.model.GenericDropdownEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public abstract class GenericDropdownService<T extends GenericDropdownEntity> {
    @PersistenceContext
    private EntityManager entityManager;

    public List<T> findAll(Class<T> entityClass) {
        return entityManager.createQuery("from " + entityClass.getSimpleName(), entityClass).getResultList();
    }

    public T findById(Long id, Class<T> entityClass) {
        return entityManager.find(entityClass, id);
    }

    public boolean isTableEmpty(Class<T> entityClass) {
        try {
            T entity = findById(1L, entityClass);
            return entity == null;
        } catch (Exception e) {
            // Catching any general exceptions and providing detailed logging
            System.err.println("Error checking if table is empty for entity: " + entityClass.getName());
            System.err.println("Exception: " + e.getClass().getName() + " - " + e.getMessage());
            return true; // Assuming true if an error occurs, you could also return false depending on the requirement
        }
    }

    @Transactional
    public void saveAll(List<T> entities) {
        for (T entity : entities) {
            if (entity.getId() == null || entity.getId() == 0L) {
                entityManager.persist(entity);
            } else {
                entityManager.merge(entity);
            }
        }
    }
}
