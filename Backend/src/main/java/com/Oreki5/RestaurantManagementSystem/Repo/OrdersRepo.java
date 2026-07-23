package com.Oreki5.RestaurantManagementSystem.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Oreki5.RestaurantManagementSystem.Models.Orders;
import com.Oreki5.RestaurantManagementSystem.Models.ReportViews.PopularityView;
import com.Oreki5.RestaurantManagementSystem.Models.ReportViews.TrafficView;

@Repository
public interface OrdersRepo extends JpaRepository<Orders, Long> {

    @Query(nativeQuery = true, value = "SELECT o.* FROM orders as o WHERE o.table_id= :tableid AND o.status ='active'")
    public List<Orders> findAllByTableId(@Param("tableid") int id);

    @Query(nativeQuery=true, value="SELECT  DISTINCT m.name as menuItem, SUM(o.quantity) as orderedAmount FROM orders as o INNER JOIN menus as m on m.id = o.menu_item_id GROUP by m.name order by COUNT(*) DESC")
    public List<PopularityView> getByPoularity();

    @Query(nativeQuery = true, value = "select CASE WHEN strftime('%H:%M',o.created_date) BETWEEN  strftime('%H:%M','03:30') and strftime('%H:%M','06:30') then 'Morning' when strftime('%H:%M',o.created_date) BETWEEN  strftime('%H:%M','06:31') and strftime('%H:%M','09:30') then 'Afternoon' when strftime('%H:%M',o.created_date) BETWEEN  strftime('%H:%M','09:31') and strftime('%H:%M','12:30') then 'Evening' when strftime('%H:%M',o.created_date) BETWEEN  strftime('%H:%M','12:31') and strftime('%H:%M','17:30') then 'Night' END as quarters , COUNT(*) as orderedAmount from orders as o WHERE strftime('%Y-%m-%d',o.created_date) = strftime('%Y-%m-%d',:dat) GROUP BY quarters order by o.created_date ASC")
    public List<TrafficView> getByTrafficAtDate(@Param("dat")String date);

}
