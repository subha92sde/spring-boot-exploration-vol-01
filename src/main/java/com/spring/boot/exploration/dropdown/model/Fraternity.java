package com.spring.boot.exploration.dropdown.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_fraternity")
@NoArgsConstructor(force = true)
public class Fraternity extends GenericDropdownEntity {
    
}
