package com.Oreki5.RestaurantManagementSystem.Models;

import java.time.Instant;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Reservations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private int numberOfCustomers;
    private Instant bookedDateTime;
    private String status;

    @Column
    @CreationTimestamp(source = SourceType.DB)
    private Instant createdDate;

}
