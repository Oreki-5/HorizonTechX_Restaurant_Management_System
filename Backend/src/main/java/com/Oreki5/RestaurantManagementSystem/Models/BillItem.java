package com.Oreki5.RestaurantManagementSystem.Models;

import lombok.Data;

@Data
public class BillItem {
    private String itemName;
    private int unitPrice;
    private int quantity;
    private int totalPrice;
}
