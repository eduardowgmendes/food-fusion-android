package br.com.edu.jet.foodfusion.ui.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import br.com.edu.jet.foodfusion.R;
import br.com.edu.jet.foodfusion.ui.adapter.SectionsAdapter;
import br.com.edu.jet.foodfusion.ui.adapter.delegate.section.DefaultSectionViewHolderDelegate;
import br.com.edu.jet.foodfusion.ui.component.section.DefaultSection;
import br.com.edu.jet.foodfusion.ui.component.section.item.list.Item;
import br.com.edu.jet.foodfusion.ui.component.section.enums.SectionType;
import br.com.edu.jet.foodfusion.ui.model.restaurant.Restaurant;
import br.com.edu.jet.foodfusion.utils.RestaurantOptions;

public class OverviewFragment extends Fragment {

    private static final String TAG = OverviewFragment.class.getSimpleName();
    private Restaurant restaurant;

    public OverviewFragment() {
        // Required empty public constructor
    }

    public static OverviewFragment newInstance(Restaurant restaurant) {
        OverviewFragment fragment = new OverviewFragment();
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
        adapter.registerDelegate(SectionType.DEFAULT_SECTION.getCode(), new DefaultSectionViewHolderDelegate());

        RecyclerView properties = (RecyclerView) inflater.inflate(R.layout.fragment_overview, container, false);
        properties.setAdapter(adapter);

        return properties;
    }

    private List<DefaultSection> createSections() {
        List<DefaultSection> sections = new ArrayList<>();
        RestaurantOptions restaurantOptions = new RestaurantOptions(getContext(), restaurant);
        sections.add(createSection(getString(R.string.establishment_title), getString(R.string.establishment_message), restaurantOptions.getGeneralInfo()));
        sections.add(createSection(getString(R.string.menus_title), getString(R.string.menus_message), restaurantOptions.getMenus()));
        sections.add(createSection(getString(R.string.contact_title), getString(R.string.contact_message), restaurantOptions.getPhones()));
        sections.add(createSection(getString(R.string.location_title), getString(R.string.location_message), restaurantOptions.getAddresses()));

        return sections;
    }

    private DefaultSection createSection(String title, String description, List<Item> properties) {
        DefaultSection defaultSection = new DefaultSection(title, description);
        defaultSection.setItems(properties);
        return defaultSection;
    }

    private DefaultSection createSection(String title, List<Item> properties) {
        DefaultSection defaultSection = new DefaultSection(title);
        defaultSection.setItems(properties);
        return defaultSection;
    }

}