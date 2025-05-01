//package io.denchik.cinemakursach.controller;
//
//import jakarta.validation.Valid;
//import org.kursach.zakazbiletov.kursach.dto.CardDto;
//import org.kursach.zakazbiletov.kursach.dto.TicketDto;
//import org.kursach.zakazbiletov.kursach.models.UserEntity;
//import org.kursach.zakazbiletov.kursach.models.enums.PaymentType;
//import org.kursach.zakazbiletov.kursach.security.SecurityUtil;
//import org.kursach.zakazbiletov.kursach.service.BookingService;
//import org.kursach.zakazbiletov.kursach.service.TicketService;
//import org.kursach.zakazbiletov.kursach.service.UserService;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//
//import java.util.List;
//
//@Controller
//public class BookingController {
//    private UserService userService;
//    private TicketService ticketService;
//    private BookingService bookingService;
//    public BookingController(BookingService bookingService, UserService userService, TicketService ticketService) {
//        this.bookingService = bookingService;
//        this.userService = userService;
//        this.ticketService = ticketService;
//    }
//    @GetMapping("/payment/{ticketId}")
//    public String payment(@PathVariable("ticketId") Long ticketId, Model model) {
//        model.addAttribute("ticketId", ticketId);
//        return "payment";
//    }
//
//    @GetMapping("/card/{ticketId}")
//    public String creditcard(@PathVariable("ticketId") Long ticketId, Model model) {
//        CardDto card = new CardDto();
//        model.addAttribute("card", card);
//        model.addAttribute("ticketId", ticketId);
//        return "creditCardForm";
//    }
//    @PostMapping("/card/{ticketId}")
//    public String checkCard(@Valid @ModelAttribute("card") CardDto card, BindingResult bindingResult, @PathVariable("ticketId") Long ticketId, Model model) {
//        String path = bookingService.validCard(card,bindingResult,model);
//        if(path != null)
//            return path;
//        UserEntity user = userService.findByUsername(SecurityUtil.getSessionUser());
//        ticketService.addTicketToBooking(user.getBooking().getId(), ticketId,PaymentType.CREDIT_CARD);
//        return "redirect:/orders";
//    }
//
//    @GetMapping("/erip/{ticketId}")
//    public String erip(@PathVariable("ticketId") Long ticketId, Model model) {
//        model.addAttribute("ticketId", ticketId);
//        return "erip";
//    }
//    @PostMapping("/erip/{ticketId}")
//    public String checkErip(@PathVariable("ticketId") Long ticketId) {
//        UserEntity user = userService.findByUsername(SecurityUtil.getSessionUser());
//        ticketService.addTicketToBooking(user.getBooking().getId(), ticketId,PaymentType.ERIP);
//        return "redirect:/orders";
//    }
//
//    @GetMapping("/orders")
//    public String orders(Model model) {
//        UserEntity user = userService.findByUsername(SecurityUtil.getSessionUser());
//        List<TicketDto> tickets=ticketService.findTicketsByBookingId(user.getBooking().getId());
//        model.addAttribute("tickets", tickets);
//        model.addAttribute("booking", tickets);
//        return "orders";
//    }
//    @GetMapping("/orders/delete/{ticketId}")
//    public String deleteOrder(@PathVariable("ticketId") Long ticketId, Model model) {
//        bookingService.deleteBooking(ticketId);
//        return "redirect:/orders";
//    }
//    @GetMapping("/paymentAll/{cartId}")
//    public String paymentAll(@PathVariable("cartId") Long cartId, Model model) {
//        model.addAttribute("cartId", cartId);
//        return "payment";
//    }
//
//    @GetMapping("/cardAll/{cartId}")
//    public String creditcardAllMovies(@PathVariable("cartId") Long cartId, Model model) {
//        CardDto card = new CardDto();
//        model.addAttribute("card", card);
//        model.addAttribute("cartId", cartId);
//        return "creditCardForm";
//    }
//    @PostMapping("/cardAll/{cartId}")
//    public String checkCardAllMovies(@Valid @ModelAttribute("card") CardDto card, BindingResult bindingResult, @PathVariable("cartId") Long cartId, Model model) {
//        String path = bookingService.validCard(card,bindingResult,model);
//        if(path != null)
//            return path;
//        UserEntity user = userService.findByUsername(SecurityUtil.getSessionUser());
//        ticketService.addTicketToAllBooking(user.getBooking().getId(), cartId,PaymentType.CREDIT_CARD);
//        return "redirect:/orders";
//    }
//
//    @GetMapping("/eripAll/{cartId}")
//    public String eripAllMovies(@PathVariable("cartId") Long cartId, Model model) {
//        model.addAttribute("cartId", cartId);
//        TicketDto ticket = ticketService.findTicketsByCartId(cartId).getFirst();
//        model.addAttribute("ticket", ticket);
//        return "erip";
//    }
//    @PostMapping("/eripAll/{cartId}")
//    public String checkEripAllMovies(@PathVariable("cartId") Long cartId) {
//        UserEntity user = userService.findByUsername(SecurityUtil.getSessionUser());
//        ticketService.addTicketToAllBooking(user.getBooking().getId(), cartId,PaymentType.ERIP);
//        return "redirect:/orders";
//    }
//
//}
//
//
//
