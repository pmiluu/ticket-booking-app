package com.bookingApp.bookingApp.repository;

import com.bookingApp.bookingApp.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket,Long> {
}
