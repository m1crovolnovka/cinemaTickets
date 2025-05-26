package io.denchik.cinemakursach.mapper;


import io.denchik.cinemakursach.dto.TicketDto;
import io.denchik.cinemakursach.models.Ticket;

public class TicketMapper {

    public static Ticket mapToTicket(TicketDto ticketDto) {
        Ticket ticket = Ticket.builder()
                .id(ticketDto.getId())
                .col(ticketDto.getCol())
                .row(ticketDto.getRow())
                .movie(MovieMapper.mapToMovie(ticketDto.getMovie()))
                .date(ticketDto.getDate())
                .time(ticketDto.getTime())
                .status(ticketDto.getStatus())
                .cinemaHall(CinemaHallMapper.mapToCinemaHall(ticketDto.getCinemaHall()))
                .payType(ticketDto.getPayType())
                .build();
        return ticket;
    }

    public static TicketDto mapToTicketDto(Ticket ticket) {
        TicketDto ticketDto = TicketDto.builder()
                .id(ticket.getId())
                .col(ticket.getCol())
                .row(ticket.getRow())
                .movie(MovieMapper.mapToMovieDto(ticket.getMovie()))
                .date(ticket.getDate())
                .time(ticket.getTime())
                .status(ticket.getStatus())
                .cinemaHall(CinemaHallMapper.mapToCinemaHallDto(ticket.getCinemaHall()))
                .payType(ticket.getPayType())
                .build();
        return ticketDto;
    }

}
