package com.Oreki5.RestaurantManagementSystem.Models.ReportViews;
import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class InventoryUsageView {
    private String itemName;
    private int usage;
}
