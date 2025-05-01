package io.denchik.cinemakursach.service.impl;


import io.denchik.cinemakursach.repository.TicketRepository;
import io.denchik.cinemakursach.service.CartService;
import io.denchik.cinemakursach.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    private TicketRepository ticketRepository;
    private TicketService ticketService;
    @Autowired
    public CartServiceImpl(TicketRepository ticketRepository, TicketService ticketService) {
        this.ticketRepository = ticketRepository;
        this.ticketService = ticketService;
    }

    @Override
    public void delete(Long ticketId) {
//        Ticket ticket = ticketRepository.findById(ticketId).get();
//        ticket.setStatus(true);
//        ticket.setCart(null);
//        ticketRepository.save(ticket);
    }

    @Override
    public void deleteAll(Long cartId) {
//        List<TicketDto> tickets = ticketService.findTicketsByCartId(cartId);
//        for(TicketDto ticket : tickets) {
//            ticket.setStatus(true);
//            ticket.setCart(null);
//            ticketRepository.save(TicketMapper.mapToTicket(ticket));
//        }
    }
}
