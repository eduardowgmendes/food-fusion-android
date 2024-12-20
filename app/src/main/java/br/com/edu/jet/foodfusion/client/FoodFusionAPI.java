package br.com.edu.jet.foodfusion.client;

import br.com.edu.jet.foodfusion.client.response.RetrieveRestaurantsResponse;
import br.com.edu.jet.foodfusion.client.response.SearchRestaurantResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface FoodFusionAPI {

    @GET("retrieve/restaurants")
    Call<RetrieveRestaurantsResponse> getRestaurants();

    @GET("search/restaurants/{restaurantId}")
    Call<SearchRestaurantResponse> getRestaurantById(@Path(value = "restaurantId") long restaurantId);
}
