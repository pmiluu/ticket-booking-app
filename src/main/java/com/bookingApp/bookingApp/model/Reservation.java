package com.bookingApp.bookingApp.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Data
public class Reservation {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String surname;

    @NotNull
    private double totalCost;

    @NotNull
    private LocalDateTime expirationDate;

    @JsonManagedReference
    @OneToMany(mappedBy = "reservation")
    private List<Ticket> tickets;

}
