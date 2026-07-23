package com.Oreki5.RestaurantManagementSystem.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Oreki5.RestaurantManagementSystem.Models.BulkInventory;
import com.Oreki5.RestaurantManagementSystem.Models.Inventory;
import com.Oreki5.RestaurantManagementSystem.Repo.InventoryRepo;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepo inventoryRepo;

    public void bulkInsert(BulkInventory bulkInventory) {

        // extracting List<Inventory> form BulkInventory Object
        List<Inventory> inventoryList = bulkInventory.getInventoryList();

        inventoryList.forEach(item->{
            item.setCurrentStock(item.getLastUpdatedStock());
        });

        inventoryRepo.saveAll(inventoryList);
    }

    public List<Inventory> getAll() {
        return inventoryRepo.findAll();
    }

    public Inventory saveInventory(Inventory inventory) {
        inventory.setCurrentStock(inventory.getLastUpdatedStock());
        return inventoryRepo.save(inventory);
    }

    public void deleteInventory(int id) {
        inventoryRepo.deleteById(id);
    }

}
