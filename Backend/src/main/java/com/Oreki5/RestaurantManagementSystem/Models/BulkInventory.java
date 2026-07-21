package com.Oreki5.RestaurantManagementSystem.Models;

import java.util.List;
import lombok.Data;

@Data
public class BulkInventory {
    private List<Inventory> inventoryList;
}
