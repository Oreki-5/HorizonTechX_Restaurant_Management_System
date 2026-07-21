package com.Oreki5.RestaurantManagementSystem.Controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Oreki5.RestaurantManagementSystem.Models.BulkInventory;
import com.Oreki5.RestaurantManagementSystem.Models.Inventory;
import com.Oreki5.RestaurantManagementSystem.Service.InventoryService;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    // Need to change request body of this to a custom BulkInventory object
    @PostMapping("/bulk")
    public void addBulkInventory(@RequestBody BulkInventory bulkInventory){
        inventoryService.bulkInsert(bulkInventory);
    }

    @GetMapping
    public List<Inventory> getAll(){
        return inventoryService.getAll();
    }

    @PostMapping
    public Inventory saveInventory(@RequestBody Inventory inventory){
        return inventoryService.saveInventory(inventory);
    }


    @DeleteMapping("/{id}")
    public void deleteInventory(@PathVariable int id){
        inventoryService.deleteInventory(id);
    }
}
