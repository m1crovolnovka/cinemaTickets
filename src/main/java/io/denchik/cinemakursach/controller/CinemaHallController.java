package io.denchik.cinemakursach.controller;

import io.denchik.cinemakursach.dto.CinemaHallDto;

import io.denchik.cinemakursach.service.CinemaHallService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@CrossOrigin("*")
@RestController
@RequestMapping()
public class CinemaHallController {
    private CinemaHallService cinemaHallService;


    @Autowired
    public CinemaHallController(CinemaHallService cinemaHallService) {

        this.cinemaHallService = cinemaHallService;
    }

    @GetMapping("/cinemaHall")
    public ResponseEntity<List<CinemaHallDto>> listCinemaHall() {
        List<CinemaHallDto> cinemaHallDtos = cinemaHallService.getAllCinemaHalls();
        return ResponseEntity.ok(cinemaHallDtos);
    }
    @PostMapping("/cinemaHall/new")
    public ResponseEntity<CinemaHallDto> saveCinemaHall(@RequestBody CinemaHallDto cinemaHallDto) {
        CinemaHallDto savedCinemaHall = cinemaHallService.createCinemaHall(cinemaHallDto);
        return new ResponseEntity<>(savedCinemaHall, HttpStatus.CREATED);
    }

    @GetMapping("/cinemaHall/{cinemaHallId}")
    public ResponseEntity<CinemaHallDto> getCinemaHall(@PathVariable("cinemaHallId") Long cinemaHallId) {
        CinemaHallDto cinemaHall = cinemaHallService.getCinemaHallById(cinemaHallId);
        return ResponseEntity.ok(cinemaHall);
    }

    @DeleteMapping("/cinemaHall/{cinemaHallId}")
    public ResponseEntity<String> deleteMovie(@PathVariable("cinemaHallId") Long cinemaHallId) {
        cinemaHallService.deleteCinemaHall(cinemaHallId);
        return ResponseEntity.ok("CinemaHall deleted successfully!");
    }

    @PutMapping("/cinemaHall/{cinemaHallId}")
    public ResponseEntity<CinemaHallDto> updateCinemaHall(@PathVariable("cinemaHallId") Long cinemaHallId,
                                                @RequestBody CinemaHallDto cinemaHallDto) {
        CinemaHallDto cinemaHall = cinemaHallService.updateCinemaHall(cinemaHallId, cinemaHallDto);
        return ResponseEntity.ok(cinemaHall);
    }


}
