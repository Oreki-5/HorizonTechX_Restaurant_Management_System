package com.Oreki5.RestaurantManagementSystem.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

import com.Oreki5.RestaurantManagementSystem.Models.Reservations;
@Repository
public interface ReservationsRepo extends JpaRepository<Reservations, Long> {

    Reservations findByName(String name);



}
