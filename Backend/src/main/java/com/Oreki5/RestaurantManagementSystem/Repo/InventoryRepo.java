package com.Oreki5.RestaurantManagementSystem.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import com.Oreki5.RestaurantManagementSystem.Models.Inventory;
import com.Oreki5.RestaurantManagementSystem.Models.ReportViews.InventoryUsageView;
import java.util.List;

@Repository
public interface InventoryRepo extends JpaRepository<Inventory, Integer> {

    @Query(nativeQuery = true, value = "SELECT DISTINCT i.item_name , (i.last_updated_stock - i.current_stock) as usage from inventory as i Order by usage DESC;")
    List<InventoryUsageView> getByUsage();

}
