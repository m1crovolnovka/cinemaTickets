package io.denchik.cinemakursach.service;


import io.denchik.cinemakursach.dto.CardDto;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

public interface BookingService {
    void deleteBooking(Long ticketId);
}
