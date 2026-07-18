package com.Oreki5.RestaurantManagementSystem.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Oreki5.RestaurantManagementSystem.Models.Inventory;
import com.Oreki5.RestaurantManagementSystem.Models.Menus;
import com.Oreki5.RestaurantManagementSystem.Models.Orders;
import com.Oreki5.RestaurantManagementSystem.Service.MenuService;

@RestController
@RequestMapping("")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @GetMapping("/menu")
    public List<Menus> getAll(){
        return menuService.getAll();
    }

    @PostMapping("/menu")
    public Menus creatMenu(@RequestBody Menus menu){
        return menuService.createMenu(menu);
    }
    @GetMapping("/orders")
    public List<Orders> getAllOrders(){
        return menuService.getAllOrders();
    }

    @PostMapping("/orders")
    public List<Inventory> createOrder(@RequestBody Orders order) throws Exception{
        return menuService.createOrder(order);
    }
}
