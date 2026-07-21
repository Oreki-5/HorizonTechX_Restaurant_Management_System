package com.Oreki5.RestaurantManagementSystem.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Oreki5.RestaurantManagementSystem.Models.Menus;

@Repository
public interface MenusRepo extends JpaRepository<Menus, Long> {

    @Query(nativeQuery=true, value="SELECT m.* from Menus as m WHERE m.status = :stat")
    List<Menus> findAll(@Param("stat")String filter);

}
