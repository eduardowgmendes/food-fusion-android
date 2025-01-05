package br.com.edu.jet.foodfusion.ui.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

import br.com.edu.jet.foodfusion.R;
import br.com.edu.jet.foodfusion.ui.adapter.SectionsAdapter;
import br.com.edu.jet.foodfusion.ui.adapter.delegate.section.DefaultSectionViewHolderDelegate;
import br.com.edu.jet.foodfusion.ui.adapter.delegate.section.SimpleCondensedSectionViewHolderDelegate;
import br.com.edu.jet.foodfusion.ui.calculation.RestaurantMetricsCalculator;
import br.com.edu.jet.foodfusion.ui.component.section.DefaultSection;
import br.com.edu.jet.foodfusion.ui.component.section.enums.SectionType;
import br.com.edu.jet.foodfusion.ui.component.section.item.SimpleCondensedSection;
import br.com.edu.jet.foodfusion.ui.component.section.item.list.BasicItem;
import br.com.edu.jet.foodfusion.ui.component.section.item.list.SimpleCondensedItem;
import br.com.edu.jet.foodfusion.ui.model.restaurant.Restaurant;

public class DashboardFragment extends Fragment {

    private static final String TAG = DashboardFragment.class.getSimpleName();

    private Restaurant restaurant;

    private RestaurantMetricsCalculator metricsCalculator;

    public DashboardFragment() {
        // Required empty public constructor
    }

    public static DashboardFragment newInstance(Restaurant restaurant) {
        DashboardFragment fragment = new DashboardFragment();
        Bundle args = new Bundle();
        args.putParcelable(TAG, restaurant);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            restaurant = getArguments().getParcelable(TAG, Restaurant.class);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        metricsCalculator = new RestaurantMetricsCalculator(restaurant);

        ImageView restaurantLogo = view.findViewById(R.id.restaurant_logo);

        if (restaurant.getLogo() == null)
            restaurantLogo.setImageResource(R.drawable.round_fastfood_24);

        TextView restaurantName = view.findViewById(R.id.restaurant_name);
        restaurantName.setText(restaurant.getName());

        TextView restaurantDescription = view.findViewById(R.id.restaurant_description);
        restaurantDescription.setText(restaurant.getDescription());

        MaterialButton detailsButton = view.findViewById(R.id.see_details_button);
        detailsButton.setOnClickListener(v -> {
            getActivity()
                    .getSupportFragmentManager()
                    .beginTransaction()
                    .addToBackStack("overview")
                    .replace(R.id.overview_contents, RestaurantOverviewFragment.newInstance(restaurant))
                    .commit();
        });

        List<DefaultSection> dashboardSections = new ArrayList<>();
        dashboardSections.add(createFinanceSection());
        dashboardSections.add(createOperationalSection());
        dashboardSections.add(createClientsSection());
        dashboardSections.add(createOthersSection());

        SectionsAdapter<DefaultSection> adapter = new SectionsAdapter<>(dashboardSections);
        adapter.registerDelegate(SectionType.DEFAULT_SECTION.getCode(), new DefaultSectionViewHolderDelegate());
        adapter.registerDelegate(SectionType.SIMPLE_CONDENSED_SECTION.getCode(), new SimpleCondensedSectionViewHolderDelegate());
        RecyclerView statisticsList = view.findViewById(R.id.restaurant_statistics_list);
        statisticsList.setAdapter(adapter);

        return view;
    }

    private SimpleCondensedSection createFinanceSection() {
        SimpleCondensedSection financeSection = new SimpleCondensedSection(getString(R.string.finance_title), getString(R.string.finance_description));
        financeSection.setItems(List.of(
                SimpleCondensedItem.create(getString(R.string.average_check_title), String.valueOf(metricsCalculator.getAverageCheck())),
                SimpleCondensedItem.create(getString(R.string.profit_margin_title), String.valueOf(metricsCalculator.getProfitMargin())),
                SimpleCondensedItem.create(getString(R.string.net_profit_title), String.valueOf(metricsCalculator.getCostOfGoodsSold())),
                SimpleCondensedItem.create(getString(R.string.revenue_per_customer), String.valueOf(metricsCalculator.getRevenuePerCustomer())),
                SimpleCondensedItem.create(getString(R.string.return_of_investment_title), String.valueOf(metricsCalculator.getRoi()))
        ));
        return financeSection;
    }

    private SimpleCondensedSection createOperationalSection() {
        SimpleCondensedSection operationalSection = new SimpleCondensedSection(getString(R.string.operational_title), getString(R.string.operational_message));
        operationalSection.setItems(List.of(
                SimpleCondensedItem.create(getString(R.string.occupancy_rate_title), String.valueOf(metricsCalculator.getOccupancyRate())),
                SimpleCondensedItem.create(getString(R.string.service_time_title), String.valueOf(metricsCalculator.getServiceTime())),
                SimpleCondensedItem.create(getString(R.string.kitchen_efficiency_title), String.valueOf(metricsCalculator.getKitchenEfficiency())),
                SimpleCondensedItem.create(getString(R.string.inventory_turnover_title), String.valueOf(metricsCalculator.getInventoryTurnover())),
                SimpleCondensedItem.create(getString(R.string.food_waste_title), String.valueOf(metricsCalculator.getFoodWaste()))
        ));
        return operationalSection;
    }

    private SimpleCondensedSection createClientsSection() {
        SimpleCondensedSection clientsSection = new SimpleCondensedSection(getString(R.string.clients_title), getString(R.string.clients_message));
        clientsSection.setItems(List.of(
                SimpleCondensedItem.create(getString(R.string.net_promoter_score_title), String.valueOf(metricsCalculator.getNetPromoterScore())),
                SimpleCondensedItem.create(getString(R.string.customer_retention_rate_title), String.valueOf(metricsCalculator.getCustomerAcquisitionRate())),
                SimpleCondensedItem.create(getString(R.string.customer_acquisition_rate_title), String.valueOf(metricsCalculator.getCustomerRetentionRate())),
                SimpleCondensedItem.create(getString(R.string.churn_rate_title), String.valueOf(metricsCalculator.getChurnRate())),
                SimpleCondensedItem.create(getString(R.string.customer_lifetime_value_title), String.valueOf(metricsCalculator.getCustomerLifetimeValue()))
        ));
        return clientsSection;
    }

    private SimpleCondensedSection createOthersSection() {
        SimpleCondensedSection clientsSection = new SimpleCondensedSection(getString(R.string.others_title), getString(R.string.others_message));
        clientsSection.setItems(List.of(
                SimpleCondensedItem.create(getString(R.string.energy_efficiency_title), String.valueOf(metricsCalculator.getEnergyEfficiency())),
                SimpleCondensedItem.create(getString(R.string.digital_marketing_title), String.valueOf(metricsCalculator.getDigitalMarketing()))
        ));
        return clientsSection;
    }

}