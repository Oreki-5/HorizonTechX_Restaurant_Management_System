package com.Oreki5.RestaurantManagementSystem.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public List<Boolean> consumeMenuItem(Menus menuItem, boolean force, int quantity) {
        List<IngredientItem> ingredientsItems = menuItem.getIngredients();
        List<Boolean> flags = new ArrayList<>();
        flags.add(false);
        flags.add(true);
        for (IngredientItem i : ingredientsItems) {

            Inventory item = inventoryRepo.findById(i.getItemId()).get();
            if ((item.getCurrentStock() - (i.getQuantity() * quantity)) < 0) {
                flags.set(1, false);
                if (!force) {
                    flags.set(0, false);
                } else {
                    item.setCurrentStock(item.getCurrentStock() - (i.getQuantity() * quantity));
                    flags.set(0, true);
                    inventoryRepo.save(item);
                }

            } else {
                item.setCurrentStock(item.getCurrentStock() - (i.getQuantity() * quantity));
                flags.set(0, true);
                inventoryRepo.save(item);
            }
        }
        return flags;
    }

    public ResponseObj saveOrder(Orders order) throws NullPointerException {

        List<Boolean> consumedOrder = consumeMenuItem(menusRepo.findById(order.getMenuItemId()).get(),
                order.isForceOrder(), order.getQuantity());

        if (consumedOrder.get(0)) {

            order.setOrderPrice(menusRepo.findById(order.getMenuItemId()).get().getPrice() * order.getQuantity());

            Tables t = tablesRepo.findById(order.getTableId()).get();
            t.setStatus("occupied");
            tablesRepo.save(t);
            if (!consumedOrder.get(1)) {
                return new ResponseObj(ordersRepo.save(order),
                        menusRepo.findById(order.getMenuItemId()).get().getName());
            }
            return new ResponseObj(ordersRepo.save(order),null);
        } else {
            return new ResponseObj(null, menusRepo.findById(order.getMenuItemId()).get().getName());
        }

    }

    public ResponseObj saveBulkOrders(BulkOrder bulkOrders) {
        List<Orders> orders = bulkOrders.getOrderList();
        List<Orders> finishedOrders = new ArrayList<>();
        String warningStr = "Some orders have insuffiecient ingredients : ";
        String skippedItemList = null;

        for (Orders order : orders) {
            ResponseObj currentOrder = saveOrder(order);
            if (currentOrder.getData() != null) {
                finishedOrders.add((Orders) currentOrder.getData());
            }
            if (currentOrder.getWarningMsg() != null) {
                if (skippedItemList == null) {
                    skippedItemList = currentOrder.getWarningMsg()
                            + ((order.isForceOrder()) ? " (Force Ordered)" : " (Skipped)") + " | ";
                } else {
                    skippedItemList += currentOrder.getWarningMsg() +  ((order.isForceOrder()) ? " (Force Ordered)" : " (Skipped)") + " | ";
                }

            }

        }
        return new ResponseObj(finishedOrders, (skippedItemList != null) ? warningStr + skippedItemList : null);
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
        Tables t = tablesRepo.findById(id);
        t.setStatus("free");
        tablesRepo.save(t);

        // After successfully creatinf Bill Object, we set all the current orders to
        // finished
        ordersRepo.saveAll(allOrders);

        return finalBill;

    }

}
