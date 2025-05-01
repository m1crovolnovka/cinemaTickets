package io.denchik.cinemakursach.service;

import io.denchik.cinemakursach.dto.TicketDto;
import io.denchik.cinemakursach.models.enums.PaymentType;

import java.util.List;


public interface TicketService {
    void createTicket(Long movieId);
    List<TicketDto> findAllTickets();
    List<TicketDto> findTicketsByMovieId(Long id);
    List<TicketDto> findTicketsByCartId(Long id);
    List<TicketDto> findTicketsByBookingId(Long bookingId);
    void addTicketToCart(Long CartId, Long ticketDto);
    void addTicketToBooking(Long bookingId, Long ticketDtoId, PaymentType paytype);
    void addTicketToAllBooking(Long bookingId, Long cartId, PaymentType paytype);
    void updateTicket(TicketDto ticketDto);

    TicketDto findTicketsById(Long ticketId);

    void delete(Long ticketId);
}
