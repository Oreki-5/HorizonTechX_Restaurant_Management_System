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

import com.Oreki5.RestaurantManagementSystem.Models.Menus;
import com.Oreki5.RestaurantManagementSystem.Service.MenuService;

@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @GetMapping
    public List<Menus> getAll(){
        return menuService.getAll();
    }

    @PostMapping("/menu")
    public Menus createMenu(@RequestBody Menus menu){
        return menuService.createMenu(menu);
    }

    @PutMapping
    public Menus updatMenu(@RequestBody Menus menu){
        return menuService.updateMenu(menu);
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
