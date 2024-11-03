package com.spring.boot.exploration.dropdown.repository;

import com.spring.boot.exploration.dropdown.model.GenericDropdownEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenericDropdownRepository<T extends GenericDropdownEntity> extends JpaRepository<T, Long> {
    
}
