package com.Oreki5.RestaurantManagementSystem.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Oreki5.RestaurantManagementSystem.Models.Tables;

@Repository
public interface TablesRepo extends JpaRepository<Tables, Long> {

    public List<Tables> findAllByStatus(String free);

    @Query(nativeQuery = true, value = "SELECT t.* FROM tables as t ORDER BY t.status ASC")
    public List<Tables> findAllTablesByStatusAsc();

}
