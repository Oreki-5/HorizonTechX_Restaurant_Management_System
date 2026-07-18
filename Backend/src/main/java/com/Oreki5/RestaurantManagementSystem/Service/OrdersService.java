package com.Oreki5.RestaurantManagementSystem.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Oreki5.RestaurantManagementSystem.Models.IngredientItem;
import com.Oreki5.RestaurantManagementSystem.Models.Menus;
import com.Oreki5.RestaurantManagementSystem.Models.Orders;
import com.Oreki5.RestaurantManagementSystem.Repo.InventoryRepo;
import com.Oreki5.RestaurantManagementSystem.Repo.MenusRepo;
import com.Oreki5.RestaurantManagementSystem.Repo.OrdersRepo;

@Service
public class OrdersService {
    @Autowired
    private MenusRepo menusRepo;

    @Autowired
    private InventoryRepo inventoryRepo;

    @Autowired
    private OrdersRepo ordersRepo;

    public List<Menus> getAll() {
        return menusRepo.findAll();
    }

    public boolean consumeMenuItem(Menus menuItem, boolean force, int quantity) throws Exception {
        List<IngredientItem> ingredientsItems = menuItem.getIngredients();

        for (IngredientItem i : ingredientsItems) {
            inventoryRepo.findById(i.getItemId()).ifPresent((item) -> {
                if ((item.getStock() - (i.getQuantity() * quantity)) < 0 && !force) {
                    throw new NullPointerException("Item cannot be ordered because there are not enough ingredients");

                } else {
                    item.setStock(item.getStock() - i.getQuantity());
                    inventoryRepo.save(item);
                }

            });

        }
        return true;
    }

    public Orders saveOrder(Orders order) throws Exception {
        if (consumeMenuItem(menusRepo.findById(order.getMenuItemId()).get(), order.isForceOrder(),
                order.getQuantity())) {
            return ordersRepo.save(order);
        }
        throw new Exception("Something bad happened");

    }

    public List<Orders> getAllOrdersOfTable(int id) {
        return ordersRepo.findAllByTableId(id);
    }

    public void generateBills(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<Orders> getOrdersByPopularity() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getOrdersByPopularity'");
    }

    public List<Orders> getOrdersByTraffic() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getOrdersByTraffic'");
    }

}
