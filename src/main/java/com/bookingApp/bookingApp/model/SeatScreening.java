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
import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class SeatScreening {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private boolean reserved;

    @ManyToOne
    @JoinColumn(name = "screening_id")
    @JsonIgnoreProperties("seatScreenings")
    private Screening screening;

    @ManyToOne
    @JoinColumn(name = "seat_id")
    @JsonIgnoreProperties("seatScreenings")
    private Seat seat;

    @OneToOne(mappedBy = "seatScreening")
    @JsonIgnoreProperties("seatScreenings")
    private Ticket ticket;

    public SeatScreening(Screening screening,boolean reserved, Seat seat){
        this.screening = screening;
        this.reserved = reserved;
        this.seat = seat;
    }
}
