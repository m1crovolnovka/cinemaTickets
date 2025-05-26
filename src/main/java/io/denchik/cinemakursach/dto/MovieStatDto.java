package io.denchik.cinemakursach.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MovieStatDto {
    private Long id;
    private String title;
    private Double revenue;
    private Integer soldTickets;

}
