package com.Oreki5.RestaurantManagementSystem.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Oreki5.RestaurantManagementSystem.Models.Reservations;
import com.Oreki5.RestaurantManagementSystem.Repo.ReservationsRepo;

@Service
public class ReservationsService {

    @Autowired
    private ReservationsRepo reservationsRepo;

    public Reservations saveReservation(Reservations reservation){
        return reservationsRepo.save(reservation);
    }

    public Reservations getByName(String name){
        return reservationsRepo.findByName(name);
    }

    public void deleteReservation(long id){
        reservationsRepo.deleteById(id);
    }
}
