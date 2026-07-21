package com.Oreki5.RestaurantManagementSystem.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import com.Oreki5.RestaurantManagementSystem.Models.Orders;

@Repository
public interface OrdersRepo extends JpaRepository<Orders, Long> {

    @Query(nativeQuery = true, value = "SELECT o.* FROM orders as o WHERE o.table_id= :tableid AND o.status ='active'")
    public List<Orders> findAllByTableId(@Param("tableid") int id);

}
