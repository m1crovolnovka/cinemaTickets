package io.denchik.cinemakursach.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchDto {

    private Long id;
    private Long movieId;
    private String photoUrl;
    private String name;
    private Double cost;
    private LocalDateTime dateTime;
    private CinemaHallDto cinemaHall;

    }
