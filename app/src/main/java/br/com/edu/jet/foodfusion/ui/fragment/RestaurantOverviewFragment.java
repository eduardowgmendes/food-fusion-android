package br.com.edu.jet.foodfusion.ui.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import br.com.edu.jet.foodfusion.R;
import br.com.edu.jet.foodfusion.ui.adapter.generic.SectionsAdapter;
import br.com.edu.jet.foodfusion.ui.adapter.generic.viewholder.OverviewSectionViewHolderDelegate;
import br.com.edu.jet.foodfusion.ui.component.content.section.DefaultSection;
import br.com.edu.jet.foodfusion.ui.component.content.section.property.Property;
import br.com.edu.jet.foodfusion.ui.enums.SectionType;
import br.com.edu.jet.foodfusion.ui.model.restaurant.Restaurant;
import br.com.edu.jet.foodfusion.utils.RestaurantOptions;

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
            restaurant = getArguments().getParcelable(TAG, Restaurant.class);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        List<DefaultSection> sections = createSections();
        SectionsAdapter<DefaultSection> adapter = new SectionsAdapter<>(sections);
        adapter.registerDelegate(SectionType.DEFAULT_SECTION.getCode(), new OverviewSectionViewHolderDelegate());

        RecyclerView properties = (RecyclerView) inflater.inflate(R.layout.fragment_restaurant_overview, container, false);
        properties.setAdapter(adapter);

        return properties;
    }

    private List<DefaultSection> createSections() {
        List<DefaultSection> sections = new ArrayList<>();
        RestaurantOptions restaurantOptions = new RestaurantOptions(restaurant);
        sections.add(createSection("Restaurant", "Manage general information about the restaurant, including name, description, and opening hours.", restaurantOptions.getGeneralInfo()));
        sections.add(createSection("Menus", "Create, organize, and update the restaurant's menus and items.", restaurantOptions.getMenus()));
        sections.add(createSection("Contact", "Register and maintain contact phone numbers for the restaurant.", restaurantOptions.getPhones()));
        sections.add(createSection("Location", "Add and manage restaurant location details for customers.", restaurantOptions.getAddresses()));

        return sections;
    }

    private DefaultSection createSection(String title, String description, List<Property> properties) {
        DefaultSection defaultSection = new DefaultSection(title, description);
        defaultSection.setPropertiesList(properties);
        return defaultSection;
    }

    private DefaultSection createSection(String title, List<Property> properties) {
        DefaultSection defaultSection = new DefaultSection(title);
        defaultSection.setPropertiesList(properties);
        return defaultSection;
    }

}