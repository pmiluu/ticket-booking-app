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
public class Seat {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private int seatNumber;

    @ManyToOne
    @JoinColumn(name = "room_id")
    @JsonIgnoreProperties("seats")
    private Room room;

    @OneToMany(mappedBy = "seat")
    @JsonIgnoreProperties("seat")
    private List<SeatScreening> seatScreenings;

    public Seat(int seatNumber, Room room){
        this.seatNumber = seatNumber;
        this.room = room;
    }
}
