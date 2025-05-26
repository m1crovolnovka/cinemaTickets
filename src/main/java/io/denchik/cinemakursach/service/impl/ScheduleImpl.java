package io.denchik.cinemakursach.service.impl;

import io.denchik.cinemakursach.dto.CinemaHallDto;
import io.denchik.cinemakursach.dto.MovieDto;
import io.denchik.cinemakursach.dto.TicketDto;
import io.denchik.cinemakursach.mapper.CinemaHallMapper;
import io.denchik.cinemakursach.mapper.MovieMapper;
import io.denchik.cinemakursach.models.CinemaHall;
import io.denchik.cinemakursach.repository.CinemaHallRepository;
import io.denchik.cinemakursach.service.CinemaHallService;
import io.denchik.cinemakursach.service.MovieService;
import io.denchik.cinemakursach.service.TicketService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class ScheduleImpl {
    private final LocalTime startOfWork= LocalTime.of(10,0);
    private final LocalTime endOfWork= LocalTime.of(0,0);
    private LocalTime time;
//    private CinemaHallRepository cinemaHallRepository;
    private CinemaHallService cinemaHallService;
    private MovieService movieService;
    private TicketService ticketService;

    ScheduleImpl(CinemaHallService cinemaHallService, MovieService movieService, TicketService ticketService) {
        this.cinemaHallService = cinemaHallService;
        this.movieService = movieService;
        this.ticketService = ticketService;
    }





}
