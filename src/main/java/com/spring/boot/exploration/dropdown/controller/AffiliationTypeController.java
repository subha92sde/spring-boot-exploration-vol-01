package com.spring.boot.exploration.dropdown.controller;

import com.spring.boot.exploration.dropdown.model.AffiliationType;
import com.spring.boot.exploration.dropdown.service.AffiliationTypeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/affiliation-type")
public class AffiliationTypeController extends GenericDropdownController<AffiliationType> {
    public AffiliationTypeController(AffiliationTypeService affiliationTypeService) {
        super(affiliationTypeService, AffiliationType.class);
    }
}
