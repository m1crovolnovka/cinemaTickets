package io.denchik.cinemakursach.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
public class CinemaHallStatDto {
    private Long id;
    private Long  cinemaHallId;
    private String hallName;
    private String movieName;
    private LocalDate date;
    private LocalTime time;
    private Integer amountOfSeats;
    private Integer amountOfOccupiedSeats;

}
