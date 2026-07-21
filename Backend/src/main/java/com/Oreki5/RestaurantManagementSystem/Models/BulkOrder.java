package com.Oreki5.RestaurantManagementSystem.Models;

import java.util.List;
import lombok.Data;


@Data
public class BulkOrder {
    private List<Orders> orderList;
}
