package com.bookingApp.bookingApp.repository;

import com.bookingApp.bookingApp.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room,Long> {
}
