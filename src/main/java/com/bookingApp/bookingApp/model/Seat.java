package com.bookingApp.bookingApp.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Data
public class Seat {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private int seatNumber;

    @NotNull
    private boolean reserved;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @OneToMany(mappedBy = "seat")
    private List<SeatScreening> seatScreenings;

    public Seat(int seatNumber,boolean reserved, Room room){
        this.seatNumber = seatNumber;
        this.reserved = reserved;
        this.room = room;
    }
}
