package br.com.edu.jet.foodfusion.client.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;
import java.util.stream.Collectors;

import br.com.edu.jet.foodfusion.FoodFusionApplication;
import br.com.edu.jet.foodfusion.client.FoodFusionAPI;
import br.com.edu.jet.foodfusion.client.converter.RestaurantConverter;
import br.com.edu.jet.foodfusion.client.converter.RestaurantConverterHelper;
import br.com.edu.jet.foodfusion.client.request.CreateRestaurantRequest;
import br.com.edu.jet.foodfusion.client.response.create.CreateRestaurantResponse;
import br.com.edu.jet.foodfusion.client.response.delete.DeleteRestaurantResponse;
import br.com.edu.jet.foodfusion.client.response.retrieve.RetrieveRestaurantsResponse;
import br.com.edu.jet.foodfusion.client.response.search.SearchRestaurantResponse;
import br.com.edu.jet.foodfusion.client.shared.dto.restaurant.RestaurantDTO;
import br.com.edu.jet.foodfusion.ui.model.restaurant.Restaurant;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestaurantRepository {

    public static final String TAG = RestaurantRepository.class.getSimpleName();

    private final FoodFusionAPI foodFusionAPI;

    public RestaurantRepository() {
        this.foodFusionAPI = FoodFusionApplication.getRetrofit().create(FoodFusionAPI.class);
    }

    public LiveData<List<Restaurant>> getAll() {
        MutableLiveData<List<Restaurant>> data = new MutableLiveData<>();

        foodFusionAPI.getRestaurants().enqueue(new Callback<RetrieveRestaurantsResponse>() {
            @Override
            public void onResponse(Call<RetrieveRestaurantsResponse> call, Response<RetrieveRestaurantsResponse> response) {
                if (response.isSuccessful()) {
                    RetrieveRestaurantsResponse retrieveResponse = response.body();
                    data.setValue(retrieveResponse.getRestaurants()
                            .stream()
                            .map(RestaurantConverterHelper::toRestaurant)
                            .collect(Collectors.toList()));
                }
            }

            @Override
            public void onFailure(Call<RetrieveRestaurantsResponse> call, Throwable throwable) {
                data.setValue(null);
            }
        });

        return data;
    }

    public LiveData<Restaurant> getById(long restaurantId) {
        MutableLiveData<Restaurant> data = new MutableLiveData<>();
        foodFusionAPI.getRestaurantById(restaurantId).enqueue(new Callback<SearchRestaurantResponse>() {
            @Override
            public void onResponse(Call<SearchRestaurantResponse> call, Response<SearchRestaurantResponse> response) {
                if (response.isSuccessful()) {
                    RestaurantDTO restaurantDTO = response.body().getRestaurant();
                    Restaurant restaurant = RestaurantConverterHelper.toRestaurant(restaurantDTO);
                    data.setValue(restaurant);
                }
            }

            @Override
            public void onFailure(Call<SearchRestaurantResponse> call, Throwable throwable) {
                data.setValue(null);
            }
        });
        return data;
    }

    public LiveData<Restaurant> create(Restaurant restaurant) {
        CreateRestaurantRequest restaurantRequest = createRestaurantRequest(restaurant);
        MutableLiveData<Restaurant> restaurantMutableLiveData = new MutableLiveData<>();
        foodFusionAPI.createRestaurant(restaurantRequest).enqueue(new Callback<CreateRestaurantResponse>() {
            @Override
            public void onResponse(Call<CreateRestaurantResponse> call, Response<CreateRestaurantResponse> response) {
                if (response.isSuccessful()) {
                    RestaurantDTO restaurantDTO = response.body().getRestaurant();
                    Restaurant restaurant = RestaurantConverterHelper.toRestaurant(restaurantDTO);
                    restaurantMutableLiveData.setValue(restaurant);
                }
            }

            @Override
            public void onFailure(Call<CreateRestaurantResponse> call, Throwable throwable) {
                restaurantMutableLiveData.setValue(null);
            }
        });

        return restaurantMutableLiveData;
    }

    private CreateRestaurantRequest createRestaurantRequest(Restaurant restaurant) {
        CreateRestaurantRequest request = new CreateRestaurantRequest();
        request.setNewRestaurant(RestaurantConverterHelper.toRestaurantDTO(restaurant));
        return request;
    }

    public LiveData<Long> deleteById(long restaurantId) {
        MutableLiveData<Long> mutableLiveData = new MutableLiveData<>();
        foodFusionAPI.deleteRestaurantById(restaurantId).enqueue(new Callback<DeleteRestaurantResponse>() {
            @Override
            public void onResponse(Call<DeleteRestaurantResponse> call, Response<DeleteRestaurantResponse> response) {
                if (response.isSuccessful()) {
                    mutableLiveData.setValue(response.body().getRestaurantDeletedId());
                }
            }

            @Override
            public void onFailure(Call<DeleteRestaurantResponse> call, Throwable throwable) {
                mutableLiveData.setValue(null);
            }
        });
        return mutableLiveData;
    }
}
