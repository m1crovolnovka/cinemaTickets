package io.denchik.cinemakursach.service;

import io.denchik.cinemakursach.dto.MovieDto;
import io.denchik.cinemakursach.models.Movie;

import java.time.LocalDate;
import java.util.List;

public interface MovieService {
    List<MovieDto> findAllMovies();
    Movie saveMovie(MovieDto movieDto);
    MovieDto createMovie(MovieDto movieDto);
    MovieDto findMovieById(Long id);

    void updateMovie(MovieDto movie);

    void delete(Long movieId);
    List<MovieDto> searchMovies(String query);
    List<MovieDto> searchMoviesByFilters(String query, String ticket, LocalDate movieDateBefore,LocalDate movieDateAfter, Double minPrice, Double maxPrice, String sort);
}
