package com.Oreki5.RestaurantManagementSystem.Models;

import java.sql.Date;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Reservations {
    private long id;

    private String name;
    private int numberOfCustomers;
    private Date bookedDateTime;
    private String status;
}
