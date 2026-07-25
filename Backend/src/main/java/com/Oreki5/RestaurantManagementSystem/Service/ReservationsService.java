package com.Oreki5.RestaurantManagementSystem.Service;

import java.time.Instant;
import java.util.List;

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

    public List<Reservations> getByName(String name){
        return reservationsRepo.findAllByName(name);
    }

    public void deleteReservation(long id){
        reservationsRepo.deleteById(id);
    }

    public List<Reservations> getAll() {
        return reservationsRepo.findAll();
    }

    public List<Reservations> getAllUpcomingReservations(Instant date) {
       return reservationsRepo.findUpcomingReservations(Long.toString(date.toEpochMilli()));
    }

}
