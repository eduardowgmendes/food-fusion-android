package br.com.edu.jet.foodfusion.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import br.com.edu.jet.foodfusion.client.repository.RestaurantRepository;
import br.com.edu.jet.foodfusion.client.request.CreateRestaurantRequest;
import br.com.edu.jet.foodfusion.client.shared.dto.restaurant.RestaurantDTO;
import br.com.edu.jet.foodfusion.ui.model.restaurant.Restaurant;

public class RestaurantViewModel extends ViewModel {

    private final RestaurantRepository restaurantRepository;

    public RestaurantViewModel() {
        this.restaurantRepository = new RestaurantRepository();
    }

    public LiveData<List<Restaurant>> getAll(boolean includeAll) {
        return restaurantRepository.getAll(includeAll);
    }

    public LiveData<Restaurant> getById(long restaurantId) {
        return restaurantRepository.getById(restaurantId);
    }

    public LiveData<Restaurant> create(Restaurant restaurant) {
        return restaurantRepository.create(restaurant);
    }

    public LiveData<Long> deleteById(long restaurantId) {
        return restaurantRepository.deleteById(restaurantId);
    }

    public LiveData<Restaurant> eraseById(long restaurantId) {
        return restaurantRepository.eraseById(restaurantId);
    }

    public LiveData<Restaurant> recoverById(long restaurantId) {
        return restaurantRepository.recoverById(restaurantId);
    }

}
