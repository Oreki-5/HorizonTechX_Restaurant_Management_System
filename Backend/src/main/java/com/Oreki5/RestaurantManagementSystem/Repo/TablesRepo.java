package com.Oreki5.RestaurantManagementSystem.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Oreki5.RestaurantManagementSystem.Models.Tables;

public interface TablesRepo extends JpaRepository<Tables, Long> {

    public List<Tables> findAllByStatus(String free);

}
