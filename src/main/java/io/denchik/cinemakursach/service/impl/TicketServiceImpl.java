package io.denchik.cinemakursach.service.impl;


import io.denchik.cinemakursach.dto.*;
import io.denchik.cinemakursach.mapper.CinemaHallMapper;
import io.denchik.cinemakursach.mapper.MovieMapper;
import io.denchik.cinemakursach.mapper.TicketMapper;
import io.denchik.cinemakursach.models.Booking;
import io.denchik.cinemakursach.models.Cart;
import io.denchik.cinemakursach.models.Movie;
import io.denchik.cinemakursach.models.Ticket;
import io.denchik.cinemakursach.models.enums.PaymentType;
import io.denchik.cinemakursach.repository.BookingRepository;
import io.denchik.cinemakursach.repository.CartRepository;
import io.denchik.cinemakursach.repository.MovieRepository;
import io.denchik.cinemakursach.repository.TicketRepository;
import io.denchik.cinemakursach.service.CinemaHallService;
import io.denchik.cinemakursach.service.MovieService;
import io.denchik.cinemakursach.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;



@Service
public class TicketServiceImpl implements TicketService {
private TicketRepository ticketRepository;
private MovieRepository movieRepository;
private CinemaHallService cinemaHallService;
private MovieService movieService;
private CartRepository cartRepository;
private BookingRepository bookingRepository;

    @Autowired
    public TicketServiceImpl(TicketRepository ticketRepository,
                             MovieRepository movieRepository,
                             CartRepository cartRepository,
                             BookingRepository bookingRepository,
                             CinemaHallService cinemaHallService,
                             MovieService movieService) {
        this.ticketRepository = ticketRepository;
        this.movieRepository = movieRepository;
        this.cartRepository = cartRepository;
        this.bookingRepository = bookingRepository;
        this.cinemaHallService = cinemaHallService;
        this.movieService = movieService;
    }

    @Override
    public List<DateRequestDto> findAllTicketDates() {
        List<LocalDate> localDates = ticketRepository.searchDates();
        List<DateRequestDto> dateRequestDtos = new ArrayList<>();
        System.out.println(localDates);
        for (LocalDate localDate : localDates) {
            dateRequestDtos.add(new DateRequestDto());
            dateRequestDtos.getLast().setFullDate(localDate.atStartOfDay().format(DateTimeFormatter.ISO_DATE_TIME));
        }
        return dateRequestDtos;
    }

    @Override
    public List<TicketDto> findAllTicketDatesTime(LocalDateTime localDateTime) {
        localDateTime = localDateTime.plusHours(3);
        LocalDate ld = localDateTime.toLocalDate();
        LocalTime lt = localDateTime.toLocalTime();
        System.out.println(localDateTime);
        List<Ticket> tickets = ticketRepository.findAll().stream().filter(e->{ return (ld.equals(e.getDate()) && lt.equals(e.getTime()) ); }).collect(Collectors.toList());
        return tickets.stream().map(TicketMapper::mapToTicketDto).collect(Collectors.toList());
    }

    @Override
    public List<MovieStatDto> findAllMovieStat() {
        List<Movie> movies = movieRepository.findAll();
        List<MovieStatDto> movieStatDtos = new ArrayList<>();
        for(Movie movie : movies) {
            Integer purchased = ticketRepository.findPurchased(movie.getId());
            MovieStatDto movieStatDto = MovieStatDto.builder()
                    .id(movie.getId())
                    .title(movie.getTitle())
                    .soldTickets(purchased)
                    .revenue(purchased * movie.getCost())
                    .build();
            movieStatDtos.add(movieStatDto);
        }
        return movieStatDtos;
    }

    @Override
    public List<CinemaHallStatDto> findAllCinemaHallStat() {
        List<CinemaHallStatDto> cinemaHallStatDtos = new ArrayList<>();
        List<Movie> movies = movieRepository.findAll();
        for(Movie movie : movies) {
            List<Ticket> tickets = ticketRepository.searchCinoSession(movie.getId());
            for(Ticket ticket : tickets){
                Integer purchased = ticketRepository.findPurchasedCinemaHall(ticket.getDate(), ticket.getTime());
                CinemaHallStatDto cinemaHallStatDto = CinemaHallStatDto.builder()
                        .id(ticket.getId())
                        .cinemaHallId(ticket.getCinemaHall().getId())
                        .hallName(ticket.getCinemaHall().getName())
                        .movieName(ticket.getMovie().getTitle())
                        .date(ticket.getDate())
                        .time(ticket.getTime())
                        .amountOfSeats(ticket.getCinemaHall().getCol() * ticket.getCinemaHall().getRow())
                        .amountOfOccupiedSeats(purchased)
                        .build();
                cinemaHallStatDtos.add(cinemaHallStatDto);
            }
        }
        return cinemaHallStatDtos;
    }

    @Override
    public void createTicket(TicketDto ticketDto) {
        Ticket ticket = TicketMapper.mapToTicket(ticketDto);
        ticketRepository.save(ticket);
    }




