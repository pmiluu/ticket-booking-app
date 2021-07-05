package com.bookingApp.bookingApp.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Data
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String title;

    @JsonManagedReference
    @OneToMany(mappedBy = "movie")
    private List<Screening> screenings;

    public Movie(String title){
        this.title = title;
    }
}
