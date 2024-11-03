package com.spring.boot.exploration.dropdown.controller;

import com.spring.boot.exploration.dropdown.model.GenericDropdownEntity;
import com.spring.boot.exploration.dropdown.service.GenericDropdownService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

public abstract class GenericDropdownController<T extends GenericDropdownEntity> {
    private final GenericDropdownService<T> genericDropdownService;
    private final Class<T> entityClass;

    public GenericDropdownController(GenericDropdownService<T> genericDropdownService, Class<T> entityClass) {
        this.genericDropdownService = genericDropdownService;
        this.entityClass = entityClass;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<T> getAll() {
        return genericDropdownService.findAll(entityClass);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public T getById(@PathVariable Long id) {
        return genericDropdownService.findById(id, entityClass);
    }
}
