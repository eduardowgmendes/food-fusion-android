package br.com.edu.jet.foodfusion.ui.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.edu.jet.foodfusion.R;
import br.com.edu.jet.foodfusion.ui.activity.CreateRestaurantActivity;
import br.com.edu.jet.foodfusion.ui.activity.MainActivity;
import br.com.edu.jet.foodfusion.ui.activity.OverviewActivity;
import br.com.edu.jet.foodfusion.ui.adapter.RestaurantListAdapter;
import br.com.edu.jet.foodfusion.ui.model.restaurant.Restaurant;
import br.com.edu.jet.foodfusion.viewmodel.RestaurantViewModel;

public class RestaurantListFragment extends Fragment {

    public static final String TAG = RestaurantListFragment.class.getSimpleName();

    private List<Restaurant> restaurants;
    private RestaurantViewModel restaurantViewModel;
    private ActivityResultLauncher<Intent> activityResultLauncher;

    public RestaurantListFragment() {
        // Required empty public constructor
    }

    public static RestaurantListFragment newInstance(List<Restaurant> restaurants) {
        RestaurantListFragment fragment = new RestaurantListFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(TAG, (ArrayList<? extends Parcelable>) restaurants);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.restaurants = getArguments().getParcelableArrayList(TAG, Restaurant.class);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        RestaurantListAdapter restaurantListAdapter = new RestaurantListAdapter(restaurants);
        restaurantListAdapter.setOnItemClickListener(new RestaurantListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                long restaurantId = restaurants.get(position).getId();

                Intent intent = new Intent(getContext(), OverviewActivity.class);
                intent.putExtra("restaurantId", restaurantId);
                activityResultLauncher.launch(intent);
            }

            @Override
            public void onItemLongClick(int position) {
                long restaurantId = restaurants.get(position).getId();
                showFullScreenContextMenu(position, restaurantId);
            }
        });

        RecyclerView list = (RecyclerView) inflater.inflate(R.layout.fragment_list, container, false);
        list.setAdapter(restaurantListAdapter);

        return list;
    }

    public void setRestaurantViewModel(RestaurantViewModel restaurantViewModel) {
        this.restaurantViewModel = restaurantViewModel;
    }

    public void setActivityResultLauncher(ActivityResultLauncher<Intent> activityResultLauncher) {
        this.activityResultLauncher = activityResultLauncher;
    }

    private void showFullScreenContextMenu(int position, long restaurantId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(R.string.context_menu_title);

        // Define the options for the menu
        String[] options = {
                getString(R.string.edit),
                getString(R.string.delete)
        };

        // Set up the click listeners for each option
        builder.setItems(options, (dialog, which) -> {
            switch (which) {
                case 0: // Edit
                    openEditRestaurantActivity(restaurantId);
                    break;

                case 1: // Delete
                    confirmDeleteRestaurant(restaurantId);
                    break;
            }
        });

        // Create and show the dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void confirmDeleteRestaurant(long restaurantId) {
        new AlertDialog.Builder(getContext())
                .setTitle(R.string.confirm_delete_title)
                .setMessage(R.string.confirm_delete_message)
                .setPositiveButton(R.string.delete, (dialog, which) -> {
                    restaurantViewModel.deleteById(restaurantId);
                    //TODO - UPDATE MAIN LIST AFTER DELETION
                })
                .setNegativeButton(R.string.cancel, null)
                .show();
    }

    private void openEditRestaurantActivity(long restaurantId) {
        Intent intent = new Intent(getContext(), OverviewActivity.class);
        intent.putExtra("restaurantId", restaurantId);
        startActivity(intent);
    }

}