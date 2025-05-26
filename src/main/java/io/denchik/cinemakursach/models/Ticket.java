package io.denchik.cinemakursach.models;

import io.denchik.cinemakursach.models.enums.PaymentType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date ;
    private LocalTime time ;
    private Integer row;
    private Integer col;
    private Boolean status;
    @ManyToOne(fetch = FetchType.LAZY)
    private CinemaHall cinemaHall;
    @ManyToOne()
    private Movie movie;
    @ManyToOne(fetch = FetchType.LAZY)
    private Cart cart;
    @ManyToOne(fetch = FetchType.LAZY)
    private Booking booking;
    @Enumerated(EnumType.STRING)
    private PaymentType payType;
}
