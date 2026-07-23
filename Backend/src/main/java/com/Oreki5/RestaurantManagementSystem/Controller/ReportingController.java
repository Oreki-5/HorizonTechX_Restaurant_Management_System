package com.Oreki5.RestaurantManagementSystem.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import com.Oreki5.RestaurantManagementSystem.Models.Inventory;
import com.Oreki5.RestaurantManagementSystem.Models.Orders;
import com.Oreki5.RestaurantManagementSystem.Service.InventoryService;
import com.Oreki5.RestaurantManagementSystem.Service.OrdersService;
import com.Oreki5.RestaurantManagementSystem.Service.ReportingService;
import com.Oreki5.RestaurantManagementSystem.Models.ReportViews.InventoryUsageView;
import com.Oreki5.RestaurantManagementSystem.Models.ReportViews.PopularityView;
import com.Oreki5.RestaurantManagementSystem.Models.ReportViews.TrafficView;


@RestController
@RequestMapping("/admin/report")
public class ReportingController {

    @Autowired
    private ReportingService reportingService;

    @GetMapping("/popular")
    public List<PopularityView> getOrdersByPopularity() {
        return reportingService.getOrdersByPopularity();
    }

    @GetMapping("/traffic")
    public List<TrafficView> getOrdersByTraffic(@RequestParam String date) {
        return reportingService.getByTrafficAtDate(date);
    }

    @GetMapping("/usage")
    public List<InventoryUsageView> getInventoryByUsage() {
        return reportingService.getInventoryByUsage();
    }

    @GetMapping("/filters")
    public List<Inventory> getInventoryWithSorts() {
        return reportingService.getInventoryWithSorts();
    }

}
