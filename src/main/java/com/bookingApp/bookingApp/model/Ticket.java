package com.bookingApp.bookingApp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor
@Data
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TicketType ticketType;

    @OneToOne
    @JoinColumn(name = "seatScreening_id")
    @JsonIgnoreProperties("tickets")
    private SeatScreening seatScreening;

    @ManyToOne
    @JoinColumn(name = "reservation_id")
    @JsonIgnoreProperties("tickets")
    private Reservation reservation;
}
