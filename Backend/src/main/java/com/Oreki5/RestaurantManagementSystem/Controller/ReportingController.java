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
import com.Oreki5.RestaurantManagementSystem.Service.ReportingService;

@RestController
@RequestMapping("/admin/report")
public class ReportingController {

    @Autowired
    private ReportingService reportingService;

    @GetMapping("/popular")
    public List<Orders> getOrdersByPopularity() {
        return reportingService.getOrdersByPopularity();
    }

    @GetMapping("/traffic")
    public List<Orders> getOrdersByTraffic() {
        return reportingService.getOrdersByTraffic();
    }

    @GetMapping("/usage")
    public List<Inventory> getInventoryByUsage() {
        return reportingService.getInventoryByUsage();
    }

    @GetMapping("/filters")
    public List<Inventory> getInventoryWithSorts() {
        return reportingService.getInventoryWithSorts();
    }

}
