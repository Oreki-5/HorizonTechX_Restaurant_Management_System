package com.Oreki5.RestaurantManagementSystem.Models;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class IngredientItem {
    private int itemId;
    private int quantity;
}
