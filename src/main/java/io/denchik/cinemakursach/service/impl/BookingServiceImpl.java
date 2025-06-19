package io.denchik.cinemakursach.service.impl;


import io.denchik.cinemakursach.models.Ticket;
import io.denchik.cinemakursach.repository.TicketRepository;
import io.denchik.cinemakursach.service.BookingService;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import io.denchik.cinemakursach.dto.CardDto;

import java.time.YearMonth;

@Service
public class BookingServiceImpl implements BookingService {
    TicketRepository ticketRepository;

    public BookingServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public void deleteBooking(Long ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId).get();
            ticket.setStatus(false);
            ticket.setBooking(null);
            ticket.setPayType(null);
            ticketRepository.save(ticket);
    }

}
