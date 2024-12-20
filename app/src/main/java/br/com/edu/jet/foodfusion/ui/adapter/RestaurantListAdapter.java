package br.com.edu.jet.foodfusion.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.List;

import br.com.edu.jet.foodfusion.R;
import br.com.edu.jet.foodfusion.ui.model.restaurant.Restaurant;

public class RestaurantListAdapter extends RecyclerView.Adapter<RestaurantListAdapter.RestaurantViewHolder> {

    private List<Restaurant> restaurants;
    private OnItemClickListener onItemClickListener;

    public RestaurantListAdapter() {
    }

    public interface OnItemClickListener extends Serializable {
        void onItemClick(int position);
    }

    public RestaurantListAdapter(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    public void setRestaurants(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RestaurantViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurant_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantViewHolder holder, int position) {
        holder.bind(restaurants.get(position));
    }

    @Override
    public int getItemCount() {
        return restaurants != null ? restaurants.size() : 0;
    }

    public class RestaurantViewHolder extends RecyclerView.ViewHolder {

        private final ImageView profilePicture;
        private final TextView name;
        private final TextView description;
        private final TextView type;
        private final TextView location;

        public RestaurantViewHolder(@NonNull View itemView) {
            super(itemView);

            if (onItemClickListener != null)
                itemView.setOnClickListener(v -> onItemClickListener.onItemClick(getAdapterPosition()));

            this.profilePicture = itemView.findViewById(R.id.restaurant_profile_picture);
            this.name = itemView.findViewById(R.id.restaurant_name);
            this.description = itemView.findViewById(R.id.restaurant_description);
            this.type = itemView.findViewById(R.id.restaurant_type);
            this.location = itemView.findViewById(R.id.restaurant_location);
        }

        public void bind(Restaurant restaurant) {
            name.setText(restaurant.getName());
            description.setText(restaurant.getDescription());
            type.setText(restaurant.getType().getDescription());
        }
    }
}