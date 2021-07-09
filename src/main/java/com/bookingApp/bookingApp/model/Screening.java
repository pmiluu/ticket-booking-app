package com.bookingApp.bookingApp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class Screening {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private LocalDateTime screeningDate;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    @JsonIgnoreProperties("screenings")
    private Movie movie;

    @OneToMany(mappedBy = "screening")
    @JsonIgnoreProperties("screening")
    private List<SeatScreening> seatScreenings;

    public Screening(LocalDateTime screeningDate, Movie movie){
        this.screeningDate = screeningDate;
        this.movie = movie;
    }
}
