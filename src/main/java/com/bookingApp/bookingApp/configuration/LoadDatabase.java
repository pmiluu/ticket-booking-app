package com.bookingApp.bookingApp.configuration;

import com.bookingApp.bookingApp.model.*;
import com.bookingApp.bookingApp.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(MovieRepository movieRepository, ScreeningRepository screeningRepository,
                                   RoomRepository roomRepository, SeatRepository seatRepository,
                                   SeatScreeningRepository seatScreeningRepository
                                   ){
        // movies
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie("Avatar"));
        movies.add(new Movie("The Green Mile"));
        movies.add(new Movie("Forrest Gump"));
        movies.add(new Movie("The Shawshank Redemption"));
        movies.add(new Movie("Lion King"));

        // screenings

        List<Screening> screenings = new ArrayList<>();
        screenings.add(new Screening(LocalDateTime.of(2021, Month.JULY,5,12,00),movies.get(0)));
        screenings.add(new Screening(LocalDateTime.of(2021, Month.JULY,6,16,00),movies.get(1)));
        screenings.add(new Screening(LocalDateTime.of(2021, Month.JULY,7,13,30),movies.get(1)));
        screenings.add(new Screening(LocalDateTime.of(2021, Month.JULY,14,18,00),movies.get(2)));
        screenings.add(new Screening(LocalDateTime.of(2021, Month.JULY,15,11,00),movies.get(1)));
        screenings.add(new Screening(LocalDateTime.of(2021, Month.JULY,20,20,00),movies.get(0)));
        screenings.add(new Screening(LocalDateTime.of(2021, Month.JULY,21,12,00),movies.get(3)));
        screenings.add(new Screening(LocalDateTime.of(2021, Month.JULY,22,15,00),movies.get(3)));
        screenings.add(new Screening(LocalDateTime.of(2021, Month.JULY,23,19,00),movies.get(3)));



        // rooms
        List<Room> rooms = new ArrayList<>();
        rooms.add(new Room(1));
        rooms.add(new Room(2));
        rooms.add(new Room(3));

        // seats
        List<Seat> seats = new ArrayList<>();
        for(int i=0;i<rooms.size();i++){
            List<Seat> seatsTmp = new ArrayList<>();
            for(int j=0;j<10;j++){
                seatsTmp.add(new Seat(j+1,rooms.get(i)));
            }
            seatsTmp.forEach(seat -> seats.add(seat));
            rooms.get(i).setSeats(seatsTmp);
        }

        // seatScreening
        List<SeatScreening> seatScreenings = new ArrayList<>();
        for (Seat seat:rooms.get(0).getSeats()) {
            seatScreenings.add(new SeatScreening(screenings.get(0),false,seat));
            seatScreenings.add(new SeatScreening(screenings.get(1),false,seat));
            seatScreenings.add(new SeatScreening(screenings.get(6),false,seat));
        }
        for (Seat seat:rooms.get(1).getSeats()) {
            seatScreenings.add(new SeatScreening(screenings.get(2),false,seat));
            seatScreenings.add(new SeatScreening(screenings.get(3),false,seat));
            seatScreenings.add(new SeatScreening(screenings.get(7),false,seat));
        }
        for (Seat seat:rooms.get(2).getSeats()) {
            seatScreenings.add(new SeatScreening(screenings.get(4),false,seat));
            seatScreenings.add(new SeatScreening(screenings.get(5),false,seat));
            seatScreenings.add(new SeatScreening(screenings.get(8),false,seat));
        }

        return args -> {
            log.info("Preloading movies");
            movieRepository.saveAll(movies);
            log.info("Preloading screenings");
            screeningRepository.saveAll(screenings);
            log.info("Preloading rooms");
            roomRepository.saveAll(rooms);
            log.info("Preloading seats");
            seatRepository.saveAll(seats);
            log.info("Preloading seatScreenings");
            seatScreeningRepository.saveAll(seatScreenings);
        };
    }
}
