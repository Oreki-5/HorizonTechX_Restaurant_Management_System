package com.Oreki5.RestaurantManagementSystem.Service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Oreki5.RestaurantManagementSystem.Models.Tables;
import com.Oreki5.RestaurantManagementSystem.Repo.TablesRepo;

@Service
public class TablesService {
    @Autowired
    private TablesRepo tablesRepo;

    public List<Tables> getFreeTables() {
        return tablesRepo.findAllByStatus("free");
    }

    public void deleteTable(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteTable'");
    }

    public Tables saveTable(Tables table) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveTable'");
    }

    public List<Tables> getAllTablesSortByStatus() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllTablesSortByStatus'");
    }

}
