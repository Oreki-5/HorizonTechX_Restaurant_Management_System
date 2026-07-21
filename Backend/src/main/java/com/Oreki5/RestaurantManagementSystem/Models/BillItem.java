package com.Oreki5.RestaurantManagementSystem.Models;

import lombok.Data;


@Data
public class BillItem {
    private String itemName;
    private int unitPrice;
    private int quantity;
    private int totalPrice;
    

    public BillItem(String itemName, int quantity, int unitPrice, int totalPrice){
        this.itemName = itemName;
        this.quantity = quantity; 
        this.unitPrice = unitPrice; 
        this.totalPrice = totalPrice; 
    }
}
