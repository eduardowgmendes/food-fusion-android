package br.com.edu.jet.foodfusion.ui.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.com.edu.jet.foodfusion.R;
import br.com.edu.jet.foodfusion.ui.adapter.generic.GenericAdapter;
import br.com.edu.jet.foodfusion.ui.adapter.generic.OverviewSectionViewHolderDelegate;
import br.com.edu.jet.foodfusion.ui.component.content.section.PropertiesListSection;
import br.com.edu.jet.foodfusion.ui.model.restaurant.Restaurant;
import br.com.edu.jet.foodfusion.utils.LocalDateTimeUtils;
import br.com.edu.jet.foodfusion.utils.RestaurantUtils;

public class RestaurantOverviewFragment extends Fragment {

    private static final String TAG = RestaurantOverviewFragment.class.getSimpleName();
    private Restaurant restaurant;

    public RestaurantOverviewFragment() {
        // Required empty public constructor
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
            restaurant = getArguments().getParcelable(TAG);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        List<PropertiesListSection> sections = createSections();
        GenericAdapter<PropertiesListSection> adapter = new GenericAdapter<>(sections);
        adapter.registerDelegate(0, new OverviewSectionViewHolderDelegate());

        RecyclerView properties = (RecyclerView) inflater.inflate(R.layout.fragment_restaurant_overview, container, false);
        properties.setAdapter(adapter);

        return properties;
    }

    private List<PropertiesListSection> createSections() {
        List<PropertiesListSection> sections = new ArrayList<>();
        Map<String, String> generalProperties = RestaurantUtils.someAttributesOf(restaurant,
                "Name",
                "Description",
                "Type",
                "Created At",
                "Updated At",
                "Deleted At");

        formatAndReplaceDateOf("Created At", generalProperties);
        formatAndReplaceDateOf("Updated At", generalProperties);
        formatAndReplaceDateOf("Deleted At", generalProperties);
        sections.add(createSection("General", "Manage general information about the restaurant, including name, description, and opening hours.", generalProperties));

        Map<String, String> menusProperties = RestaurantUtils.someAttributesOf(restaurant,
                "Menus");
        sections.add(createSection("Menus", "Create, organize, and update the restaurant's menus and items.", menusProperties));

        Map<String, String> addressesProperties = RestaurantUtils.someAttributesOf(restaurant,
                "Addresses");
        sections.add(createSection("Addresses", "Add and manage restaurant location details for customers.", addressesProperties));

        Map<String, String> phonesProperties = RestaurantUtils.someAttributesOf(restaurant,
                "Phones");
        sections.add(createSection("Phones", "Register and maintain contact phone numbers for the restaurant.", phonesProperties));
        return sections;
    }

    private static void formatAndReplaceDateOf(String key, Map<String, String> allAttributes) {
        String createdAt = allAttributes.get(key);
        String createdAtFormatted = LocalDateTimeUtils.toFriendlyLocalDateTime(createdAt);
        allAttributes.replace(key, createdAtFormatted);
    }

    private PropertiesListSection createSection(String title, String description, Map<String, String> properties) {
        PropertiesListSection propertiesListSection = new PropertiesListSection();
        propertiesListSection.setTitle(title);
        propertiesListSection.setDescription(description);
        propertiesListSection.setPropertiesList(properties);
        return propertiesListSection;
    }

    private PropertiesListSection createSection(String title, Map<String, String> properties) {
        PropertiesListSection propertiesListSection = new PropertiesListSection();
        propertiesListSection.setTitle(title);
        propertiesListSection.setPropertiesList(properties);
        return propertiesListSection;
    }

}