package br.com.edu.jet.foodfusion.client.request;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import br.com.edu.jet.foodfusion.client.shared.dto.restaurant.RestaurantDTO;

public class CreateRestaurantRequest implements Serializable {

    @SerializedName("newRestaurant")
    private RestaurantDTO newRestaurant;

    public RestaurantDTO getNewRestaurant() {
        return newRestaurant;
    }

    public void setNewRestaurant(RestaurantDTO newRestaurant) {
        this.newRestaurant = newRestaurant;
    }
}
