package io.denchik.cinemakursach.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    @Column(columnDefinition = "text")
    private String photoUrl;

    @Column(columnDefinition = "text")
    private String content;

    private Double cost;

    private Integer duration;

    private String videoId;

    @OneToMany(mappedBy = "movie",cascade = CascadeType.REMOVE)
    private List<Ticket> tickets = new ArrayList<>();
}
