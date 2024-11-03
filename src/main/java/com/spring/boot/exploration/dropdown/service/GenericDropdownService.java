package com.spring.boot.exploration.dropdown.service;

import com.spring.boot.exploration.dropdown.model.GenericDropdownEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

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
}
