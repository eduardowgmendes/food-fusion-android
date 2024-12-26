package br.com.edu.jet.foodfusion.ui.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import br.com.edu.jet.foodfusion.R;
import br.com.edu.jet.foodfusion.ui.component.emptystate.EmptyState;
import br.com.edu.jet.foodfusion.ui.fragment.DashboardFragment;
import br.com.edu.jet.foodfusion.ui.fragment.EmptyStateFragment;
import br.com.edu.jet.foodfusion.ui.fragment.LoaderFragment;
import br.com.edu.jet.foodfusion.ui.fragment.RestaurantOverviewFragment;
import br.com.edu.jet.foodfusion.viewmodel.RestaurantViewModel;

public class OverviewActivity extends BaseActivity {

    private RestaurantViewModel restaurantViewModel;

    private long restaurantId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_restaurant_overview);

        restaurantId = getIntent().getLongExtra("restaurantId", 0);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.overview), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setSupportActionBar(findViewById(R.id.restaurant_overview_toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        restaurantViewModel = new ViewModelProvider(this).get(RestaurantViewModel.class);

        replace(R.id.overview_contents, LoaderFragment.newInstance());

        if (restaurantId != 0) {
            restaurantViewModel.getById(restaurantId).observe(this, restaurant -> {
                if (restaurant != null) {
                    replace(R.id.overview_contents, DashboardFragment.newInstance(restaurant));
                } else {
                    replace(R.id.overview_contents, EmptyStateFragment.newInstance(new EmptyState(R.drawable.nothing_found, getResources().getString(R.string.no_items_found_title), getResources().getString(R.string.no_items_found_message))));
                }
            });
        } else {
            replace(R.id.overview_contents, EmptyStateFragment.newInstance(new EmptyState(R.drawable.not_connected, getResources().getString(R.string.server_issue_title), getResources().getString(R.string.server_issue_message))));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater()
                .inflate(R.menu.restaurant_overview_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            Toast.makeText(this, "Entering settings", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}