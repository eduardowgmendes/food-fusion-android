package br.com.edu.jet.foodfusion.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import br.com.edu.jet.foodfusion.R;
import br.com.edu.jet.foodfusion.ui.fragment.EmptyStateFragment;
import br.com.edu.jet.foodfusion.ui.fragment.LoaderFragment;
import br.com.edu.jet.foodfusion.ui.fragment.RestaurantListFragment;
import br.com.edu.jet.foodfusion.ui.model.restaurant.Restaurant;
import br.com.edu.jet.foodfusion.ui.utils.EmptyStateRepository;
import br.com.edu.jet.foodfusion.viewmodel.RestaurantViewModel;


public class MainActivity extends BaseActivity implements View.OnClickListener {

    private ActivityResultLauncher<Intent> createRestaurantLauncher;

    private static final String TAG = MainActivity.class.getSimpleName();

    private RestaurantViewModel restaurantViewModel;
    private EmptyStateRepository emptyStateRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        emptyStateRepository = new EmptyStateRepository(getResources());
        restaurantViewModel = new ViewModelProvider(this).get(RestaurantViewModel.class);

        setSupportActionBar(findViewById(R.id.main_toolbar));
        configureRestaurantsList(restaurantViewModel);

        createRestaurantLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == RESULT_OK) {
                configureRestaurantsList(restaurantViewModel);
            }
        });

        FloatingActionButton createNewButton = findViewById(R.id.create_new_restaurant_button);
        createNewButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CreateRestaurantActivity.class);
            createRestaurantLauncher.launch(intent);
        });
    }

    public void configureRestaurantsList(RestaurantViewModel restaurantViewModel) {
        replace(R.id.main_content, LoaderFragment.newInstance());
        restaurantViewModel.getAll().observe(this, restaurants -> {
            if (restaurants != null) {
                if (restaurants.isEmpty()) {
                    replace(R.id.main_content, EmptyStateFragment.newInstance(emptyStateRepository.emptyListIssue()));
                } else {
                    RestaurantListFragment restaurantListFragment = RestaurantListFragment.newInstance(restaurants);
                    restaurantListFragment.setRestaurantViewModel(restaurantViewModel);
                    replace(R.id.main_content, restaurantListFragment);
                }
            } else {
                replace(R.id.main_content, EmptyStateFragment.newInstance(emptyStateRepository.serverIssue(R.drawable.baseline_downloading_24, getString(R.string.reload_something_button_hint), this)));
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.empty_state_action_button) {
            replace(R.id.main_content, LoaderFragment.newInstance());
            configureRestaurantsList(restaurantViewModel);
        }
    }

}