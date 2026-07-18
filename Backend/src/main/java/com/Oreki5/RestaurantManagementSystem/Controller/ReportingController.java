package com.Oreki5.RestaurantManagementSystem.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Oreki5.RestaurantManagementSystem.Models.Inventory;
import com.Oreki5.RestaurantManagementSystem.Models.Orders;
import com.Oreki5.RestaurantManagementSystem.Service.InventoryService;
import com.Oreki5.RestaurantManagementSystem.Service.OrdersService;

@RestController
@RequestMapping("/admin/report")
public class ReportingController {
    @Autowired
    private OrdersService ordersService;
    @Autowired
    private InventoryService inventoryService;

    @GetMapping
    public List<Orders> getOrdersByPopularity(){
        return ordersService.getOrdersByPopularity();
    }
    @GetMapping
    public List<Orders> getOrdersByTraffic(){
        return ordersService.getOrdersByTraffic();
    }
    @GetMapping
    public List<Inventory> getInventoryByUsage(){
        return inventoryService.getInventoryByUsage();
    }
    @GetMapping
    public List<Inventory> getInventoryWithSorts(){
        return inventoryService.getInventoryWithSorts();
    }

}
