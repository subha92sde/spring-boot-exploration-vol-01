package com.spring.boot.exploration.dropdown.controller;

import com.spring.boot.exploration.dropdown.model.Fraternity;
import com.spring.boot.exploration.dropdown.service.FraternityService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/fraternity")
public class FraternityController extends GenericDropdownController<Fraternity> {
    private final FraternityService fraternityService;

    public FraternityController(FraternityService fraternityService) {
        super(fraternityService, Fraternity.class);
        this.fraternityService = fraternityService;
    }

    @RequestMapping(value = "/bulk-create", method = RequestMethod.POST)
    public List<Fraternity> bulkCreate(@RequestBody List<String> fraternityNames) {
        List<Fraternity> fraternities = fraternityNames.stream()
                .filter(name -> name != null && !name.isBlank())
                .map(name -> {
                    Fraternity fraternity = new Fraternity();
                    fraternity.setName(name);
                    return fraternity;
                })
                .toList();
        List<Fraternity> createdFraternities = fraternityService.bulkCreate(fraternities);
        fraternityService.exportFraternityDataToCsv();
        return createdFraternities;
    }
}
