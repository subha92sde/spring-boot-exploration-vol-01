package com.spring.boot.exploration.movie.service;

import com.spring.boot.exploration.movie.dto.MovieReqDTO;
import com.spring.boot.exploration.movie.model.Movie;
import com.spring.boot.exploration.movie.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;

    public Movie create(MovieReqDTO reqDTO) {
        Movie movie = new Movie();
        movie.setTitle(reqDTO.getTitle());
        movie.setImdbRating(reqDTO.getImdbRating());
        movie.setYearOfRelease(reqDTO.getYearOfRelease());
        movieRepository.save(movie);
        return movie;
    }
}
