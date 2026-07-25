package com.Oreki5.RestaurantManagementSystem.Models;

import java.time.Instant;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class BillRecords {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    private long tableId;

    private int orderPrice;

    @CreationTimestamp(source=SourceType.DB)
    private Instant createdDate;

    public BillRecords(){

    }

    public BillRecords(long tableId, int orderPrice) {
        this.tableId = tableId;
        this.orderPrice = orderPrice;
    }
    
}
