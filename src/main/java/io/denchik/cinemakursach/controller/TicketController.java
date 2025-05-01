//package io.denchik.cinemakursach.controller;
//
//import org.kursach.zakazbiletov.kursach.dto.MovieDto;
//import org.kursach.zakazbiletov.kursach.dto.TicketDto;
//import org.kursach.zakazbiletov.kursach.models.Ticket;
//import org.kursach.zakazbiletov.kursach.models.UserEntity;
//import org.kursach.zakazbiletov.kursach.repository.TicketRepository;
//import org.kursach.zakazbiletov.kursach.security.SecurityUtil;
//import org.kursach.zakazbiletov.kursach.service.MovieService;
//import org.kursach.zakazbiletov.kursach.service.TicketService;
//import org.kursach.zakazbiletov.kursach.service.UserService;
//import org.kursach.zakazbiletov.kursach.service.impl.CartServiceImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Comparator;
//import java.util.List;
//import java.util.Set;
//import java.util.stream.Collectors;
//
//@Controller
//public class TicketController {
//
//    private TicketService ticketService;
//    private UserService userService;
//    private MovieService movieService;
//
//    @Autowired
//    public TicketController(TicketService ticketService, UserService userService, MovieService movieService) {
//        this.userService = userService;
//        this.ticketService = ticketService;
//        this.movieService = movieService;
//
//    }
//
//
//    @GetMapping("/tickets/{movieId}/new")
//    public String createTicketForm(@PathVariable("movieId") Long movieId, Model model) {
//        Ticket ticket = new Ticket();
//        model.addAttribute("movieId", movieId);
//        model.addAttribute("ticket", ticket);
//        return "tickets-create";
//    }
//
//    @GetMapping("/movies/{movieId}/tickets")
//    public String movieDetail(@PathVariable("movieId") Long movieId, Model model) {
//        List<TicketDto> tickets = ticketService.findTicketsByMovieId(movieId);
//        Set<String> sectors = tickets.stream().map(TicketDto::getSector).collect(Collectors.toSet());
//        for (String sec : sectors)
//            model.addAttribute(sec, tickets.stream().filter(loc -> loc.getSector().equals(sec)).anyMatch(loc -> loc.getStatus().equals(true)));
//        MovieDto movieDto = movieService.findMovieById(movieId);
//        model.addAttribute("movie", movieDto);
//        if (tickets.getFirst().getName().equals("Prime Hall")) {
//            return "tickets/Prime_Hall";
//        }
//        if (tickets.getFirst().getName().equals("Минск-Арена")) {
//            return "tickets/Minsk-Arena";
//        } else {
//            return "redirect:/movies/" + movieId + "/tickets/" + tickets.getFirst().getSector();
//        }
//    }
//
//    @GetMapping("/movies/{movieId}/tickets/{sector}")
//    public String movieDetailSector(@PathVariable("movieId") Long movieId, @PathVariable("sector") String sector, Model model) {
//        List<TicketDto> tickets = ticketService.findTicketsByMovieId(movieId).stream().filter(loc -> loc.getSector().equals(sector)).collect(Collectors.toList());
//        MovieDto movieDto = movieService.findMovieById(movieId);
//        model.addAttribute("movie", movieDto);
//        if (tickets.getFirst().getName().equals("Prime Hall")) {
//            switch (sector) {
//                case "Balcony1", "Balcony2":
//                    tickets = tickets.stream().sorted(Comparator.comparing(TicketDto::getNumOfSeat)).collect(Collectors.toList());
//                    model.addAttribute("tickets", tickets);
//                    return "tickets/Prime_Hall_Balcony1";
//                case "Balcony3":
//                    tickets = tickets.stream().sorted(Comparator.comparing(TicketDto::getNumOfSeat)).collect(Collectors.toList());
//                    model.addAttribute("tickets", tickets);
//                    return "tickets/Prima_Hall_Balcony3";
//                case "Dancefloor":
//                    Long ticketId = tickets.stream().filter(loc -> loc.getStatus().equals(true)).findFirst().get().getId();
//                    return "redirect:/movies/{movieId}/tickets/Dancefloor/" + ticketId;
//            }
//        }
//        if (tickets.getFirst().getName().equals("Минск-Арена")) {
//            switch (sector) {
//                case "Sector1", "Sector3":
//                    tickets = tickets.stream().sorted(Comparator.comparing(TicketDto::getNumOfSeat)).collect(Collectors.toList());
//                    model.addAttribute("tickets", tickets);
//                    return "tickets/Minsk-Arena_Sector13";
//                case "Sector2":
//                    tickets = tickets.stream().sorted(Comparator.comparing(TicketDto::getNumOfSeat)).collect(Collectors.toList());
//                    model.addAttribute("tickets", tickets);
//                    return "tickets/Minsk-Arena_Sector2";
//                case "Balcony1", "Balcony2":
//                    tickets = tickets.stream().sorted(Comparator.comparing(TicketDto::getNumOfSeat)).collect(Collectors.toList());
//                    model.addAttribute("tickets", tickets);
//                    return "tickets/Minsk-Arena_Balcony12";
//            }
//        }
//        if (tickets.getFirst().getName().equals("Дворец Республики"))  {
//            tickets = tickets.stream().sorted(Comparator.comparing(TicketDto::getNumOfSeat)).collect(Collectors.toList());
//            model.addAttribute("tickets", tickets);
//            return "tickets/Palace_of_the_Republic";
//        }
//        return "redirect:/movies/" + movieId + "/tickets";
//    }
//
//
//    @GetMapping("/movies/{movieId}/tickets/{sector}/{ticketId}")
//    public String addToCart(@PathVariable("ticketId") Long ticketId) {
//        UserEntity user = userService.findByUsername(SecurityUtil.getSessionUser());
//        TicketDto ticketDto = ticketService.findTicketsById( ticketId);
//        ticketService.updateTicket(ticketDto);
//        ticketService.addTicketToCart(user.getCart().getId(), ticketId);
//        return "redirect:/cart";
//    }
//
//}
