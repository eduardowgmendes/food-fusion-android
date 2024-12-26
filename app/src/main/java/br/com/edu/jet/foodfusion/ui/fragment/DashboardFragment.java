package br.com.edu.jet.foodfusion.ui.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import br.com.edu.jet.foodfusion.R;
import br.com.edu.jet.foodfusion.ui.activity.OverviewActivity;
import br.com.edu.jet.foodfusion.ui.model.restaurant.Restaurant;

public class DashboardFragment extends Fragment {

    private static final String TAG = DashboardFragment.class.getSimpleName();

    private Restaurant restaurant;

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

        ImageView restaurantLogo = view.findViewById(R.id.restaurant_logo);

        if (restaurant.getLogo() == null)
            restaurantLogo.setImageResource(R.drawable.round_fastfood_24);

        TextView restaurantName = view.findViewById(R.id.restaurant_name);
        restaurantName.setText(restaurant.getName());

        TextView restaurantDescription = view.findViewById(R.id.restaurant_description);
        restaurantDescription.setText(restaurant.getDescription());

        MaterialButton detailsButton = view.findViewById(R.id.see_details_button);
        detailsButton.setOnClickListener(v -> {
            Toast.makeText(getContext(), R.string.feature_not_implemented_message, Toast.LENGTH_SHORT).show();
        });

        return view;
    }
}