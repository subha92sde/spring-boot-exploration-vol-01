package com.spring.boot.exploration.dropdown.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_affiliation_type")
@NoArgsConstructor(force = true)
public class AffiliationType extends GenericDropdownEntity {
    
}
