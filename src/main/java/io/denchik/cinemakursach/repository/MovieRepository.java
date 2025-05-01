package io.denchik.cinemakursach.repository;

import io.denchik.cinemakursach.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query("SELECT m from Movie m WHERE m.title LIKE CONCAT('%', :query, '%')")
    List<Movie> searchMovies(String query);
}
