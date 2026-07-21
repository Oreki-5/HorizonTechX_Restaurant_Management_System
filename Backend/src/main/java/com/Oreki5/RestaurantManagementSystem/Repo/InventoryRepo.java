package com.Oreki5.RestaurantManagementSystem.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Oreki5.RestaurantManagementSystem.Models.Inventory;

@Repository
public interface InventoryRepo extends JpaRepository<Inventory, Integer> {

}
