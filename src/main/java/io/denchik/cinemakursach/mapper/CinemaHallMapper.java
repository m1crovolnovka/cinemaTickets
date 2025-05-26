package io.denchik.cinemakursach.mapper;

import io.denchik.cinemakursach.dto.CinemaHallDto;
import io.denchik.cinemakursach.models.CinemaHall;


public class CinemaHallMapper {

    public static CinemaHall mapToCinemaHall(CinemaHallDto cinemaHallDto) {
        return CinemaHall.builder()
                .id(cinemaHallDto.getId())
                .col(cinemaHallDto.getCol())
                .row(cinemaHallDto.getRow())
                .name(cinemaHallDto.getName())
                .build();
    }

    public static CinemaHallDto mapToCinemaHallDto(CinemaHall cinemaHall) {
        return CinemaHallDto.builder()
                .col(cinemaHall.getCol())
                .row(cinemaHall.getRow())
                .id(cinemaHall.getId())
                .name(cinemaHall.getName())
                .build();
    }


}
