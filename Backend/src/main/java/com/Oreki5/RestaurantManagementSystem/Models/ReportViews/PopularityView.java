package com.Oreki5.RestaurantManagementSystem.Models.ReportViews;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class PopularityView {

    private String menuItem;
    private int orderedAmount;
}
