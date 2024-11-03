package com.spring.boot.exploration.dropdown.controller;

import com.spring.boot.exploration.dropdown.model.Genre;
import com.spring.boot.exploration.dropdown.service.GenreService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/genre")
public class GenreController extends GenericDropdownController<Genre> {
    public GenreController(GenreService genreService) {
        super(genreService, Genre.class);
    }
}