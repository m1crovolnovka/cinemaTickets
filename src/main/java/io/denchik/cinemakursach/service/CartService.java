package io.denchik.cinemakursach.service;

public interface CartService {
    void delete(Long ticketId);
    void deleteAll(Long cartId);
    void addTicketToCart(Long CartId, Long ticketDto);
}
