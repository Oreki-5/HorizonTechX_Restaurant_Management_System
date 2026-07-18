package com.Oreki5.RestaurantManagementSystem.Models;

import java.util.List;
import lombok.Data;

@Data
public class Bill {
    private List<BillItem> billItems;
    private int total;

}
