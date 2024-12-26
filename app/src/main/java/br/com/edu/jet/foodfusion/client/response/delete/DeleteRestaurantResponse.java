package br.com.edu.jet.foodfusion.client.response.delete;

import com.google.gson.annotations.SerializedName;

public class DeleteRestaurantResponse {

    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    @SerializedName("body")
    private long restaurantDeletedId;

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public long getRestaurantDeletedId() {
        return restaurantDeletedId;
    }
}
