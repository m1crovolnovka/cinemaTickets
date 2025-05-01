package io.denchik.cinemakursach.service;

public interface CartService {
    void delete(Long ticketId);
    void deleteAll(Long cartId);
}
