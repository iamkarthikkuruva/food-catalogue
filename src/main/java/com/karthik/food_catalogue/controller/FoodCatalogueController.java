package com.karthik.food_catalogue.controller;

import com.karthik.food_catalogue.dto.FoodCataloguePage;
import com.karthik.food_catalogue.dto.FoodItemDTO;
import com.karthik.food_catalogue.service.FoodCatalogueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/foodCatalogue")
@CrossOrigin
public class FoodCatalogueController {

    @Autowired
    private FoodCatalogueService foodCatalogueService;

    @PostMapping("/addFoodItem")
    public ResponseEntity<FoodItemDTO> addFoodItem(@RequestBody FoodItemDTO foodItemDTO) {
        FoodItemDTO foodItemSaved = foodCatalogueService.addFoodItem(foodItemDTO);
        return new ResponseEntity<>(foodItemSaved, HttpStatus.CREATED);
    }

    @GetMapping("/fetchRestaurantAndFoodItemsById/{id}")
    public ResponseEntity<FoodCataloguePage> fetchRestaurantDetailsWithFoodMenu(@PathVariable Integer id) {
        FoodCataloguePage foodCataloguePage = foodCatalogueService.fetchFoodCataloguePageDetails(id);
        return new ResponseEntity<>(foodCataloguePage, HttpStatus.OK);
    }

}
