package com.Oreki5.RestaurantManagementSystem.Models.ReportViews;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class TrafficView {
    private String quarter;
    private int orderedAmount;
}
