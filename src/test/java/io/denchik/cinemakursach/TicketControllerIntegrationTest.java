package io.denchik.cinemakursach;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.denchik.cinemakursach.dto.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class TicketControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser(username = "admin", authorities = {"ADMIN"})
    void testAddShedule() throws Exception {
        DateRequestDto request = new DateRequestDto();
        request.setFullDate(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));

        mockMvc.perform(post("/tickets/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }


    @Test
    @WithMockUser(username = "admin", authorities = {"USER"})
    void testTicketsDates() throws Exception {
        mockMvc.perform(get("/tickets/dates"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", isA(List.class)));
    }

    @Test
    void testTicketsForChooseMenu() throws Exception {
        DateRequestDto request = new DateRequestDto();
        request.setFullDate(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));

        mockMvc.perform(put("/tickets/chooseMenu")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", authorities = {"ADMIN"})
    void testTicketsForMoviesStat() throws Exception {
        mockMvc.perform(get("/tickets/moviesStat"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", isA(List.class)));
    }

    @Test
    @WithMockUser(username = "admin", authorities = {"ADMIN"})
    void testTicketsForCinemaHallStat() throws Exception {
        mockMvc.perform(get("/tickets/cinemaHallStat"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", isA(List.class)));
    }


}
