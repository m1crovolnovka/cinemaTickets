package io.denchik.cinemakursach.controller;

import io.denchik.cinemakursach.dto.MovieDto;
import io.denchik.cinemakursach.models.Movie;
import io.denchik.cinemakursach.models.UserEntity;
import io.denchik.cinemakursach.repository.MovieRepository;
import io.denchik.cinemakursach.repository.RoleRepository;
import io.denchik.cinemakursach.security.SecurityUtil;
import io.denchik.cinemakursach.service.MovieService;
import io.denchik.cinemakursach.service.TicketService;
import io.denchik.cinemakursach.service.UserService;
import jakarta.validation.Valid;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequestMapping()
public class MovieController {
    private MovieService movieService;
    private MovieRepository movieRepository;
    private UserService userService;
    private RoleRepository roleRepository;
    private TicketService ticketService;


    @Autowired
    public MovieController(MovieService movieService, MovieRepository movieRepository, UserService userService, RoleRepository roleRepository, TicketService ticketService) {
        this.movieService = movieService;
        this.movieRepository = movieRepository;
        this.userService = userService;
        this.roleRepository = roleRepository;
        this.ticketService = ticketService;
    }

    @GetMapping("/movies")
    public ResponseEntity<List<MovieDto>> listMovies() {
        List<MovieDto> moviesDto = movieService.findAllMovies();
        return ResponseEntity.ok(moviesDto);
//        UserEntity user = new UserEntity();
//        List<MovieDto> movies = movieService.findAllMovies();
//        String username = SecurityUtil.getSessionUser();
//        if(username != null) {
//            user = userService.findByUsername(username);
//            model.addAttribute("user", user);
//        }
//        model.addAttribute("user", user);
//        model.addAttribute("movies", movies);
//        return "movies-list";
    }
    @PostMapping("/movies/new")
    public ResponseEntity<MovieDto> saveMovie(@RequestBody MovieDto movieDto) {
        MovieDto savedMovie = movieService.createMovie(movieDto);
        return new ResponseEntity<>(savedMovie, HttpStatus.CREATED);
//        if(bindingResult.hasErrors()) {
//            model.addAttribute("movie", movieDto);
//            return "movies-create";
//        }
//        if(movieDto.getPlace().equals("PrimeHall"))
//            movieDto.setPlace("Prime Hall");
//        if (movieDto.getPlace().equals("ДворецРеспублики"))
//            movieDto.setPlace("Дворец Республики");
//        if(movieDto.getCost() < 0) {
//            bindingResult.rejectValue("cost", "error.cost", "Стоимость не может быть отрицательной");
//            return "movies-create";
//        }
//        Movie movie = movieService.saveMovie(movieDto);
//        ticketService.createTicket(movie.getId());
//        return "redirect:/movies";
    }

    @GetMapping("/movies/{movieId}")
    public ResponseEntity<MovieDto> movieDetail(@PathVariable("movieId") Long movieId) {
        MovieDto movieDto = movieService.findMovieById(movieId);
        return ResponseEntity.ok(movieDto);

        //        UserEntity user = new UserEntity();
//        MovieDto movieDto = movieService.findMovieById(movieId);
//        String username = SecurityUtil.getSessionUser();
//        if(username != null) {
//            user = userService.findByUsername(username);
//            model.addAttribute("user", user);
//        }
//        model.addAttribute("user", user);
//        model.addAttribute("movie", movieDto);
//        return "movie-detail";
    }

    @GetMapping("/movies/new")
    public String createMovieForm(Model model) {
        Movie movie = new Movie();
        model.addAttribute("movie", movie);
        return "movies-create";
    }
    @GetMapping("/movies/{movieId}/delete")
    public String deleteMovie(@PathVariable("movieId") Long movieId, Model model) {
        movieService.delete(movieId);
        return "redirect:/movies";
    }

    @GetMapping("/movies/{movieId}/edit")
    public String editMovieForm(@PathVariable("movieId") Long movieId, Model model) {
        MovieDto movie = movieService.findMovieById(movieId);
        model.addAttribute("movie", movie);
        return "movies-edit";
    }
   // @PostMapping("/movies/{movieId}/edit")
//    public String updateMovie(@PathVariable("movieId") Long movieId,
//                              @Valid @ModelAttribute("movie") MovieDto movie,
//                              BindingResult bindingResult) {
//        if(bindingResult.hasErrors()) {
//            return "movies-edit";
//        }
//         movie.setId(movieId);
//        if(movie.getPlace().equals("PrimeHall"))
//            movie.setPlace("Prime Hall");
//        if (movie.getPlace().equals("ДворецРеспублики"))
//            movie.setPlace("Дворец Республики");
//        if(movie.getCost() < 0) {
//            bindingResult.rejectValue("cost", "error.cost", "Стоимость не может быть отрицательной");
//        return "movies-edit";
//        }
//            movieService.updateMovie(movie);
//         return "redirect:/movies";
//    }
//    @GetMapping("/movies/search")
//    public String searchMovies(@RequestParam(value = "query") String query, Model model) {
//        List<MovieDto> movies = movieService.searchMovies(query).stream().sorted(Comparator.comparing(MovieDto::getStarted)).collect(Collectors.toList());
//       // MovieDto movieDto = movies.stream().max(Comparator.comparing(MovieDto::getCost)).get();
//        Movie movie = movieRepository.findAll().stream().max(Comparator.comparing(Movie::getCost)).get();
//        model.addAttribute("query",query);
//        model.addAttribute("maxPrice", movie.getCost());
//        model.addAttribute("maxPriceConst", movie.getCost());
//        model.addAttribute("movies", movies);
//        return "movies-search";
//    }
//    @PostMapping("/movies/search")
//    public String searchMovies(@RequestParam(value = "query") String query,
//                               @RequestParam(value = "ticket") String ticket,
//                               @RequestParam(value = "movieDateBefore", defaultValue = "") LocalDate movieDateBefore,
//                               @RequestParam(value = "movieDateAfter", defaultValue = "") LocalDate movieDateAfter,
//                               @RequestParam(value = "minPrice", defaultValue = "0") Double minPrice,
//                               @RequestParam(value = "maxPrice", defaultValue = "10000") Double maxPrice,
//                               @RequestParam(value = "sortBy") String sort,
//                               Model model) {
//        if(ticket.equals("PrimeHall"))
//            ticket = "Prime Hall";
//        if(ticket.equals("ДворецРеспублики"))
//            ticket = "Дворец Республики";
//        List<MovieDto> movies = movieService.searchMoviesByFilters(query,ticket,movieDateBefore,movieDateAfter,minPrice,maxPrice,sort);
//        Movie movie = movieRepository.findAll().stream().max(Comparator.comparing(Movie::getCost)).get();
//        model.addAttribute("query",query);
//        model.addAttribute("ticket",ticket);
//        model.addAttribute("movieDateBefore",movieDateBefore);
//        model.addAttribute("movieDateAfter",movieDateAfter);
//        model.addAttribute("minPrice",minPrice);
//        model.addAttribute("maxPrice",maxPrice);
//        model.addAttribute("maxPriceConst",movie.getCost());
//        model.addAttribute("sortBy",sort);
//        model.addAttribute("movies", movies);
//        return "movies-search";
//    }

}
