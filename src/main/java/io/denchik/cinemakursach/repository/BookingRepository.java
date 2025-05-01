package io.denchik.cinemakursach.repository;

import io.denchik.cinemakursach.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
