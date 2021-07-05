package com.bookingApp.bookingApp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Data
public class SeatScreening {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "screening_id")
    private Screening screening;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "seat_id")
    private Seat seat;

    @JsonManagedReference
    @OneToOne(mappedBy = "seatScreening")
    private Ticket ticket;

    public SeatScreening(Screening screening, Seat seat){
        this.screening = screening;
        this.seat = seat;
    }
}
