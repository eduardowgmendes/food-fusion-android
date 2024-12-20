package br.com.edu.jet.foodfusion.client.response;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

import br.com.edu.jet.foodfusion.client.shared.dto.restaurant.RestaurantDTO;

public class SearchRestaurantResponse {

    @SerializedName("success")
    private boolean isSuccess;

    @SerializedName("message")
    private String message;

    @SerializedName("body")
    private RestaurantDTO restaurant;

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public RestaurantDTO getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(RestaurantDTO restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SearchRestaurantResponse)) return false;
        SearchRestaurantResponse that = (SearchRestaurantResponse) o;
        return isSuccess == that.isSuccess && Objects.equals(message, that.message) && Objects.equals(restaurant, that.restaurant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isSuccess, message, restaurant);
    }

    @Override
    public String toString() {
        return "SearchRestaurantResponse{" +
                "isSuccess=" + isSuccess +
                ", message='" + message + '\'' +
                ", restaurant=" + restaurant +
                '}';
    }
}
