package com.Oreki5.RestaurantManagementSystem.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Oreki5.RestaurantManagementSystem.Models.Orders;
import com.Oreki5.RestaurantManagementSystem.Service.OrdersService;

@RestController
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    @GetMapping("/{tableID}")
    public List<Orders> getAllOrdersOfTable(@PathVariable int tableID) {
        return ordersService.getAllOrdersOfTable(tableID);
    }

    @PostMapping
    public Orders createOrder(@RequestBody Orders order) throws Exception {
        return ordersService.saveOrder(order);
    }

    @PutMapping
    public Orders updateOrder(@RequestBody Orders order) throws Exception {
        return ordersService.saveOrder(order);
    }

    @GetMapping("/bill/{tableId}")
    public void generateBills(@PathVariable int id){
        ordersService.generateBills(id);
    }
}
