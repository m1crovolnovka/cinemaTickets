package io.denchik.cinemakursach.mapper;

import io.denchik.cinemakursach.dto.MovieDto;
import io.denchik.cinemakursach.models.Movie;


public class MovieMapper {

    public static Movie mapToMovie(MovieDto movieDto) {
        Movie movie = Movie.builder()
                .id(movieDto.getId())
                .title(movieDto.getTitle())
                .content(movieDto.getContent())
                .cost(movieDto.getCost())
                .photoUrl(movieDto.getPhotoUrl())
                .duration(movieDto.getDuration())
                .videoId(movieDto.getVideoId())
                .build();
        return movie;
    }

    public static MovieDto mapToMovieDto(Movie movie) {
        MovieDto movieDto = MovieDto.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .content(movie.getContent())
                .cost(movie.getCost())
                .photoUrl(movie.getPhotoUrl())
                .duration(movie.getDuration())
                .videoId(movie.getVideoId())
                .build();
        return movieDto;
    }
}
