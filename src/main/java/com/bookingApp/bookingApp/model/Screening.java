package com.bookingApp.bookingApp.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Screening {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private LocalDate screeningDate;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @OneToMany(mappedBy = "screening")
    private List<SeatScreening> seatScreenings;
}
