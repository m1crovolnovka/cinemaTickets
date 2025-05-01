package io.denchik.cinemakursach.service.impl;


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
    public boolean validateNumber(Long num){
        String number = String.valueOf(num);
        int sum = 0;
        boolean alternateDigit = false;
        for(int i = number.length()-1; i >= 0; i--){
            int digit = Integer.parseInt(number.substring(i, i+1));
            if(alternateDigit){
                digit *= 2;
                if(digit > 9){
                    digit = 1 + (digit % 10);
                }
            }
            sum += digit;
            alternateDigit = !alternateDigit;
        }
        return (sum % 10 == 0);
    }

    @Override
    public void deleteBooking(Long ticketId) {
//        Ticket ticket = ticketRepository.findById(ticketId).get();
//            ticket.setStatus(true);
//            ticket.setBooking(null);
//            ticket.setPayType(null);
//            ticketRepository.save(ticket);
    }

    @Override
    public String validCard(CardDto card, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("card", card);
            return "creditCardForm";
        }
        if( !validateNumber(Long.valueOf(card.getCardNumber()))){
            model.addAttribute("card", card);
            bindingResult.rejectValue("cardNumber", "invalid", "Такой карты не существует");
            return "creditCardForm";
        }
        YearMonth yearMonth = YearMonth.now();
        if(card.getDate().isBefore(yearMonth)){
            model.addAttribute("card", card);
            bindingResult.rejectValue("date", "invalid", "Дата карты уже истекла");
            return "creditCardForm";
        }
        return null;
    }
}
