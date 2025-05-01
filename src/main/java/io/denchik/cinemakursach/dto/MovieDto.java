package io.denchik.cinemakursach.dto;


import io.denchik.cinemakursach.models.Ticket;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class MovieDto {
    private Long id;
    @NotEmpty(message = "Название мероприятия не должно быть пустым")
    private String title;
    @NotEmpty(message = "Ссылка на фото не должна быть пустой")
    private String photoUrl;
    @NotEmpty(message = "Описание не должно быть пустым")
    private String content;
    @NotNull(message = "Цена не должна быть пустая")
    private Double cost;
    private List<Ticket> tickets;
}
