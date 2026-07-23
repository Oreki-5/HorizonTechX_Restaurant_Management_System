package com.Oreki5.RestaurantManagementSystem.Models;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class IngredientItem {
    private int itemId;
    private int quantity;
}
