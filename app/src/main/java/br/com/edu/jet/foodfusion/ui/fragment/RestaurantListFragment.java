package br.com.edu.jet.foodfusion.ui.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.edu.jet.foodfusion.R;
import br.com.edu.jet.foodfusion.ui.activity.OverviewActivity;
import br.com.edu.jet.foodfusion.ui.adapter.RestaurantListAdapter;
import br.com.edu.jet.foodfusion.ui.model.restaurant.Restaurant;

public class RestaurantListFragment extends Fragment {

    public static final String TAG = RestaurantListFragment.class.getSimpleName();

    private List<Restaurant> restaurants;

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
        restaurantListAdapter.setOnItemClickListener((RestaurantListAdapter.OnItemClickListener) position -> {
            long restaurantId = restaurants.get(position).getId();

            Intent intent = new Intent(getContext(), OverviewActivity.class);
            intent.putExtra("restaurantId", restaurantId);
            startActivity(intent);
        });

        RecyclerView list = (RecyclerView) inflater.inflate(R.layout.fragment_list, container, false);
        list.setAdapter(restaurantListAdapter);

        return list;
    }
}