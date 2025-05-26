package io.denchik.cinemakursach.service;

import io.denchik.cinemakursach.dto.CinemaHallStatDto;
import io.denchik.cinemakursach.dto.DateRequestDto;
import io.denchik.cinemakursach.dto.MovieStatDto;
import io.denchik.cinemakursach.dto.TicketDto;
import io.denchik.cinemakursach.models.enums.PaymentType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


public interface TicketService {
    void createTicket(TicketDto ticketDto);
    List<TicketDto> findTicketsByMovieIdAndTimes(Long id);
    List<TicketDto> findTicketsByMovieIdAndDate(Long movieId,LocalDate date);
    List<DateRequestDto> findAllTicketDates();
    List<TicketDto> findAllTicketDatesTime(LocalDateTime date);
    List<MovieStatDto> findAllMovieStat();
    List<CinemaHallStatDto> findAllCinemaHallStat();
    List<TicketDto> findTicketsByCartId(Long id);
    List<TicketDto> findTicketsByBookingId(Long bookingId);

    void addTicketToBooking(Long bookingId, Long ticketDtoId, PaymentType paytype);
    void addTicketToAllBooking(Long bookingId, Long cartId, PaymentType paytype);

   void createShedule(LocalDate localDate);
    void delete(Long ticketId);
}
