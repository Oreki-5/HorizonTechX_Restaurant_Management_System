package com.Oreki5.RestaurantManagementSystem.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Oreki5.RestaurantManagementSystem.Models.IngredientItem;
import com.Oreki5.RestaurantManagementSystem.Models.Inventory;
import com.Oreki5.RestaurantManagementSystem.Models.Menus;
import com.Oreki5.RestaurantManagementSystem.Models.Orders;
import com.Oreki5.RestaurantManagementSystem.Repo.InventoryRepo;
import com.Oreki5.RestaurantManagementSystem.Repo.MenusRepo;
import com.Oreki5.RestaurantManagementSystem.Repo.OrdersRepo;

@Service
public class MenuService {
    @Autowired
    private MenusRepo menusRepo;

    @Autowired
    private InventoryRepo inventoryRepo;

    @Autowired
    private OrdersRepo ordersRepo;

    public List<Menus> getAll() {
        return menusRepo.findAll();
    }

    public Menus createMenu(Menus menu) {
        return menusRepo.save(menu);
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

    public List<Inventory> createOrder(Orders order) throws Exception {
        if (consumeMenuItem(menusRepo.findById(order.getMenuItemId()).get(), order.isForceOrder(), order.getQuantity())) {
            ordersRepo.save(order);
            inventoryRepo.findAll();
        }
        return inventoryRepo.findAll();
    }

    public List<Orders> getAllOrders() {
        return ordersRepo.findAll();
    }

}
