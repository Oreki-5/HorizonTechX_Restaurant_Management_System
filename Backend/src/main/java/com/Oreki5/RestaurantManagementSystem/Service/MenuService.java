package com.Oreki5.RestaurantManagementSystem.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Oreki5.RestaurantManagementSystem.Models.Menus;
import com.Oreki5.RestaurantManagementSystem.Repo.MenusRepo;

@Service
public class MenuService {
    @Autowired
    private MenusRepo menusRepo;

     public List<Menus> getAll() {
        return menusRepo.findAll();
    }


    public Menus createMenu(Menus menu) {
        return menusRepo.save(menu);
    }


    public Menus updateMenu(Menus menu) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateMenu'");
    }


    public void deleteMenu(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteMenu'");
    }


    public void restoreMenu(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'restoreMenu'");
    }
}
