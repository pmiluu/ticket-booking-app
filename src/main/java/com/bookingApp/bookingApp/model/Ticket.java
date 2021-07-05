package com.bookingApp.bookingApp.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Data
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private double price;

    @OneToOne
    @JoinColumn(name = "seatScreening_id")
    private SeatScreening seatScreening;

    @ManyToOne
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;
}
