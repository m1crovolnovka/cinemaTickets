package io.denchik.cinemakursach.controller;

import io.denchik.cinemakursach.dto.TicketDto;
import io.denchik.cinemakursach.models.UserEntity;
import io.denchik.cinemakursach.models.enums.PaymentType;
import io.denchik.cinemakursach.security.SecurityUtil;
import io.denchik.cinemakursach.service.BookingService;
import io.denchik.cinemakursach.service.TicketService;
import io.denchik.cinemakursach.service.UserService;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping()
public class BookingController {
    private UserService userService;
    private TicketService ticketService;
    private BookingService bookingService;
    public BookingController(BookingService bookingService, UserService userService, TicketService ticketService) {
        this.bookingService = bookingService;
        this.userService = userService;
        this.ticketService = ticketService;
    }


    @PostMapping("/orders/{ticketId}")
    public ResponseEntity<String> addOrder(@PathVariable("ticketId") Long ticketId, @RequestBody String string) {
        UserEntity user = userService.findByUsername(SecurityUtil.getSessionUser());
        if(string.equals("CREDIT_CARD")) {
            ticketService.addTicketToBooking(user.getBooking().getId(), ticketId, PaymentType.CREDIT_CARD);
        }
        else{
            ticketService.addTicketToBooking(user.getBooking().getId(), ticketId, PaymentType.ERIP);
        }
        return ResponseEntity.ok("Билету успешно добавились в заказы");
    }

    @PostMapping("/orders/fullCart/{cartId}")
    public ResponseEntity<String> addOrders(@PathVariable("cartId") Long cartId, @RequestBody String string) {
        UserEntity user = userService.findByUsername(SecurityUtil.getSessionUser());
        if(string.equals("CREDIT_CARD")) {
            ticketService.addTicketToAllBooking(user.getBooking().getId(), cartId, PaymentType.CREDIT_CARD);
        }
        else{
            ticketService.addTicketToAllBooking(user.getBooking().getId(), cartId, PaymentType.ERIP);
        }
        return ResponseEntity.ok("Билет успешно добавились в заказы");
    }


    @GetMapping("/orders")
    public ResponseEntity<List<TicketDto>> orders() {
        UserEntity user = userService.findByUsername(SecurityUtil.getSessionUser());
        List<TicketDto> tickets=ticketService.findTicketsByBookingId(user.getBooking().getId());
        return ResponseEntity.ok(tickets);
    }
    @PostMapping("/orders/delete/{ticketId}")
    public ResponseEntity<String> deleteOrder(@PathVariable("ticketId") Long ticketId) {
        bookingService.deleteBooking(ticketId);
        return ResponseEntity.ok("Билет успешно возвращен");
    }


}



