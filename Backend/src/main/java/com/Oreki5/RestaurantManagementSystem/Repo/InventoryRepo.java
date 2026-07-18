package com.Oreki5.RestaurantManagementSystem.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Oreki5.RestaurantManagementSystem.Models.Inventory;

public interface InventoryRepo extends JpaRepository<Inventory, Integer> {

}
