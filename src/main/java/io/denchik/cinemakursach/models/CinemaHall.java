package io.denchik.cinemakursach.models;

import io.denchik.cinemakursach.models.enums.PaymentType;
import lombok.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class CinemaHall {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer countOfSeat;
    @OneToMany(mappedBy = "cinemaHall",cascade = CascadeType.REMOVE)
    private List<Ticket> tickets = new ArrayList<>();
}
