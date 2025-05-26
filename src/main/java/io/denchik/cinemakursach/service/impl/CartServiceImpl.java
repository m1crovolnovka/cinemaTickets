package io.denchik.cinemakursach.service.impl;


import io.denchik.cinemakursach.dto.TicketDto;
import io.denchik.cinemakursach.mapper.TicketMapper;
import io.denchik.cinemakursach.models.Cart;
import io.denchik.cinemakursach.models.Ticket;
import io.denchik.cinemakursach.repository.CartRepository;
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
    private CartRepository cartRepository;

    @Autowired
    public CartServiceImpl(TicketRepository ticketRepository, TicketService ticketService, CartRepository cartRepository) {
        this.ticketRepository = ticketRepository;
        this.ticketService = ticketService;
        this.cartRepository = cartRepository;
    }

    @Override
    public void delete(Long ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId).get();
        ticket.setCart(null);
        ticketRepository.save(ticket);
    }

    @Override
    public void deleteAll(Long cartId) {
        List<TicketDto> tickets = ticketService.findTicketsByCartId(cartId);
        for(TicketDto ticket : tickets) {
            Ticket tick = ticketRepository.findById(ticket.getId()).get();
            tick.setCart(null);
            ticketRepository.save(tick);
        }
    }

    @Override
    public void addTicketToCart(Long cartId, Long ticketId) {
        Cart cart = cartRepository.findById(cartId).get();
        Ticket ticket = ticketRepository.findById(ticketId).get();
        ticket.setCart(cart);
        ticketRepository.save(ticket);
    }
}
