//package io.denchik.cinemakursach.controller;
//
//import org.kursach.zakazbiletov.kursach.dto.TicketDto;
//import org.kursach.zakazbiletov.kursach.models.UserEntity;
//import org.kursach.zakazbiletov.kursach.security.SecurityUtil;
//import org.kursach.zakazbiletov.kursach.service.CartService;
//import org.kursach.zakazbiletov.kursach.service.TicketService;
//import org.kursach.zakazbiletov.kursach.service.UserService;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//@Controller
//public class CartController {
//    private UserService userService;
//    private TicketService ticketService;
//    private CartService cartService;
//
//    public CartController(UserService userService, TicketService ticketService, CartService cartService) {
//        this.userService = userService;
//        this.ticketService = ticketService;
//        this.cartService = cartService;
//    }
//
//    @GetMapping("/cart")
//    public String cartDetail( Model model) {
//        UserEntity user = userService.findByUsername(SecurityUtil.getSessionUser());
//        List<TicketDto> tickets=ticketService.findTicketsByCartId(user.getCart().getId());
//        Double sum = 0.0;
//        if(tickets.size()!=0){
//            sum = tickets.stream().mapToDouble(loc -> loc.getMovie().getCost()).sum();
//        }
//        model.addAttribute("sum",sum);
//        model.addAttribute("tickets", tickets);
//        return "cart-list";
//    }
//    @GetMapping("/cart/{ticketId}/delete")
//    public String deleteMovie(@PathVariable("ticketId") Long ticketId) {
//        cartService.delete(ticketId);
//        return "redirect:/cart";
//    }
//    @GetMapping("/cart/delete")
//    public String deleteALLMovie() {
//        UserEntity user = userService.findByUsername(SecurityUtil.getSessionUser());
//        cartService.deleteAll(user.getCart().getId());
//        return "redirect:/cart";
//    }
//
//}
