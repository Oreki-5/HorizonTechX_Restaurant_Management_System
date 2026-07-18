package com.Oreki5.RestaurantManagementSystem.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Oreki5.RestaurantManagementSystem.Models.Reservations;

public interface ReservationsRepo extends JpaRepository<Reservations, Long> {

}