    @Override
    public List<TicketDto> findTicketsByMovieIdAndTimes(Long movieId) {
        System.out.println(movieId);
        Movie movie = movieRepository.findById(movieId).get();
        return movie.getTickets().stream().map(TicketMapper::mapToTicketDto).filter(e -> e.getRow() == 1 && e.getCol() == 1).collect(Collectors.toList());
    }

    @Override
    public List<TicketDto> findTicketsByMovieIdAndDate(Long movieId,LocalDate date) {
        Movie movie = movieRepository.findById(movieId).get();
        return movie.getTickets().stream().map(TicketMapper::mapToTicketDto).filter(e -> e.getRow() == 1 && e.getCol() == 1 && e.getDate().equals(date)).collect(Collectors.toList());
    }

    @Override
    public List<TicketDto> findTicketsByCartId(Long cartId) {
        Cart cart = cartRepository.findById(cartId).get();
        return cart.getTickets().stream().map(TicketMapper::mapToTicketDto).collect(Collectors.toList());
    }

    @Override
    public List<TicketDto> findTicketsByBookingId(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId).get();
        return  booking.getTickets().stream().map(TicketMapper::mapToTicketDto).collect(Collectors.toList());
    }


    @Override
    public void addTicketToBooking(Long bookingId, Long ticketId, PaymentType payType) {
        Booking booking = bookingRepository.findById(bookingId).get();
        Ticket ticket = ticketRepository.findById(ticketId).get();
        ticket.setBooking(booking);
        ticket.setPayType(payType);
        ticket.setStatus(true);
        ticket.setCart(null);
        ticketRepository.save(ticket);
    }

    @Override
    public void addTicketToAllBooking(Long bookingId, Long cartId, PaymentType paytype) {
        Booking booking = bookingRepository.findById(bookingId).get();
        List<Ticket> tickets = findTicketsByCartId(cartId).stream().map(TicketMapper::mapToTicket).toList();
        for (Ticket ticket : tickets) {
            ticket.setBooking(booking);
            ticket.setPayType(paytype);
            ticket.setStatus(true);
            ticket.setCart(null);
            ticketRepository.save(ticket);
        }
    }



    @Override
    public void delete(Long ticketId) {
        ticketRepository.deleteById(ticketId);
    }

    public void deleteSchedule(){
        LocalDate localDate = LocalDateTime.now().minusHours(3).toLocalDate();
        List<Ticket> tickets = ticketRepository.findAll().stream().filter(t -> {
            return t.getDate().isBefore(localDate);
        }).toList();
        ticketRepository.deleteAll(tickets);
    }


    @Override
    public void createShedule(LocalDate localDate){
        deleteSchedule();
        final LocalTime startOfWork= LocalTime.of(10,0);
        final LocalTime endOfWork= LocalTime.of(0,0);
        LocalTime time;
        List<CinemaHallDto> cinemaHallDtos = cinemaHallService.getAllCinemaHalls();
        List<Movie> movies = movieRepository.findAll().stream().sorted(Comparator.comparing((e) -> {
            if(e.getTickets()==null || e.getTickets().isEmpty()) {
                System.out.println(e);
                return 1000000;
            }
            else {
                int soldTickets = e.getTickets().stream().reduce(0, (x, y) -> x + y.getStatus().compareTo(true), Integer::sum);
                int allTickets = e.getTickets().size();
                if( soldTickets == 0){
                    return allTickets;
                }
                else{
                    return allTickets + soldTickets;
                }
            }
        } )).collect(Collectors.toList()).reversed();
        for(CinemaHallDto cinemaHallDto : cinemaHallDtos){
            Movie movie = movies.getFirst();
            time = startOfWork;
            while(true) {
                movie =movies.getFirst();
                if(time.plusMinutes(movie.getDuration()).getHour() < startOfWork.getHour())
                {
                    for(Movie mv : movies){
                        if(time.plusMinutes(mv.getDuration()).getHour() > startOfWork.getHour())
                        {
                            movie = mv;
                            break;
                        }
                    }
                    if(time.plusMinutes(movie.getDuration()).getHour() < startOfWork.getHour())
                    {
                        break;
                    }
                }
                movies.remove(movie);
                for (int i = 0; i < cinemaHallDto.getRow(); i++) {
                    for (int j = 0; j < cinemaHallDto.getCol(); j++) {
                        TicketDto ticketDto = TicketDto.builder()
                                .col(j+1)
                                .row(i+1)
                                .date(localDate)
                                .movie(MovieMapper.mapToMovieDto(movie))
                                .time(time)
                                .cinemaHall(cinemaHallDto)
                                .status(false)
                                .build();
                        createTicket(ticketDto);
                    }
                }
                time = time.plusMinutes(movie.getDuration() + 15);
                int remainder = time.getMinute() % 5;
                if (!(remainder == 0)) {
                    time = time.plusMinutes(5 - remainder);
                }
                movies.add(movie);
            }

        }
    }
}
