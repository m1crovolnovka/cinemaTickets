package io.denchik.cinemakursach.models;

import io.denchik.cinemakursach.models.enums.PaymentType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    private Integer numOfSeat;
    private Boolean status;
    @ManyToOne
    private CinemaHall cinemaHall;
    @ManyToOne
    private Movie movie;
    @ManyToOne
    private Cart cart;
    @ManyToOne
    private Booking booking;
    @Enumerated(EnumType.STRING)
    private PaymentType payType;
}
