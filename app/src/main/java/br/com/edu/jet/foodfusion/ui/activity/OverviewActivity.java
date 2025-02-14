package br.com.edu.jet.foodfusion.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Path;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import br.com.edu.jet.foodfusion.R;
import br.com.edu.jet.foodfusion.ui.calculation.RestaurantMetricsCalculator;
import br.com.edu.jet.foodfusion.ui.component.section.DefaultSection;
import br.com.edu.jet.foodfusion.ui.component.section.item.list.Item;
import br.com.edu.jet.foodfusion.ui.component.section.item.list.SimpleCondensedItem;
import br.com.edu.jet.foodfusion.ui.fragment.sheet.OptionsBottomSheetDialog;
import br.com.edu.jet.foodfusion.ui.fragment.adapter.OverviewAdapter;
import br.com.edu.jet.foodfusion.ui.fragment.adapter.transformer.DepthPageTransformer;
import br.com.edu.jet.foodfusion.ui.model.restaurant.Restaurant;
import br.com.edu.jet.foodfusion.ui.utils.CuisineTypeTranslator;
import br.com.edu.jet.foodfusion.utils.ResourceUtils;
import br.com.edu.jet.foodfusion.utils.RestaurantSettingsComposer;
import br.com.edu.jet.foodfusion.viewmodel.RestaurantViewModel;

public class OverviewActivity extends BaseActivity {

    private ActivityResultLauncher<Intent> deleteRestaurantLauncher;

    private RestaurantViewModel restaurantViewModel;

    private long restaurantId;
    private ImageView restaurantLogo;
    private TextView restaurantName;
    private TextView restaurantDescription, restaurantType;
    private MaterialButton restaurantStatusButton, restaurantTypeButton, restaurantServiceTimeButton;
    private TabLayout sectionsTabLayout;
    private ViewPager2 overviewPager;
    private ImageView restaurantBackdrop;
    private FloatingActionButton utilityFloatingActionButton;
    private RestaurantSettingsComposer restaurantSettingsComposer;
    private MenuItem restaurantStatusMenuItem;

    private List<List<DefaultSection>> allSections;

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
        getSupportActionBar().setTitle(R.string.panel);

        restaurantLogo = findViewById(R.id.restaurant_logo);
        restaurantName = findViewById(R.id.restaurant_name);
        restaurantDescription = findViewById(R.id.restaurant_description);
        restaurantTypeButton = findViewById(R.id.restaurant_type_button);
        restaurantServiceTimeButton = findViewById(R.id.restaurant_service_time);

        restaurantBackdrop = findViewById(R.id.restaurant_backdrop);

        sectionsTabLayout = findViewById(R.id.overview_tab_layout);
        overviewPager = findViewById(R.id.overview_view_pager);
        overviewPager.setPageTransformer(new DepthPageTransformer());
        restaurantStatusButton = findViewById(R.id.restaurant_status_button);

        utilityFloatingActionButton = findViewById(R.id.utility_floating_action_button);
        utilityFloatingActionButton
                .animate()
                .alpha(0)
                .setDuration(0)
                .start();

        deleteRestaurantLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == RESULT_OK) {
                finish();
            }
        });

        restaurantViewModel = new ViewModelProvider(this).get(RestaurantViewModel.class);

        if (restaurantId != 0) {
            restaurantViewModel.getById(restaurantId).observe(this, restaurant -> {
                if (restaurant != null) {
                    configureAppBar(restaurant);

                    if (restaurantStatusMenuItem != null) {
                        restaurantStatusMenuItem.setTitle(restaurant.isDeleted() ? getString(R.string.recover) : getString(R.string.erase));
                    }

                    restaurantServiceTimeButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            OptionsBottomSheetDialog
                                    .create(getString(R.string.service_time_title), restaurantSettingsComposer.getServiceTimes(restaurant))
                                    .show(getSupportFragmentManager(), OptionsBottomSheetDialog.TAG);
                        }
                    });

                    restaurantSettingsComposer = new RestaurantSettingsComposer(this, restaurant);
                    allSections = allSections(restaurant);
                    overviewPager.setAdapter(new OverviewAdapter(getSupportFragmentManager(), getLifecycle(), allSections));
                    new TabLayoutMediator(sectionsTabLayout, overviewPager, (tab, position) -> {
                        tab.setText(getSectionTitle(position));
                    }).attach();

                    if (restaurant.getBackdrop() != null) {
                        Picasso.get()
                                .load(restaurant.getBackdrop()).fit()
                                .centerCrop().into(restaurantBackdrop);
                    }

                    overviewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
                        @Override
                        public void onPageSelected(int position) {
                            super.onPageSelected(position);
                            updateFabPosition(position);
                        }
                    });

                    restaurantTypeButton.setText(CuisineTypeTranslator.translateType(restaurant.getType(), this));

                    if (restaurant.isDeleted()) {
                        restaurantStatusButton.setIconResource(R.drawable.baseline_error_outline_24);
                        restaurantStatusButton.setIconTint(provideTint(R.color.error));
                        restaurantStatusButton.setText(R.string.erased_hint);
                    } else {
                        restaurantStatusButton.setIconResource(R.drawable.baseline_check_circle_outline_24);
                        restaurantStatusButton.setIconTint(provideTint(R.color.success));
                        restaurantStatusButton.setText(R.string.active_hint);
                    }

                    restaurantStatusButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            OptionsBottomSheetDialog
                                    .create(getString(R.string.restaurant_status_hint), restaurantSettingsComposer.getStatus(restaurant))
                                    .show(getSupportFragmentManager(), OptionsBottomSheetDialog.TAG);
                        }
                    });
                }
            });
        }
    }

    private ColorStateList provideTint(int color) {
        return ColorStateList.valueOf(ContextCompat.getColor(this, color));
    }

    private final View.OnClickListener createMenu = v -> new AlertDialog.Builder(OverviewActivity.this)
            .setTitle(R.string.not_available_title)
            .setMessage(R.string.feature_not_implemented_message)
            .setPositiveButton(R.string.ok_button_hint, (dialog, which) -> dialog.dismiss())
            .show();

    private final View.OnClickListener createAddress = v -> new AlertDialog.Builder(OverviewActivity.this)
            .setTitle(R.string.not_available_title)
            .setMessage(R.string.feature_not_implemented_message)
            .setPositiveButton(R.string.ok_button_hint, (dialog, which) -> dialog.dismiss())
            .show();

    private final View.OnClickListener createPhone = v -> new AlertDialog.Builder(OverviewActivity.this)
            .setTitle(R.string.not_available_title)
            .setMessage(R.string.feature_not_implemented_message)
            .setPositiveButton(R.string.ok_button_hint, (dialog, which) -> dialog.dismiss())
            .show();

    private final View.OnClickListener createEmail = v -> new AlertDialog.Builder(OverviewActivity.this)
            .setTitle(R.string.create_something_button_hint)
            .setMessage(R.string.feature_not_implemented_message)
            .setPositiveButton(R.string.ok_button_hint, (dialog, which) -> dialog.dismiss())
            .show();

    private void updateFabPosition(int position) {

        switch (position) {
            case 0:
            case 1:
                hideFab(utilityFloatingActionButton);
                break;
            case 2:
                utilityFloatingActionButton.setOnClickListener(createMenu);
                showFab(utilityFloatingActionButton);
                break;
            case 3:
                utilityFloatingActionButton.setOnClickListener(createAddress);
                showFab(utilityFloatingActionButton);
                break;
            case 4:
                utilityFloatingActionButton.setOnClickListener(createPhone);
                showFab(utilityFloatingActionButton);
            case 5:
                utilityFloatingActionButton.setOnClickListener(createEmail);
                showFab(utilityFloatingActionButton);
                break;
        }
    }

    private void showFab(FloatingActionButton floatingActionButton) {
        floatingActionButton
                .animate()
                .alpha(1)
                .setDuration(300)
                .start();
    }

    private void hideFab(FloatingActionButton floatingActionButton) {
        floatingActionButton
                .animate()
                .alpha(0)
                .setDuration(300)
                .start();
    }

    private void configureAppBar(Restaurant restaurant) {
        if (restaurant.getLogo() != null) {
            Picasso.get()
                    .load(restaurant.getLogo()).fit()
                    .centerCrop()
                    .into(restaurantLogo);
        } else {
            restaurantLogo.setImageResource(R.drawable.twotone_broken_image_24);
        }
        restaurantName.setText(restaurant.getName());
        restaurantDescription.setText(restaurant.getDescription());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater()
                .inflate(R.menu.restaurant_overview_menu, menu);

        restaurantStatusMenuItem = menu.findItem(R.id.action_restaurant_erase);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            Toast.makeText(this, "Entering settings", Toast.LENGTH_SHORT).show();
        }

        if (item.getItemId() == R.id.action_restaurant_delete) {
            restaurantViewModel.deleteById(restaurantId).observe(this, new Observer<Long>() {
                @Override
                public void onChanged(Long aLong) {
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("isDeleted", true);
                    setResult(Activity.RESULT_OK, resultIntent);
                    finish();
                }
            });
        }

        if (item.getItemId() == R.id.action_restaurant_erase) {
            restaurantViewModel.eraseById(restaurantId).observe(this, new Observer<Restaurant>() {
                @Override
                public void onChanged(Restaurant restaurant) {
                    if (restaurant.isDeleted()) {
                        Intent resultIntent = new Intent();
                        resultIntent.putExtra("isErased", true);
                        setResult(Activity.RESULT_OK, resultIntent);
                        finish();
                    }
                }
            });
        }

        return super.onOptionsItemSelected(item);
    }

    private String getSectionTitle(int position) {
        switch (position) {
            case 0:
                return ResourceUtils.getString(R.string.metrics_title);
            case 1:
                return ResourceUtils.getString(R.string.profile);
            case 2:
                return ResourceUtils.getString(R.string.menus);
            case 3:
                return ResourceUtils.getString(R.string.location_title);
            case 4:
                return ResourceUtils.getString(R.string.contacts_title);
            case 5:
                return ResourceUtils.getString(R.string.email_title);
            default:
                throw new IllegalArgumentException("Invalid position: " + position);
        }
    }

    private List<List<DefaultSection>> allSections(Restaurant restaurant) {
        List<List<DefaultSection>> allSections = new ArrayList<>();
        allSections.add(metrics(restaurant));
        allSections.add(generalInfo(restaurant));
        allSections.add(menus());
        allSections.add(addresses());
        allSections.add(phones());
        allSections.add(emails());
        return allSections;
    }

    private List<DefaultSection> addresses() {
        List<DefaultSection> sections = new ArrayList<>();
        sections.add(createSection(ResourceUtils.getString(R.string.location_title), ResourceUtils.getString(R.string.location_message), restaurantSettingsComposer.getAddresses()));
        return sections;
    }

    private List<DefaultSection> phones() {
        List<DefaultSection> sections = new ArrayList<>();
        sections.add(createSection(ResourceUtils.getString(R.string.contact_title), ResourceUtils.getString(R.string.contact_message), restaurantSettingsComposer.getPhones()));
        return sections;
    }

    private List<DefaultSection> emails() {
        List<DefaultSection> sections = new ArrayList<>();
        sections.add(createSection(ResourceUtils.getString(R.string.email_title), ResourceUtils.getString(R.string.contact_message), restaurantSettingsComposer.getEmails()));
        return sections;
    }

    private List<DefaultSection> menus() {
        List<DefaultSection> sections = new ArrayList<>();
        sections.add(createSection(ResourceUtils.getString(R.string.menus_title), ResourceUtils.getString(R.string.menus_message), restaurantSettingsComposer.getMenus()));
        return sections;
    }

    private List<DefaultSection> generalInfo(Restaurant restaurant) {
        List<DefaultSection> sections = new ArrayList<>();
        RestaurantSettingsComposer restaurantSettingsComposer = new RestaurantSettingsComposer(this, restaurant);
        sections.add(createSection(ResourceUtils.getString(R.string.visual_title), ResourceUtils.getString(R.string.visual_message), restaurantSettingsComposer.getVisual()));
        sections.add(createSection(ResourceUtils.getString(R.string.establishment_title), ResourceUtils.getString(R.string.establishment_message), restaurantSettingsComposer.getGeneralInfo()));
        sections.add(createSection(ResourceUtils.getString(R.string.history_title), ResourceUtils.getString(R.string.history_message), restaurantSettingsComposer.getHistory()));
        return sections;
    }

    private List<DefaultSection> history(Restaurant restaurant) {
        List<DefaultSection> sections = new ArrayList<>();
        RestaurantSettingsComposer restaurantSettingsComposer = new RestaurantSettingsComposer(this, restaurant);
        sections.add(createSection(ResourceUtils.getString(R.string.history_title), ResourceUtils.getString(R.string.history_message), restaurantSettingsComposer.getHistory()));
        return sections;
    }

    private List<DefaultSection> metrics(Restaurant restaurant) {
        List<DefaultSection> sections = new ArrayList<>();
        RestaurantMetricsCalculator calculator = new RestaurantMetricsCalculator(restaurant);
        List<Item> financeItems = List.of(
                new SimpleCondensedItem(getString(R.string.average_check_title), String.valueOf(calculator.getAverageCheck())),
                new SimpleCondensedItem(getString(R.string.profit_margin_title), String.valueOf(calculator.getAverageCheck())),
                new SimpleCondensedItem(getString(R.string.revenue_per_customer), String.valueOf(calculator.getAverageCheck())),
                new SimpleCondensedItem(getString(R.string.return_of_investment_title), String.valueOf(calculator.getAverageCheck())),
                new SimpleCondensedItem(getString(R.string.cost_of_goods_sold_title), String.valueOf(calculator.getAverageCheck()))
        );
        sections.add(createSection(getString(R.string.finance_title), getString(R.string.finance_description), financeItems));
        List<Item> clientsItems = List.of(
                new SimpleCondensedItem(getString(R.string.net_promoter_score_title), String.valueOf(calculator.getAverageCheck())),
                new SimpleCondensedItem(getString(R.string.customer_retention_rate_title), String.valueOf(calculator.getAverageCheck())),
                new SimpleCondensedItem(getString(R.string.churn_rate_title), String.valueOf(calculator.getAverageCheck())),
                new SimpleCondensedItem(getString(R.string.customer_lifetime_value_title), String.valueOf(calculator.getAverageCheck())),
                new SimpleCondensedItem(getString(R.string.average_check_title), String.valueOf(calculator.getAverageCheck()))
        );
        sections.add(createSection(getString(R.string.clients_title), getString(R.string.clients_message), clientsItems));
        List<Item> operationalItems = List.of(
                new SimpleCondensedItem(getString(R.string.occupancy_rate_title), String.valueOf(calculator.getAverageCheck())),
                new SimpleCondensedItem(getString(R.string.inventory_turnover_title), String.valueOf(calculator.getAverageCheck())),
                new SimpleCondensedItem(getString(R.string.service_time_title), String.valueOf(calculator.getAverageCheck())),
                new SimpleCondensedItem(getString(R.string.food_waste_title), String.valueOf(calculator.getAverageCheck())),
                new SimpleCondensedItem(getString(R.string.kitchen_efficiency_title), String.valueOf(calculator.getAverageCheck()))
        );
        sections.add(createSection(getString(R.string.operational_title), getString(R.string.operational_message), operationalItems));
        List<Item> otherItems = List.of(
                new SimpleCondensedItem(getString(R.string.energy_efficiency_title), String.valueOf(calculator.getAverageCheck())),
                new SimpleCondensedItem(getString(R.string.digital_marketing_title), String.valueOf(calculator.getAverageCheck()))
        );
        sections.add(createSection(getString(R.string.others_title), getString(R.string.others_message), otherItems));
        return sections;
    }

    private List<DefaultSection> finance(Restaurant restaurant) {
        List<DefaultSection> sections = new ArrayList<>();
        RestaurantMetricsCalculator calculator = new RestaurantMetricsCalculator(restaurant);
        List<Item> financeItems = List.of(
                new SimpleCondensedItem(getString(R.string.average_check_title), String.valueOf(calculator.getAverageCheck())),
                new SimpleCondensedItem(getString(R.string.profit_margin_title), String.valueOf(calculator.getAverageCheck())),
                new SimpleCondensedItem(getString(R.string.revenue_per_customer), String.valueOf(calculator.getAverageCheck())),
                new SimpleCondensedItem(getString(R.string.return_of_investment_title), String.valueOf(calculator.getAverageCheck())),
                new SimpleCondensedItem(getString(R.string.cost_of_goods_sold_title), String.valueOf(calculator.getAverageCheck()))
        );
        sections.add(createSection(getString(R.string.finance_title), getString(R.string.finance_description), financeItems));
        return sections;
    }

    private List<DefaultSection> clients(Restaurant restaurant) {
        List<DefaultSection> sections = new ArrayList<>();
        RestaurantMetricsCalculator calculator = new RestaurantMetricsCalculator(restaurant);
        List<Item> clientsItems = List.of(
                new SimpleCondensedItem(getString(R.string.net_promoter_score_title), String.valueOf(calculator.getAverageCheck())),
                new SimpleCondensedItem(getString(R.string.customer_retention_rate_title), String.valueOf(calculator.getAverageCheck())),
                new SimpleCondensedItem(getString(R.string.churn_rate_title), String.valueOf(calculator.getAverageCheck())),
                new SimpleCondensedItem(getString(R.string.customer_lifetime_value_title), String.valueOf(calculator.getAverageCheck())),
                new SimpleCondensedItem(getString(R.string.average_check_title), String.valueOf(calculator.getAverageCheck()))
        );
        sections.add(createSection(getString(R.string.clients_title), getString(R.string.clients_message), clientsItems));
        return sections;
    }

    private List<DefaultSection> operational(Restaurant restaurant) {
        List<DefaultSection> sections = new ArrayList<>();
        RestaurantMetricsCalculator calculator = new RestaurantMetricsCalculator(restaurant);
        List<Item> operationalItems = List.of(
                new SimpleCondensedItem(getString(R.string.occupancy_rate_title), String.valueOf(calculator.getAverageCheck())),
                new SimpleCondensedItem(getString(R.string.inventory_turnover_title), String.valueOf(calculator.getAverageCheck())),
                new SimpleCondensedItem(getString(R.string.service_time_title), String.valueOf(calculator.getAverageCheck())),
                new SimpleCondensedItem(getString(R.string.food_waste_title), String.valueOf(calculator.getAverageCheck())),
                new SimpleCondensedItem(getString(R.string.kitchen_efficiency_title), String.valueOf(calculator.getAverageCheck()))
        );
        sections.add(createSection(getString(R.string.operational_title), getString(R.string.operational_message), operationalItems));
        return sections;
    }

    private List<DefaultSection> others(Restaurant restaurant) {
        List<DefaultSection> sections = new ArrayList<>();
        RestaurantMetricsCalculator calculator = new RestaurantMetricsCalculator(restaurant);
        List<Item> otherItems = List.of(
                new SimpleCondensedItem(getString(R.string.energy_efficiency_title), String.valueOf(calculator.getAverageCheck())),
                new SimpleCondensedItem(getString(R.string.digital_marketing_title), String.valueOf(calculator.getAverageCheck()))
        );
        sections.add(createSection(getString(R.string.others_title), getString(R.string.others_message), otherItems));
        return sections;
    }

    private DefaultSection createSection(String title, String description, List<Item> items) {
        DefaultSection defaultSection = new DefaultSection(title, description);
        defaultSection.setItems(items);
        return defaultSection;
    }

    private DefaultSection createSection(String title, List<Item> properties) {
        DefaultSection defaultSection = new DefaultSection(title);
        defaultSection.setItems(properties);
        return defaultSection;
    }
}