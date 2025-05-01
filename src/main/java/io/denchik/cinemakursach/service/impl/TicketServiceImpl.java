package io.denchik.cinemakursach.service.impl;


import io.denchik.cinemakursach.dto.TicketDto;
import io.denchik.cinemakursach.models.Booking;
import io.denchik.cinemakursach.models.Cart;
import io.denchik.cinemakursach.models.Movie;
import io.denchik.cinemakursach.models.Ticket;
import io.denchik.cinemakursach.models.enums.PaymentType;
import io.denchik.cinemakursach.repository.BookingRepository;
import io.denchik.cinemakursach.repository.CartRepository;
import io.denchik.cinemakursach.repository.MovieRepository;
import io.denchik.cinemakursach.repository.TicketRepository;
import io.denchik.cinemakursach.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;



@Service
public class TicketServiceImpl implements TicketService {
private TicketRepository ticketRepository;
private MovieRepository movieRepository;
private CartRepository cartRepository;
private BookingRepository bookingRepository;

    @Autowired
    public TicketServiceImpl(TicketRepository ticketRepository, MovieRepository movieRepository, CartRepository cartRepository, BookingRepository bookingRepository) {
        this.ticketRepository = ticketRepository;
        this.movieRepository = movieRepository;
        this.cartRepository = cartRepository;
        this.bookingRepository = bookingRepository;
    }

    @Override
    public void createTicket(Long movieId) {
//        Movie movie = MovieRepository.findById(movieId).get();
//        TicketDto ticketDto = mapToTicketDto(new Ticket());
//        ticketDto.setName(movie.getPlace());
//        ticketDto.setDate(movie.getStarted());
//        if(ticketDto.getName().equals("Prime Hall")) {
//            createPrimeHall(movie.getId(), ticketDto);
//        }
//        if(ticketDto.getName().equals("Минск-Арена")) {
//            createMinsk_Arena(movie.getId(), ticketDto);
//        }
//        if(ticketDto.getName().equals("Дворец Республики")) {
//            createPalace_Republic(movie.getId(), ticketDto);
//        }
    }
    public void createPrimeHall(Long movieId, TicketDto ticketDto) {
//        Movie movie = movieRepository.findById(movieId).get();
//        for(Integer k = 1; k <= 14; k++ ) {
//                Ticket ticket = mapToTicket(ticketDto);
//                ticket.setMovie(movie);
//                ticket.setStatus(true);
//                ticket.setNumOfSeat(k);
//                ticket.setSector("Balcony1");
//                ticketRepository.save(ticket);
//            }
//        for(Integer k = 1; k <= 14; k++ ) {
//            Ticket ticket = mapToTicket(ticketDto);
//            ticket.setMovie(movie);
//            ticket.setStatus(true);
//            ticket.setNumOfSeat(k);
//            ticket.setSector("Balcony2");
//            ticketRepository.save(ticket);
//        }
//        for(Integer k = 1; k <= 14; k++ ) {
//            Ticket ticket = mapToTicket(ticketDto);
//            ticket.setMovie(movie);
//            ticket.setStatus(true);
//            ticket.setNumOfSeat(k);
//            ticket.setSector("Balcony3");
//            ticketRepository.save(ticket);
//        }
//        for(Integer k = 1; k <= 100; k++ ) {
//            Ticket ticket = mapToTicket(ticketDto);
//            ticket.setMovie(movie);
//            ticket.setStatus(true);
//            ticket.setNumOfSeat(null);
//            ticket.setSector("Dancefloor");
//            ticketRepository.save(ticket);
//        }
    }
    public void createMinsk_Arena(Long movieId, TicketDto ticketDto) {
//        Movie movie = MovieRepository.findById(movieId).get();
//        for(Integer k = 1; k <= 42; k++ ) {
//            Ticket ticket = mapToTicket(ticketDto);
//            ticket.setMovie(movie);
//            ticket.setStatus(true);
//            ticket.setNumOfSeat(k);
//            ticket.setSector("Sector1");
//            ticketRepository.save(ticket);
//        }
//        for(Integer k = 1; k <= 54; k++ ) {
//            Ticket ticket = mapToTicket(ticketDto);
//            ticket.setMovie(movie);
//            ticket.setStatus(true);
//            ticket.setNumOfSeat(k);
//            ticket.setSector("Sector2");
//            ticketRepository.save(ticket);
//        }
//        for(Integer k = 1; k <= 42; k++ ) {
//            Ticket ticket = mapToTicket(ticketDto);
//            ticket.setMovie(movie);
//            ticket.setStatus(true);
//            ticket.setNumOfSeat(k);
//            ticket.setSector("Sector3");
//            ticketRepository.save(ticket);
//        }
//        for(Integer k = 1; k <= 30; k++ ) {
//            Ticket ticket = mapToTicket(ticketDto);
//            ticket.setMovie(movie);
//            ticket.setStatus(true);
//            ticket.setNumOfSeat(k);
//            ticket.setSector("Balcony1");
//            ticketRepository.save(ticket);
//        }
//        for(Integer k = 1; k <= 30; k++ ) {
//            Ticket ticket = mapToTicket(ticketDto);
//            ticket.setMovie(movie);
//            ticket.setStatus(true);
//            ticket.setNumOfSeat(k);
//            ticket.setSector("Balcony2");
//            ticketRepository.save(ticket);
//        }
    }
public void createPalace_Republic(Long movieId, TicketDto ticketDto) {
//    Movie movie = MovieRepository.findById(movieId).get();
//    for(Integer k = 1; k <= 72; k++ ) {
//        Ticket ticket = mapToTicket(ticketDto);
//        ticket.setMovie(movie);
//        ticket.setStatus(true);
//        ticket.setNumOfSeat(k);
//        ticket.setSector("MainHall");
//        ticketRepository.save(ticket);
//    }
}



