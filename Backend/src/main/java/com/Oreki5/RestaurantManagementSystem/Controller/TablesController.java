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

import com.Oreki5.RestaurantManagementSystem.Models.Tables;
import com.Oreki5.RestaurantManagementSystem.Service.TablesService;

@RestController
@RequestMapping("/tables")
public class TablesController {

    @Autowired
    private TablesService tablesService;

    @GetMapping("/free")
    public List<Tables> getFreeTables() {
        return tablesService.getFreeTables();
    }

    @PostMapping
    public Tables createTable(@RequestBody Tables table) {
        return tablesService.saveTable(table);
    }

    @GetMapping
    public List<Tables> getAllTablesSortByStatus() {
        return tablesService.getAllTablesSortByStatus();
    }

    @PutMapping
    public Tables updateTable(@RequestBody Tables table) {
        return tablesService.saveTable(table);
    }
    
    @DeleteMapping("/{id}")
    public void deleteTable(@PathVariable long id){
        tablesService.deleteTable(id);
    }

    

}
