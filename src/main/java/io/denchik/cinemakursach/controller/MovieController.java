package io.denchik.cinemakursach.controller;

import io.denchik.cinemakursach.dto.MovieDto;
import io.denchik.cinemakursach.dto.SearchDto;
import io.denchik.cinemakursach.repository.MovieRepository;
import io.denchik.cinemakursach.repository.RoleRepository;
import io.denchik.cinemakursach.service.MovieService;
import io.denchik.cinemakursach.service.TicketService;
import io.denchik.cinemakursach.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping()
public class MovieController {
    private final MovieService movieService;



    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;

    }

    @GetMapping("/movies")
    public ResponseEntity<List<MovieDto>> listMovies() {
        List<MovieDto> moviesDto = movieService.findAllMovies();
        return ResponseEntity.ok(moviesDto);
    }
    @PostMapping("/movies/new")
    public ResponseEntity<MovieDto> saveMovie(@RequestBody MovieDto movieDto) {
        MovieDto savedMovie = movieService.createMovie(movieDto);
        return new ResponseEntity<>(savedMovie, HttpStatus.CREATED);
    }

    @GetMapping("/movies/{movieId}")
    public ResponseEntity<MovieDto> movieDetail(@PathVariable("movieId") Long movieId) {
        MovieDto movieDto = movieService.findMovieById(movieId);
        return ResponseEntity.ok(movieDto);
    }

    @DeleteMapping("/movies/{movieId}")
    public ResponseEntity<String> deleteMovie(@PathVariable("movieId") Long movieId) {
        movieService.delete(movieId);
        return ResponseEntity.ok("Movie deleted successfully!");
    }

    @PutMapping("/movies/{movieId}")
    public ResponseEntity<MovieDto> updateMovie(@PathVariable("movieId") Long movieId,
                                                @RequestBody MovieDto updatedMovie) {
        MovieDto movieDto = movieService.updateMovie(movieId, updatedMovie);
        return ResponseEntity.ok(movieDto);
    }


    @GetMapping("/movies/search")
    public ResponseEntity<List<SearchDto>> searchMovies(@RequestParam(value = "query") String query) {
        List<SearchDto> searchDtos = movieService.searchMovies(query);
        return ResponseEntity.ok(searchDtos);
    }


}
