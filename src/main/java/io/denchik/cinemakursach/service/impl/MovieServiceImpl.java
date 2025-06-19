package io.denchik.cinemakursach.service.impl;

import io.denchik.cinemakursach.dto.MovieDto;
import io.denchik.cinemakursach.dto.SearchDto;
import io.denchik.cinemakursach.mapper.CinemaHallMapper;
import io.denchik.cinemakursach.mapper.MovieMapper;
import io.denchik.cinemakursach.models.Movie;
import io.denchik.cinemakursach.models.Ticket;
import io.denchik.cinemakursach.repository.MovieRepository;
import io.denchik.cinemakursach.repository.TicketRepository;
import io.denchik.cinemakursach.service.MovieService;

import io.denchik.cinemakursach.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static io.denchik.cinemakursach.mapper.MovieMapper.mapToMovie;
import static io.denchik.cinemakursach.mapper.MovieMapper.mapToMovieDto;


@Service
public class MovieServiceImpl implements MovieService {
    private final TicketRepository ticketRepository;
    private MovieRepository movieRepository;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository, TicketRepository ticketRepository) {
        this.movieRepository = movieRepository;
        this.ticketRepository = ticketRepository;

    }


    @Override
    public List<MovieDto> findAllMovies() {
        List<Movie> movies = movieRepository.findAll();
        return movies.stream().map(MovieMapper::mapToMovieDto).collect(Collectors.toList());
    }


    @Override
    public MovieDto createMovie(MovieDto movieDto) {
       Movie movie = MovieMapper.mapToMovie(movieDto);
       Movie savedMovie = movieRepository.save(movie);
       return mapToMovieDto(savedMovie);
    }

    @Override
    public MovieDto findMovieById(Long movieId) {
        Movie movie = movieRepository.findById(movieId).get();
        return mapToMovieDto(movie);
    }

    @Override
    public MovieDto updateMovie(Long movieId,MovieDto movieDto) {
        Movie oldMovie = movieRepository.findById(movieId).orElseThrow();
        oldMovie.setTitle(movieDto.getTitle());
        oldMovie.setContent(movieDto.getContent());
        oldMovie.setPhotoUrl(movieDto.getPhotoUrl());
        oldMovie.setDuration(movieDto.getDuration());
        oldMovie.setCost(movieDto.getCost());
        oldMovie.setVideoId(movieDto.getVideoId());
        Movie updatedMovie = movieRepository.save(oldMovie);

    return mapToMovieDto(updatedMovie);
    }

    @Override
    public void delete(Long movieId) {
        movieRepository.deleteById(movieId);
    }

    @Override
    public List<SearchDto> searchMovies(String query) {
        List<Movie> movies = movieRepository.searchMovies(query);
        List<SearchDto> searchDtos = new ArrayList<>();
        for(Movie movie: movies){
            for(Ticket ticket : ticketRepository.searchCinoSession(movie.getId())){
                SearchDto searchDto = new SearchDto();
                searchDto.setId(ticket.getId());
                searchDto.setMovieId(movie.getId());
                searchDto.setCost(movie.getCost());
                searchDto.setName(movie.getTitle());
                searchDto.setDateTime(LocalDateTime.of(ticket.getDate(),ticket.getTime()));
                searchDto.setCinemaHall(CinemaHallMapper.mapToCinemaHallDto(ticket.getCinemaHall()));
                searchDto.setPhotoUrl(movie.getPhotoUrl());
                searchDtos.add(searchDto);
            }
        }
        return searchDtos;
    }



}
