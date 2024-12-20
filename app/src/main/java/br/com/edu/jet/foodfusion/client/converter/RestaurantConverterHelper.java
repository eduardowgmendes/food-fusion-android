package br.com.edu.jet.foodfusion.client.converter;

import br.com.edu.jet.foodfusion.client.shared.dto.restaurant.RestaurantDTO;
import br.com.edu.jet.foodfusion.ui.model.restaurant.Restaurant;

public class RestaurantConverterHelper {

    private static final RestaurantConverter RESTAURANT_CONVERTER = new RestaurantConverter();

    public static Restaurant toRestaurant(RestaurantDTO restaurantDTO) {
        return RESTAURANT_CONVERTER.from(restaurantDTO);
    }

    public static RestaurantDTO toRestaurantDTO(Restaurant restaurant) {
        return RESTAURANT_CONVERTER.to(restaurant);
    }
}
