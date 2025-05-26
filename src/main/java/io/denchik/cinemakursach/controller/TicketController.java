package io.denchik.cinemakursach.controller;


import io.denchik.cinemakursach.dto.CinemaHallStatDto;
import io.denchik.cinemakursach.dto.DateRequestDto;
import io.denchik.cinemakursach.dto.MovieStatDto;
import io.denchik.cinemakursach.dto.TicketDto;
import io.denchik.cinemakursach.service.MovieService;
import io.denchik.cinemakursach.service.TicketService;
import io.denchik.cinemakursach.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping()
public class TicketController {

    private TicketService ticketService;
    private UserService userService;
    private MovieService movieService;


    @Autowired
    public TicketController(TicketService ticketService, UserService userService, MovieService movieService) {
        this.userService = userService;
        this.ticketService = ticketService;
        this.movieService = movieService;

    }


    @PostMapping("/tickets/new")
    public void addShedule(@RequestBody DateRequestDto request) {
        System.out.println(LocalDate.parse(request.getFullDate(), DateTimeFormatter.ISO_DATE_TIME).plusDays(1));
        ticketService.createShedule(LocalDate.parse(request.getFullDate(), DateTimeFormatter.ISO_DATE_TIME).plusDays(1));
    }


    @PutMapping("/tickets/movie/{movieId}")
    public ResponseEntity<List<TicketDto>> ticketsForMovieByDate(@PathVariable("movieId") Long movieId,@RequestBody DateRequestDto request) {
        LocalDate date = LocalDateTime.parse(request.getFullDate(), DateTimeFormatter.ISO_DATE_TIME).plusHours(3).toLocalDate();
        System.out.println(date);
        List<TicketDto> ticketDto = ticketService.findTicketsByMovieIdAndDate(movieId,date);
        return ResponseEntity.ok(ticketDto);
    }

    @GetMapping("/tickets/dates")
    public ResponseEntity<List<DateRequestDto>> ticketsDates() {
        List<DateRequestDto> ticketDto = ticketService.findAllTicketDates();
        return ResponseEntity.ok(ticketDto);
    }

    @PutMapping("/tickets/chooseMenu")
    public ResponseEntity<List<TicketDto>> ticketsForChooseMenu(@RequestBody DateRequestDto request) {
        List<TicketDto> ticketDto = ticketService.findAllTicketDatesTime(LocalDateTime.parse(request.getFullDate(), DateTimeFormatter.ISO_DATE_TIME));
        return ResponseEntity.ok(ticketDto);
    }

    @GetMapping("/tickets/moviesStat")
    public ResponseEntity<List<MovieStatDto>> ticketsForMoviesStat() {
        List<MovieStatDto> ticketDto = ticketService.findAllMovieStat();
        return ResponseEntity.ok(ticketDto);
    }

    @GetMapping("/tickets/cinemaHallStat")
    public ResponseEntity<List<CinemaHallStatDto>> ticketsForCinemaHallStat() {
        List<CinemaHallStatDto> ticketDto = ticketService.findAllCinemaHallStat();
        return ResponseEntity.ok(ticketDto);
    }


}
