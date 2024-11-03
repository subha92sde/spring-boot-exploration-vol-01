package com.spring.boot.exploration.dropdown.controller;

import com.spring.boot.exploration.dropdown.model.Fraternity;
import com.spring.boot.exploration.dropdown.service.FraternityService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/fraternity")
public class FraternityController extends GenericDropdownController<Fraternity> {
    public FraternityController(FraternityService fraternityService) {
        super(fraternityService, Fraternity.class);
    }
}
