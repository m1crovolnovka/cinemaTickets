package io.denchik.cinemakursach.repository;

import io.denchik.cinemakursach.dto.MovieStatDto;
import io.denchik.cinemakursach.models.Movie;
import io.denchik.cinemakursach.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    @Query("SELECT t.date  from Ticket t GROUP BY t.date")
    List<LocalDate> searchDates();

    @Query("SELECT t from Ticket t WHERE t.row = 1 AND t.col=1 and t.movie.id = :moveId ")
    List<Ticket> searchCinoSession(Long moveId);

    @Query("SELECT COUNT(t.status) from Ticket t WHERE t.status = true AND t.movie.id = :movieId")
    Integer findPurchased(Long movieId);

    @Query("SELECT COUNT(t.status) from Ticket t WHERE t.date = :date AND t.time = :time AND t.status=true")
    Integer findPurchasedCinemaHall(LocalDate date, LocalTime time);
}
