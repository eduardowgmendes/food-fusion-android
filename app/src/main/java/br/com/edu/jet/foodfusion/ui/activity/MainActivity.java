package br.com.edu.jet.foodfusion.ui.activity;

import android.os.Bundle;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import br.com.edu.jet.foodfusion.R;
import br.com.edu.jet.foodfusion.ui.component.emptystate.EmptyState;
import br.com.edu.jet.foodfusion.ui.fragment.EmptyStateFragment;
import br.com.edu.jet.foodfusion.ui.fragment.LoaderFragment;
import br.com.edu.jet.foodfusion.ui.fragment.RestaurantListFragment;
import br.com.edu.jet.foodfusion.ui.utils.EmptyStateRepository;
import br.com.edu.jet.foodfusion.viewmodel.RestaurantViewModel;


public class MainActivity extends BaseActivity {

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
        configureRestaurantsList();
    }

    private void configureRestaurantsList() {
        replace(R.id.main_content, LoaderFragment.newInstance());
        restaurantViewModel.getAll().observe(this, restaurants -> {
            if (restaurants != null) {
                if (restaurants.isEmpty()) {
                    replace(R.id.main_content, EmptyStateFragment.newInstance(emptyStateRepository.emptyListIssue()));
                } else {
                    replace(R.id.main_content, RestaurantListFragment.newInstance(restaurants));
                }
            } else {
                replace(R.id.main_content, EmptyStateFragment.newInstance(emptyStateRepository.serverIssue()));
            }
        });
    }

}