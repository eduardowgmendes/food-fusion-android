package br.com.edu.jet.foodfusion.ui.activity;

import android.os.Bundle;

import androidx.lifecycle.ViewModelProvider;

import br.com.edu.jet.foodfusion.R;
import br.com.edu.jet.foodfusion.ui.component.content.emptystate.EmptyState;
import br.com.edu.jet.foodfusion.ui.fragment.EmptyStateFragment;
import br.com.edu.jet.foodfusion.ui.fragment.LoaderFragment;
import br.com.edu.jet.foodfusion.ui.fragment.RestaurantListFragment;
import br.com.edu.jet.foodfusion.viewmodel.RestaurantViewModel;


public class MainActivity extends BaseActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private RestaurantViewModel restaurantViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        restaurantViewModel = new ViewModelProvider(this).get(RestaurantViewModel.class);

        configureRestaurantsList();
    }

    private void configureRestaurantsList() {
        replace(R.id.main_content, LoaderFragment.newInstance());
        restaurantViewModel.getAll().observe(this, restaurants -> {
            if (restaurants != null) {
                if (restaurants.isEmpty()) {
                    replace(R.id.main_content, EmptyStateFragment.newInstance(new EmptyState(R.drawable.baseline_add_circle_24, "Oww, such empty!", "Add some restaurant by pressing Add +")));
                } else {
                    replace(R.id.main_content, RestaurantListFragment.newInstance(restaurants));
                }
            } else {
                replace(R.id.main_content, EmptyStateFragment.newInstance(new EmptyState(R.drawable.baseline_error_24, "Oops! Something went wrong!", "Failed to request data to server.")));
            }
        });
    }

}