package com.Oreki5.RestaurantManagementSystem.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Oreki5.RestaurantManagementSystem.Models.Inventory;
import com.Oreki5.RestaurantManagementSystem.Models.ReportViews.InventoryUsageView;
import com.Oreki5.RestaurantManagementSystem.Models.ReportViews.PopularityView;
import com.Oreki5.RestaurantManagementSystem.Models.ReportViews.TrafficView;
import com.Oreki5.RestaurantManagementSystem.Repo.InventoryRepo;
import com.Oreki5.RestaurantManagementSystem.Repo.OrdersRepo;

@Service
public class ReportingService {

    @Autowired
    private OrdersRepo ordersRepo;

    @Autowired
    private InventoryRepo inventoryRepo;

    public List<PopularityView> getOrdersByPopularity() {

        return ordersRepo.getByPoularity();

    }

    public List<TrafficView> getByTrafficAtDate(String date) {

        return ordersRepo.getByTrafficAtDate(date);

    }

    public List<InventoryUsageView> getInventoryByUsage() {
        return inventoryRepo.getByUsage();
    }

    public List<Inventory> getInventoryWithSorts() {

        throw new UnsupportedOperationException("Unimplemented method 'getInventoryWithSorts'");
    }
}
