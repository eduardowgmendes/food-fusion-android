package br.com.edu.jet.foodfusion.client;

import br.com.edu.jet.foodfusion.client.request.CreateRestaurantRequest;
import br.com.edu.jet.foodfusion.client.response.delete.DeleteRestaurantResponse;
import br.com.edu.jet.foodfusion.client.response.create.CreateRestaurantResponse;
import br.com.edu.jet.foodfusion.client.response.retrieve.RetrieveRestaurantsResponse;
import br.com.edu.jet.foodfusion.client.response.search.SearchRestaurantResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface FoodFusionAPI {

    @GET("retrieve/restaurants")
    Call<RetrieveRestaurantsResponse> getRestaurants();

    @GET("search/restaurants/{restaurantId}")
    Call<SearchRestaurantResponse> getRestaurantById(@Path(value = "restaurantId") long restaurantId);

    @POST("create/restaurant")
    Call<CreateRestaurantResponse> createRestaurant(@Body CreateRestaurantRequest createRestaurantRequest);

    @DELETE("delete/restaurant/by_id/{restaurantId}")
    Call<DeleteRestaurantResponse> deleteRestaurantById(@Path(value = "restaurantId") long restaurantId);
}
