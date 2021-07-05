package com.bookingApp.bookingApp.model;

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
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private int roomNumber;

    @OneToMany(mappedBy = "room")
    @JsonIgnoreProperties("room")
    private List<Seat> seats;

    public Room(int roomNumber){
        this.roomNumber = roomNumber;
    }
}
