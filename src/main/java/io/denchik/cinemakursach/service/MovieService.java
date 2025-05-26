package io.denchik.cinemakursach.service;

import io.denchik.cinemakursach.dto.MovieDto;
import io.denchik.cinemakursach.dto.SearchDto;
import io.denchik.cinemakursach.models.Movie;

import java.time.LocalDate;
import java.util.List;

public interface MovieService {
    List<MovieDto> findAllMovies();
    MovieDto createMovie(MovieDto movieDto);
    MovieDto findMovieById(Long id);

    MovieDto updateMovie(Long movieId, MovieDto updatedMovie);

    void delete(Long movieId);
    List<SearchDto> searchMovies(String query);
}
