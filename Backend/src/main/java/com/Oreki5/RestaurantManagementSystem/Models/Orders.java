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
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long menuItemId;

    private int quantity;

    private boolean forceOrder;

    private long tableId;

    private String status = "active";

    private int orderPrice;

    @Column
    @CreationTimestamp(source = SourceType.DB)
    private Instant createdDate;

}
