package io.denchik.cinemakursach.service.impl;

import io.denchik.cinemakursach.dto.CinemaHallDto;
import io.denchik.cinemakursach.mapper.CinemaHallMapper;
import io.denchik.cinemakursach.models.CinemaHall;
import io.denchik.cinemakursach.repository.CinemaHallRepository;
import io.denchik.cinemakursach.service.CinemaHallService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class CinemaHallServiceImpl implements CinemaHallService {

    private CinemaHallRepository cinemaHallRepository;

    public CinemaHallServiceImpl(CinemaHallRepository cinemaHallRepository) {
        this.cinemaHallRepository = cinemaHallRepository;
    }


    @Override
    public List<CinemaHallDto> getAllCinemaHalls() {
        List<CinemaHall> cinemaHalls = cinemaHallRepository.findAll();
        return cinemaHalls.stream().map(CinemaHallMapper::mapToCinemaHallDto).collect(Collectors.toList());
    }

    @Override
    public CinemaHallDto getCinemaHallById(Long id) {
        CinemaHall cinemaHall = cinemaHallRepository.findById(id).get();
        return CinemaHallMapper.mapToCinemaHallDto(cinemaHall);
    }

    @Override
    public CinemaHallDto createCinemaHall(CinemaHallDto cinemaHallDto) {
        CinemaHall cinemaHall = CinemaHallMapper.mapToCinemaHall(cinemaHallDto);
        CinemaHall savedCinemaHall = cinemaHallRepository.save(cinemaHall);
        return CinemaHallMapper.mapToCinemaHallDto(savedCinemaHall);
    }

    @Override
    public void deleteCinemaHall(Long id) {
        cinemaHallRepository.deleteById(id);
    }

    @Override
    public CinemaHallDto updateCinemaHall(Long id ,CinemaHallDto cinemaHall) {
        CinemaHall oldCinemaHall = cinemaHallRepository.findById(id).get();
        oldCinemaHall.setName(cinemaHall.getName());
        oldCinemaHall.setCol(cinemaHall.getCol());
        oldCinemaHall.setRow(cinemaHall.getRow());
        CinemaHall updatedCinemaHall = cinemaHallRepository.save(oldCinemaHall);
        return CinemaHallMapper.mapToCinemaHallDto(updatedCinemaHall);
    }
}
