package br.com.edu.jet.foodfusion.client.response.create;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import br.com.edu.jet.foodfusion.client.shared.dto.restaurant.RestaurantDTO;

public class CreateRestaurantResponse implements Serializable {

    @SerializedName("success")
    private boolean isSuccess;

    @SerializedName("message")
    private String message;

    @SerializedName("body")
    private RestaurantDTO restaurant;

    public boolean isSuccess() {
        return isSuccess;
    }

    public String getMessage() {
        return message;
    }

    public RestaurantDTO getRestaurant() {
        return restaurant;
    }
}
