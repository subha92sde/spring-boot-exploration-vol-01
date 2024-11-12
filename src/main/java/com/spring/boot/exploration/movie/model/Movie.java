package com.spring.boot.exploration.movie.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.time.Year;

@Entity
@Table(name = "tbl_movie", schema = "chewbacca")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title", unique = true, nullable = false, length = 100)
    private String title;

    @Column(name = "imdb_rating", nullable = false, precision = 2, scale = 1)
    private BigDecimal imdbRating;

    @Column(name = "year_of_release", columnDefinition = "year", nullable = false)
    @Convert(converter = YearAttributeConverter.class)
    private Year yearOfRelease;
}
