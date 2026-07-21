package com.Oreki5.RestaurantManagementSystem.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Oreki5.RestaurantManagementSystem.Models.Reservations;
import com.Oreki5.RestaurantManagementSystem.Service.ReservationsService;

@RestController
@RequestMapping("/reserve")
public class ReservationsController {

    @Autowired
    private ReservationsService reservationService;

    

    @PostMapping
    public Reservations createReservation(@RequestBody Reservations reservations){
        return reservationService.saveReservation(reservations);
    }

    @GetMapping("/{name}")
    public Reservations getByName(@PathVariable String name){
        return reservationService.getByName(name);
    }

    @PutMapping
    public Reservations updateReservation(@RequestBody Reservations reservations){
        return reservationService.saveReservation(reservations);
    }
    
    @DeleteMapping("/{id}")
    public void deleteReservation(@PathVariable long id){
        reservationService.deleteReservation(id);
    }



}
