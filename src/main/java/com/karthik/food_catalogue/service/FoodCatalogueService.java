package com.karthik.food_catalogue.service;

import com.karthik.food_catalogue.dto.FoodCataloguePage;
import com.karthik.food_catalogue.dto.FoodItemDTO;
import com.karthik.food_catalogue.dto.Restaurant;
import com.karthik.food_catalogue.entity.FoodItem;
import com.karthik.food_catalogue.mapper.FoodItemMapper;
import com.karthik.food_catalogue.repo.FoodItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FoodCatalogueService {
    @Autowired
    private FoodItemRepo foodItemRepo;

    @Autowired
    private RestTemplate restTemplate;

    public FoodItemDTO addFoodItem(FoodItemDTO foodItemDTO) {
        FoodItem foodItemSavedInDB = foodItemRepo.save(FoodItemMapper.INSTANCE.mapFoodItemDtoToFoodItem(foodItemDTO));
        return FoodItemMapper.INSTANCE.mapFoodItemToFoodItemDto(foodItemSavedInDB);
    }

    public FoodCataloguePage fetchFoodCataloguePageDetails(Integer restaurantId) {
        // FoodItem List
        List<FoodItem> foodItemList = fetchFoodItemList(restaurantId);
        //Restaurant Details
        Restaurant restaurant = fetchRestaurantDetailsFromMS(restaurantId);
        return createFoodCataloguePage(foodItemList, restaurant);
    }

    private FoodCataloguePage createFoodCataloguePage(List<FoodItem> foodItemList, Restaurant restaurant) {
        FoodCataloguePage foodCataloguePage = new FoodCataloguePage();
        foodCataloguePage.setFoodItemsList(foodItemList);
        foodCataloguePage.setRestaurant(restaurant);
        return foodCataloguePage;
    }

    private Restaurant fetchRestaurantDetailsFromMS(Integer restaurantId) {
        return restTemplate.getForObject("http://RESTAURANT-SERVICE/restaurant/fetchById/" + restaurantId, Restaurant.class);
    }

    private List<FoodItem> fetchFoodItemList(Integer restaurantId) {
        return foodItemRepo.findByRestaurantId(restaurantId);
    }
}