    @Override
    public List<TicketDto> findAllTickets() {
        List<Ticket> tickets = ticketRepository.findAll();
        return null;//tickets.stream().map(ticket-> mapToTicketDto(ticket)).collect(Collectors.toList());
    }

    @Override
    public List<TicketDto> findTicketsByMovieId(Long movieId) {
        Movie movie = movieRepository.findById(movieId).get();
        return null; //movie.getTickets().stream().map((ticket)->mapToTicketDto(ticket)).collect(Collectors.toList());
    }

    @Override
    public List<TicketDto> findTicketsByCartId(Long cartId) {
        Cart cart = cartRepository.findById(cartId).get();
        return null;// cart.getTickets().stream().map((ticket)->mapToTicketDto(ticket)).collect(Collectors.toList());
    }

    @Override
    public List<TicketDto> findTicketsByBookingId(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId).get();
        return null;// booking.getTickets().stream().map((ticket)->mapToTicketDto(ticket)).collect(Collectors.toList());
    }

    @Override
    public void addTicketToCart(Long cartId, Long ticketId) {
        Cart cart = cartRepository.findById(cartId).get();
        Ticket ticket = ticketRepository.findById(ticketId).get();
        ticket.setCart(cart);
        ticketRepository.save(ticket);
    }

    @Override
    public void addTicketToBooking(Long bookingId, Long ticketId, PaymentType payType) {
        Booking booking = bookingRepository.findById(bookingId).get();
        Ticket ticket = ticketRepository.findById(ticketId).get();
        ticket.setBooking(booking);
        ticket.setPayType(payType);
        ticket.setCart(null);
        ticketRepository.save(ticket);
    }

    @Override
    public void addTicketToAllBooking(Long bookingId, Long cartId, PaymentType paytype) {
        Booking booking = bookingRepository.findById(bookingId).get();
        List<TicketDto> tickets = findTicketsByCartId(cartId);
//        for (TicketDto ticket : tickets) {
//            ticket.setBooking(booking);
//            ticket.setPayType(paytype);
//            ticket.setCart(null);
//            ticketRepository.save(mapToTicket(ticket));
//        }
    }

    @Override
    public void updateTicket(TicketDto ticketDto) {
//        Ticket ticket = mapToTicket(ticketDto);
//        ticket.setStatus(false);
//        ticketRepository.save(ticket);
    }

    @Override
    public TicketDto findTicketsById(Long ticketId) {
        return null;//mapToTicketDto(ticketRepository.findById(ticketId).get());
    }

    @Override
    public void delete(Long ticketId) {
        ticketRepository.deleteById(ticketId);
    }

}
