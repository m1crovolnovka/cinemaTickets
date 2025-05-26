package io.denchik.cinemakursach.service;

import io.denchik.cinemakursach.dto.CinemaHallDto;

import java.util.List;

public interface CinemaHallService {

    List<CinemaHallDto> getAllCinemaHalls();
    CinemaHallDto getCinemaHallById(Long id);
    CinemaHallDto createCinemaHall(CinemaHallDto cinemaHall);
    void deleteCinemaHall(Long id);
    CinemaHallDto updateCinemaHall(Long cinemaHallId, CinemaHallDto cinemaHall);
}
