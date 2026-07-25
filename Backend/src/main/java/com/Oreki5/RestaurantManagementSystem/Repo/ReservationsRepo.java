package com.Oreki5.RestaurantManagementSystem.Repo;

import java.time.Instant;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Oreki5.RestaurantManagementSystem.Models.Reservations;

@Repository
public interface ReservationsRepo extends JpaRepository<Reservations, Long> {

    Reservations findByName(String name);

    List<Reservations> findAllByName(String name);

    @Query(nativeQuery = true, value = "SELECT * from reservations where booked_date_time > :date")
    List<Reservations> findUpcomingReservations(@Param("date") String date);

}
