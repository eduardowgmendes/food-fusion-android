package br.com.edu.jet.foodfusion.client.response.retrieve;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import br.com.edu.jet.foodfusion.client.shared.dto.restaurant.RestaurantDTO;

public class RetrieveRestaurantsResponse implements Serializable {

    @SerializedName("success")
    private boolean isSuccess;

    @SerializedName("message")
    private String message;

    @SerializedName("body")
    private List<RestaurantDTO> restaurants;

    public boolean isSuccess() {
        return isSuccess;
    }

    public String getMessage() {
        return message;
    }

    public List<RestaurantDTO> getRestaurants() {
        return restaurants;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RetrieveRestaurantsResponse that = (RetrieveRestaurantsResponse) o;
        return isSuccess == that.isSuccess && Objects.equals(message, that.message) && Objects.equals(restaurants, that.restaurants);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isSuccess, message, restaurants);
    }

    @Override
    public String toString() {
        return "RetrieveRestaurantsResponse{" +
                "isSuccess=" + isSuccess +
                ", message='" + message + '\'' +
                ", restaurants=" + restaurants +
                '}';
    }
}
