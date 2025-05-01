package io.denchik.cinemakursach.repository;

import io.denchik.cinemakursach.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
