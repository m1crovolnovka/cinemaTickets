package io.denchik.cinemakursach;

import org.springframework.security.test.context.support.WithMockUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.denchik.cinemakursach.dto.MovieDto;
import io.denchik.cinemakursach.models.Role;
import io.denchik.cinemakursach.models.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class MovieControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private MovieDto sampleMovie;

    @BeforeEach
    void setUp() {
        sampleMovie = MovieDto.builder()
            .title("Inception")
            .cost(51.0)
            .videoId("JnHD_-rjC7Y&t=675s")
            .duration(148)
            .content("Mind-bending thriller")
            .photoUrl("https://example.com/inception.jpg")
            .build();
    }

    @Test
    @WithMockUser(username = "admin", authorities = {"ADMIN"})
    void testCreateAndGetMovie() throws Exception {
        String json = objectMapper.writeValueAsString(sampleMovie);
        String response = mockMvc.perform(post("/movies/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("Inception"))
                .andReturn().getResponse().getContentAsString();

        MovieDto created = objectMapper.readValue(response, MovieDto.class);
        mockMvc.perform(get("/movies/" +created.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Inception"));
    }

    @Test
    void testListMovies() throws Exception {
        mockMvc.perform(get("/movies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", isA(List.class)));
    }


    @Test
    void testSearchMovies() throws Exception {
        mockMvc.perform(get("/movies/search")
                        .param("query", "inception"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", authorities = {"ADMIN"})
    void testUpdateMovie() throws Exception {
        String response = mockMvc.perform(post("/movies/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sampleMovie)))
                .andReturn().getResponse().getContentAsString();

        MovieDto created = objectMapper.readValue(response, MovieDto.class);
        created.setTitle("Updated Title");
        mockMvc.perform(put("/movies/" + created.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(created)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Updated Title"));
    }

    @Test
    @WithMockUser(username = "admin", authorities = {"ADMIN"})
    void testDeleteMovie() throws Exception {
        String response = mockMvc.perform(post("/movies/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sampleMovie)))
                .andReturn().getResponse().getContentAsString();

        MovieDto created = objectMapper.readValue(response, MovieDto.class);
        mockMvc.perform(delete("/movies/" + created.getId()))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Movie deleted successfully!")));
    }
}