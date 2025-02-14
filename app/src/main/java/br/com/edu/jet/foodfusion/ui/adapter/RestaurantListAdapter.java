package br.com.edu.jet.foodfusion.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.List;

import br.com.edu.jet.foodfusion.R;
import br.com.edu.jet.foodfusion.ui.model.restaurant.Restaurant;
import br.com.edu.jet.foodfusion.utils.LocalDateTimeUtils;
import br.com.edu.jet.foodfusion.utils.ResourceUtils;

public class RestaurantListAdapter extends RecyclerView.Adapter<RestaurantListAdapter.RestaurantViewHolder> {

    private List<Restaurant> restaurants;
    private OnItemClickListener onItemClickListener;

    public RestaurantListAdapter() {

    }

    public interface OnItemClickListener extends Serializable {
        void onItemClick(int position);

        void onItemLongClick(int position);
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
        private final Context context;

        public RestaurantViewHolder(@NonNull View itemView) {
            super(itemView);

            this.context = itemView.getContext();

            if (onItemClickListener != null) {
                itemView.setOnClickListener(v -> onItemClickListener.onItemClick(getAdapterPosition()));
                itemView.setOnLongClickListener(v -> {
                    onItemClickListener.onItemLongClick(getAdapterPosition());
                    return true;
                });
            }

            this.profilePicture = itemView.findViewById(R.id.restaurant_profile_picture);
            this.name = itemView.findViewById(R.id.restaurant_name);
        }

        public void bind(Restaurant restaurant) {

            ResourceUtils.init(context);

            if (restaurant.getLogo() != null)
                Picasso.get().load(restaurant.getLogo()).fit().centerCrop().into(profilePicture);

            name.setText(restaurant.getName());

            if (restaurant.isDeleted()) {
                name.setTextColor(ResourceUtils.getColor(android.R.color.darker_gray));
            }
        }
    }
}
