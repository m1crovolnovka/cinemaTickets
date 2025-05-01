package io.denchik.cinemakursach.service.impl;

import io.denchik.cinemakursach.dto.MovieDto;
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
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static io.denchik.cinemakursach.mapper.MovieMapper.mapToMovie;
import static io.denchik.cinemakursach.mapper.MovieMapper.mapToMovieDto;


@Service
public class MovieServiceImpl implements MovieService {
    private final TicketRepository ticketRepository;
   // private final TicketService ticketService;
    private MovieRepository movieRepository;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository, TicketRepository ticketRepository/*, TicketService ticketService*/) {
        this.movieRepository = movieRepository;
        this.ticketRepository = ticketRepository;
        //this.ticketService = ticketService;
    }

    @Override
    public List<MovieDto> findAllMovies() {
        List<Movie> movies = movieRepository.findAll();
        return movies.stream().map(MovieMapper::mapToMovieDto).collect(Collectors.toList());
    }

    @Override
    public Movie saveMovie(MovieDto movieDto) {
        Movie movie = mapToMovie(movieDto);
        return movieRepository.save(movie);
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
    public void updateMovie(MovieDto movieDto) {
        Movie oldMovie = movieRepository.findById(movieDto.getId()).get();
        Movie movie = mapToMovie(movieDto);
//        if (oldMovie.getPlace().equals(movie.getPlace())) {
//            movieRepository.save(movie);
//        }
//        else{
//            List<Ticket> tickets = oldMovie.getTickets();
//            ticketRepository.deleteAll(tickets);
//            movieRepository.save(movie);
//            ticketService.createTicket(movie.getId());
//        }

    }

    @Override
    public void delete(Long movieId) {
        movieRepository.deleteById(movieId);
    }

    @Override
    public List<MovieDto> searchMovies(String query) {
        List<Movie> movies = movieRepository.searchMovies(query);
        return movies.stream().map(MovieMapper::mapToMovieDto).collect(Collectors.toList());
    }

    @Override
    public List<MovieDto> searchMoviesByFilters(String query, String ticket, LocalDate movieDateBefore, LocalDate movieDateAfter, Double minPrice, Double maxPrice, String sort) {
        List<Movie> movies = movieRepository.searchMovies(query);
//        if (!ticket.equals(""))
//            movies = movies.stream().filter(movie -> movie.getPlace().equals(ticket)).collect(Collectors.toList());
//        if (movieDateBefore != null)
//            movies = movies.stream().filter(movie -> movie.getStarted().isAfter(movieDateBefore)).collect(Collectors.toList());
//        if (movieDateAfter != null)
//            movies = movies.stream().filter(movie -> movie.getStarted().isBefore(movieDateAfter)).collect(Collectors.toList());
//        movies = movies.stream().filter(movie -> movie.getCost() >= minPrice).collect(Collectors.toList());
//        movies = movies.stream().filter(movie -> movie.getCost() <= maxPrice).collect(Collectors.toList());
//        if (sort.equals("dateIcs"))
//            movies = movies.stream().sorted(Comparator.comparing(Movie::getStarted)).collect(Collectors.toList());
//        if (sort.equals("dateDcs"))
//            movies = movies.stream().sorted(Comparator.comparing(Movie::getStarted).reversed()).collect(Collectors.toList());
//        if (sort.equals("priceIcs"))
//            movies = movies.stream().sorted(Comparator.comparing(Movie::getCost)).collect(Collectors.toList());
//        if (sort.equals("priceDcs"))
//            movies = movies.stream().sorted(Comparator.comparing(Movie::getCost).reversed()).collect(Collectors.toList());
        return movies.stream().map(MovieMapper::mapToMovieDto).collect(Collectors.toList());
    }

}
