package br.com.edu.jet.foodfusion.ui.fragment.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;
import java.util.Map;

import br.com.edu.jet.foodfusion.ui.component.section.DefaultSection;
import br.com.edu.jet.foodfusion.ui.fragment.DashboardFragment;
import br.com.edu.jet.foodfusion.ui.fragment.RestaurantSettingsFragment;

public class OverviewAdapter extends FragmentStateAdapter {

    private final List<List<DefaultSection>> sectionsList;

    public OverviewAdapter(@NonNull Fragment fragment, List<List<DefaultSection>> sectionsList) {
        super(fragment);
        this.sectionsList = sectionsList;
    }

    public OverviewAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle, List<List<DefaultSection>> sectionsList) {
        super(fragmentManager, lifecycle);
        this.sectionsList = sectionsList;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        RestaurantSettingsFragment restaurantSettingsFragment = RestaurantSettingsFragment.newInstance();
        restaurantSettingsFragment.setSections(sectionsList.get(position));
        return restaurantSettingsFragment;
    }

    @Override
    public int getItemCount() {
        return sectionsList.size();
    }

}
