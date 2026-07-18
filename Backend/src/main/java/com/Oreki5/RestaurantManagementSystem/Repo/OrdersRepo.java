package com.Oreki5.RestaurantManagementSystem.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Oreki5.RestaurantManagementSystem.Models.Orders;

public interface OrdersRepo extends JpaRepository<Orders, Long>{

    public List<Orders> findAllByTableId(int id);

}
