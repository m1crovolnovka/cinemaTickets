package io.denchik.cinemakursach.dto;

import io.denchik.cinemakursach.models.Booking;
import io.denchik.cinemakursach.models.Cart;
import io.denchik.cinemakursach.models.CinemaHall;
import io.denchik.cinemakursach.models.Movie;
import io.denchik.cinemakursach.models.enums.PaymentType;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
public class TicketDto {

    private Long id;
    private LocalDate date ;
    private LocalTime time ;
    private Integer row;
    private Integer col;
    private Boolean status;
    private CinemaHallDto cinemaHall;
    private MovieDto movie;
    private PaymentType payType;
}
