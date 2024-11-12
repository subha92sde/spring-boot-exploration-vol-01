package com.spring.boot.exploration.movie.controller;

import com.spring.boot.exploration.movie.dto.MovieReqDTO;
import com.spring.boot.exploration.movie.model.Movie;
import com.spring.boot.exploration.movie.service.MovieService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/movie")
public class MovieController {
    private final MovieService movieService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody @Valid MovieReqDTO reqDTO) {
        Movie movie = movieService.create(reqDTO);
        return new ResponseEntity<>(movie, HttpStatus.OK);
    }
}
