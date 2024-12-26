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
import br.com.edu.jet.foodfusion.ui.adapter.SectionsAdapter;
import br.com.edu.jet.foodfusion.ui.adapter.delegate.section.DefaultSectionViewHolderDelegate;
import br.com.edu.jet.foodfusion.ui.component.section.DefaultSection;
import br.com.edu.jet.foodfusion.ui.component.section.enums.SectionType;

public class RestaurantSettingsFragment extends Fragment {

    private static final String TAG = RestaurantSettingsFragment.class.getSimpleName();

    public RestaurantSettingsFragment() {
        // Required empty public constructor
    }

    public static RestaurantSettingsFragment newInstance() {
        RestaurantSettingsFragment fragment = new RestaurantSettingsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        List<DefaultSection> sections = getSections();
        SectionsAdapter<DefaultSection> adapter = new SectionsAdapter<>(sections);
        adapter.registerDelegate(SectionType.DEFAULT_SECTION.getCode(), new DefaultSectionViewHolderDelegate());

        RecyclerView properties = (RecyclerView) inflater.inflate(R.layout.fragment_restaurant_settings, container, false);
        properties.setAdapter(adapter);

        return properties;
    }

    private List<DefaultSection> sections = new ArrayList<>();

    public void setSections(List<DefaultSection> sections) {
        this.sections = sections;
    }

    public List<DefaultSection> getSections() {
        return sections;
    }

}