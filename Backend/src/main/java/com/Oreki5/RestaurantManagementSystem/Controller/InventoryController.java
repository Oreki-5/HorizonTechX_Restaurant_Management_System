package com.Oreki5.RestaurantManagementSystem.Controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Oreki5.RestaurantManagementSystem.Models.Inventory;
import com.Oreki5.RestaurantManagementSystem.Service.InventoryService;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @PostMapping("/bulk")
    public void addBulkInventory(List<Inventory> itemList){
        inventoryService.bulkInsert(itemList);
    }

    @GetMapping
    public List<Inventory> getAll(){
        return inventoryService.getAll();
    }

    @PutMapping
    public Inventory updateInventory(Inventory inventory){
        return inventoryService.saveInventory(inventory);
    }

    @DeleteMapping("/{id}")
    public void deleteInventory(@PathVariable int id){
        inventoryService.deleteInventory(id);
    }
}
