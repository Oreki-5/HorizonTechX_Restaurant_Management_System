package com.Oreki5.RestaurantManagementSystem.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Oreki5.RestaurantManagementSystem.Models.Inventory;
import com.Oreki5.RestaurantManagementSystem.Models.ReportViews.InventoryUsageView;
import com.Oreki5.RestaurantManagementSystem.Models.ReportViews.PopularityView;
import com.Oreki5.RestaurantManagementSystem.Models.ReportViews.TrafficView;
import com.Oreki5.RestaurantManagementSystem.Service.ReportingService;

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
    public List<Inventory> getInventoryWithSorts(@RequestParam(required = false, name = "name") String name,
            @RequestParam(required = false, name = "order") String order) {
        return reportingService.getInventoryWithSorts(name, order);
    }

}
