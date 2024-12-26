package br.com.edu.jet.foodfusion.ui.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

import br.com.edu.jet.foodfusion.R;
import br.com.edu.jet.foodfusion.ui.component.section.DefaultSection;
import br.com.edu.jet.foodfusion.ui.component.section.item.list.Item;
import br.com.edu.jet.foodfusion.ui.fragment.adapter.OverviewAdapter;
import br.com.edu.jet.foodfusion.ui.model.restaurant.Restaurant;
import br.com.edu.jet.foodfusion.utils.ResourceUtils;
import br.com.edu.jet.foodfusion.utils.RestaurantSettingsComposer;

public class RestaurantOverviewFragment extends Fragment {

    private static final String TAG = RestaurantOverviewFragment.class.getSimpleName();
    private Restaurant restaurant;

    public RestaurantOverviewFragment() {

    }

    public static RestaurantOverviewFragment newInstance(Restaurant restaurant) {
        RestaurantOverviewFragment fragment = new RestaurantOverviewFragment();
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
            ResourceUtils.init(requireActivity().getApplicationContext());
        }
    }

    private final RestaurantSettingsComposer restaurantSettingsComposer = new RestaurantSettingsComposer(getContext(), restaurant);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootLayout = inflater.inflate(R.layout.fragment_restaurant_overview, container, false);
        ViewPager2 overviewPager = rootLayout.findViewById(R.id.overview_pager);
        List<List<DefaultSection>> allSections = allSections();
        overviewPager.setAdapter(new OverviewAdapter(this, allSections));
        TabLayout overviewTabLayout = rootLayout.findViewById(R.id.overview_tab_layout);
        new TabLayoutMediator(overviewTabLayout, overviewPager, (tab, position) -> tab.setText(getSectionTitle(position))).attach();

        return rootLayout;
    }

    private String getSectionTitle(int position) {
        switch (position) {
            case 0:
                return ResourceUtils.getString(R.string.profile);
            case 1:
                return ResourceUtils.getString(R.string.menus);
            case 2:
                return ResourceUtils.getString(R.string.location_title);
            case 3:
                return ResourceUtils.getString(R.string.contacts_title);
            default:
                throw new IllegalArgumentException("Invalid position" + position);
        }
    }

    private List<List<DefaultSection>> allSections() {
        List<List<DefaultSection>> allSections = new ArrayList<>();
        allSections.add(generalInfo());
        allSections.add(menus());
        allSections.add(addresses());
        allSections.add(phones());
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

    private List<DefaultSection> menus() {
        List<DefaultSection> sections = new ArrayList<>();
        sections.add(createSection(ResourceUtils.getString(R.string.menus_title), ResourceUtils.getString(R.string.menus_message), restaurantSettingsComposer.getMenus()));
        return sections;
    }

    private List<DefaultSection> generalInfo() {
        List<DefaultSection> sections = new ArrayList<>();
        RestaurantSettingsComposer restaurantSettingsComposer = new RestaurantSettingsComposer(getContext(), restaurant);
        sections.add(createSection(ResourceUtils.getString(R.string.establishment_title), ResourceUtils.getString(R.string.establishment_message), restaurantSettingsComposer.getGeneralInfo()));
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