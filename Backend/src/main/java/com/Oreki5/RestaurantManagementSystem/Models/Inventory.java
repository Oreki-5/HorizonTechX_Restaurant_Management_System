package com.Oreki5.RestaurantManagementSystem.Models;

import java.time.Instant;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Inventory {
    @Id
    private int itemId;

    private String itemName;

    private String unitOfMeaure;

    private int currentStock;

    private int lastUpdatedStock;

    @Column
    @CreationTimestamp(source = SourceType.DB)
    private Instant updatedAt;

}
