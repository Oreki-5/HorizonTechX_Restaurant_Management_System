package com.Oreki5.RestaurantManagementSystem.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Oreki5.RestaurantManagementSystem.Models.Bill;
import com.Oreki5.RestaurantManagementSystem.Models.BillItem;
import com.Oreki5.RestaurantManagementSystem.Models.BulkOrder;
import com.Oreki5.RestaurantManagementSystem.Models.IngredientItem;
import com.Oreki5.RestaurantManagementSystem.Models.Inventory;
import com.Oreki5.RestaurantManagementSystem.Models.Menus;
import com.Oreki5.RestaurantManagementSystem.Models.Orders;
import com.Oreki5.RestaurantManagementSystem.Models.ResponseObj;
import com.Oreki5.RestaurantManagementSystem.Models.Tables;
import com.Oreki5.RestaurantManagementSystem.Repo.InventoryRepo;
import com.Oreki5.RestaurantManagementSystem.Repo.MenusRepo;
import com.Oreki5.RestaurantManagementSystem.Repo.OrdersRepo;
import com.Oreki5.RestaurantManagementSystem.Repo.TablesRepo;

@Service
public class OrdersService {
    @Autowired
    private MenusRepo menusRepo;

    @Autowired
    private InventoryRepo inventoryRepo;

    @Autowired
    private OrdersRepo ordersRepo;

    @Autowired
    private TablesRepo tablesRepo;

    public boolean consumeMenuItem(Menus menuItem, boolean force, int quantity) {
        List<IngredientItem> ingredientsItems = menuItem.getIngredients();

        for (IngredientItem i : ingredientsItems) {

            Inventory item = inventoryRepo.findById(i.getItemId()).get();
            if ((item.getStock() - (i.getQuantity() * quantity)) < 0 && !force) {
                return false;

            } else {
                item.setStock(item.getStock() - i.getQuantity());
                inventoryRepo.save(item);
            }
        }
        return true;
    }

    public ResponseObj saveOrder(Orders order) throws NullPointerException {

        if (consumeMenuItem(menusRepo.findById(order.getMenuItemId()).get(), order.isForceOrder(),
                order.getQuantity())) {
            Tables t = tablesRepo.findById(order.getTableId()).get();
            t.setStatus("occupied");
            tablesRepo.save(t);
            return new ResponseObj(ordersRepo.save(order));
        } else {
            return new ResponseObj(null,menusRepo.findById(order.getMenuItemId()).get().getName());
        }
        // throw new NullPointerException("Bulk Order cancelled mid-way. Not enough
        // ingredients for "
        // + menusRepo.findById(order.getMenuItemId()).get().getName()
        // + ". All Orders after this items were not ordered due to this error."
        // + " If you want to override this restriction, put forceOrder flag to true");
    }

    public ResponseObj saveBulkOrders(BulkOrder bulkOrders) {
        List<Orders> orders = bulkOrders.getOrderList();
        List<Orders> finishedOrders = new ArrayList<>();
        String warningStr = "";

        for (Orders order : orders) {
            ResponseObj currentOrder = saveOrder(order);
            if (currentOrder.getData() != null) {
                finishedOrders.add((Orders) currentOrder.getData());
            }
            warningStr += currentOrder.getWarningMsg()+" ";

        }
        return new ResponseObj(finishedOrders,warningStr);
    }

    public List<Orders> getAllOrdersOfTable(int id) {
        return ordersRepo.findAllByTableId(id);
    }

    public Bill generateBills(int id) {
        /*
         * get all orders of given table id
         * get all menuItem record {id , name, price} repo.getOnlyIdAndName()
         * convert list to map using
         * .stream().collect(Collecttors.toMap(Menus::getID,Menus::getName))
         * convert list to map using
         * .stream().collect(Collecttors.toMap(Menus::getID,Menus::getPrice))
         * create map for orders and their quantity
         * for each order udpate the count of orders and total
         * for each item in hashMap:
         * create BillItem object
         * insert it in a List of BillItems
         * then create Bill Object contructor and pass list<BillItems>.
         * constuctor will handle calculation of total
         */

        List<Orders> allOrders = ordersRepo.findAllByTableId(id);
        List<Menus> allMenuItems = menusRepo.findAll();

        Map<Long, String> nameMap = allMenuItems.stream().collect(Collectors.toMap(Menus::getId, Menus::getName));
        Map<Long, Integer> priceMap = allMenuItems.stream().collect(Collectors.toMap(Menus::getId, Menus::getPrice));

        Map<Long, Integer> billMap = new HashMap<>();
        allOrders.forEach((order) -> {
            billMap.put(order.getMenuItemId(),
                    billMap.getOrDefault(order.getMenuItemId(), 0) + order.getQuantity());
            order.setStatus("finished");

        });

        Bill finalBill = new Bill();
        billMap.forEach((menuId, quantity) -> {
            BillItem billItem = new BillItem(nameMap.get(menuId), quantity, priceMap.get(menuId),
                    (priceMap.get(menuId) * quantity));
            finalBill.addBillItem(billItem);
        });

        // After successfully creatinf Bill Object, we set all the current orders to
        // finished
        ordersRepo.saveAll(allOrders);

        return finalBill;

    }

}
