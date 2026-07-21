package com.Oreki5.RestaurantManagementSystem.Models;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class Bill {
    private List<BillItem> billItems;
    private int total;

    public Bill() {
        this.billItems = new ArrayList<>();
        this.total = 0;
    }

    public void addBillItem(BillItem billItem) {
        this.billItems.add(billItem);
        this.total += billItem.getTotalPrice();
    }
}
