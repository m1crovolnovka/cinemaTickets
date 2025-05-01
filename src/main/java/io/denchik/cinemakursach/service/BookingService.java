package io.denchik.cinemakursach.service;


import io.denchik.cinemakursach.dto.CardDto;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

public interface BookingService {
    boolean validateNumber(Long num);
    void deleteBooking(Long ticketId);
    String validCard(CardDto card, BindingResult bindingResult, Model model);
}
