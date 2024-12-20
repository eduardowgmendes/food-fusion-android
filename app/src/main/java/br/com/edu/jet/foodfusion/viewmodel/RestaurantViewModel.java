package br.com.edu.jet.foodfusion.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import br.com.edu.jet.foodfusion.client.repository.RestaurantRepository;
import br.com.edu.jet.foodfusion.ui.model.restaurant.Restaurant;

public class RestaurantViewModel extends ViewModel {

    private final RestaurantRepository restaurantRepository;

    public RestaurantViewModel() {
        this.restaurantRepository = new RestaurantRepository();
    }

    public LiveData<List<Restaurant>> getAll() {
        return restaurantRepository.getAll();
    }

    public LiveData<Restaurant> getById(long restaurantId) {
        return restaurantRepository.getById(restaurantId);
    }

}
