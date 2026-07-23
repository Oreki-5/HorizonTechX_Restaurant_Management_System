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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Oreki5.RestaurantManagementSystem.Models.Menus;
import com.Oreki5.RestaurantManagementSystem.Service.MenuService;

@RestController
@RequestMapping("/menus")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @GetMapping
    public List<Menus> getAll(@RequestParam(required=false) String filter){
        return menuService.getAll(filter);
    }

    @PostMapping
    public Menus createMenu(@RequestBody Menus menu){
        return menuService.saveMenu(menu);
    }

    @PutMapping
    public Menus updateMenu(@RequestBody Menus menu){
        return menuService.saveMenu(menu);
    }

    @DeleteMapping("/{id}")
    public void deleteMenu(@PathVariable int id){
        menuService.deleteMenu(id);
    }

    @PostMapping("/restore/{id}")
    public void restoreMenu(@PathVariable int id){
        menuService.restoreMenu(id);
    }
}
