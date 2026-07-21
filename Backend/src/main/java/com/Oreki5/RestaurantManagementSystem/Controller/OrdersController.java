package com.Oreki5.RestaurantManagementSystem.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Oreki5.RestaurantManagementSystem.Models.Bill;
import com.Oreki5.RestaurantManagementSystem.Models.BulkOrder;
import com.Oreki5.RestaurantManagementSystem.Models.Orders;
import com.Oreki5.RestaurantManagementSystem.Models.ResponseObj;
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
    public ResponseEntity<Object> createOrder(@RequestBody Orders order) {
        try {
            ResponseObj res = new ResponseObj(ordersService.saveOrder(order));
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (NullPointerException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }

    }

    @PostMapping("/bulk")
    public ResponseEntity<Object> createBulkOrder(@RequestBody BulkOrder orders) {
        try {
            ResponseObj res = new ResponseObj(ordersService.saveBulkOrders(orders));
            return new ResponseEntity<>(res, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }

    }

    @PutMapping
    public Orders updateOrder(@RequestBody Orders order) throws Exception {
        return ordersService.saveOrder(order);
    }

    @GetMapping("/bill/{tableId}")
    public Bill generateBills(@PathVariable int tableId) {
        return ordersService.generateBills(tableId);
    }
}
