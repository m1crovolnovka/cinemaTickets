package io.denchik.cinemakursach.dto;

import io.denchik.cinemakursach.models.Movie;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.denchik.cinemakursach.models.Booking;
import io.denchik.cinemakursach.models.Cart;
import io.denchik.cinemakursach.models.enums.PaymentType;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CinemaHallDto {
    private Long id;
    @NotEmpty(message = "Название не может быть пустым")
    private String name;
    private Integer row;
    private Integer col;

}
