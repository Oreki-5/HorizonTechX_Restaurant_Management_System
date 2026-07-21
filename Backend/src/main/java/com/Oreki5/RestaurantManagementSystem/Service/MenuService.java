package com.Oreki5.RestaurantManagementSystem.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Oreki5.RestaurantManagementSystem.Models.Menus;
import com.Oreki5.RestaurantManagementSystem.Repo.MenusRepo;

@Service
public class MenuService {

    @Autowired
    private MenusRepo menusRepo;

    public List<Menus> getAll(String filter) {
        if(filter ==null) filter = "active";
        return menusRepo.findAll(filter);
    }

    public Menus saveMenu(Menus menu) {
        return menusRepo.save(menu);
    }

    public void deleteMenu(long id) {
        Optional<Menus> m = menusRepo.findById(id);
        if (m.isPresent()) {
            Menus menu = m.get();
            menu.setStatus("inactive");
            menusRepo.save(menu);
        } else {
            throw new NullPointerException("No such menu exists");
        }
    }

    public Menus restoreMenu(long id) throws NullPointerException {

        Optional<Menus> m = menusRepo.findById(id);
        if (m.isPresent()) {
            Menus menu = m.get();
            menu.setStatus("active");
            return menusRepo.save(menu);
        } else {
            throw new NullPointerException("No such menu exists");
        }

    }
}
