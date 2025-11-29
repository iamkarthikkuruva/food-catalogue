package com.karthik.food_catalogue.dto;

import com.karthik.food_catalogue.entity.FoodItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodCataloguePage {

    private List<FoodItem> foodItemsList;
    private Restaurant restaurant;
}
