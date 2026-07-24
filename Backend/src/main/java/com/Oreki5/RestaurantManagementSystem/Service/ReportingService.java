package com.Oreki5.RestaurantManagementSystem.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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

    public List<Inventory> getInventoryWithSorts(String name, String order) {
        boolean flag = false;
        if (!name.isEmpty() && order.equalsIgnoreCase("ASC")) {
            flag = true;
        }
        Sort s = flag ? Sort.by(order.equalsIgnoreCase("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, name) : null;
        if (s != null)
            return inventoryRepo.findAll(s);
        else
            return inventoryRepo.findAll();
    }
}
