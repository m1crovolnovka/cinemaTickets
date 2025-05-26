package io.denchik.cinemakursach.controller;


import io.denchik.cinemakursach.dto.TicketDto;
import io.denchik.cinemakursach.models.UserEntity;
import io.denchik.cinemakursach.security.SecurityUtil;
import io.denchik.cinemakursach.service.CartService;
import io.denchik.cinemakursach.service.TicketService;
import io.denchik.cinemakursach.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin("*")
@RestController
@RequestMapping()
public class CartController {
    private final UserService userService;
    private final TicketService ticketService;
    private final CartService cartService;

    public CartController(UserService userService, TicketService ticketService, CartService cartService) {
        this.userService = userService;
        this.ticketService = ticketService;
        this.cartService = cartService;
    }

    @PostMapping("/cart/{ticketId}")
    public ResponseEntity<String> addTicketToCart(@PathVariable("ticketId") Long ticketId) {
        UserEntity user = userService.findByUsername(SecurityUtil.getSessionUser());
        System.out.println(user.getCart().getId());
        cartService.addTicketToCart(user.getCart().getId(), ticketId);
        return ResponseEntity.ok("Вы добавили билет");
    }

    @PostMapping("/cart/delete/{ticketId}")
    public ResponseEntity<String> deleteTicketFromCart(@PathVariable("ticketId") Long ticketId) {
        cartService.delete(ticketId);
        return ResponseEntity.ok("Вы удалили билет");
    }


    @GetMapping("/cart")
    public ResponseEntity<List<TicketDto>> cartDetail() {
        UserEntity user = userService.findByUsername(SecurityUtil.getSessionUser());
        List<TicketDto> tickets = ticketService.findTicketsByCartId(user.getCart().getId());
        return ResponseEntity.ok(tickets);
    }

    @PostMapping("/cart/delete")
    public ResponseEntity<String> clearCart() {
        UserEntity user = userService.findByUsername(SecurityUtil.getSessionUser());
        cartService.deleteAll(user.getCart().getId());
        return ResponseEntity.ok("Данные из корзины успешно удалены");
    }

    @GetMapping("/cart/getId")
    public ResponseEntity<Long> getCartId() {
        UserEntity user = userService.findByUsername(SecurityUtil.getSessionUser());
        Long cartId = user.getCart().getId();
        return ResponseEntity.ok(cartId);
    }


}
