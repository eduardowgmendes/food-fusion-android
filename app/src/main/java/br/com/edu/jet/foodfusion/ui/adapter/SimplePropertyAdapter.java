package br.com.edu.jet.foodfusion.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.com.edu.jet.foodfusion.R;

public class SimplePropertyAdapter extends RecyclerView.Adapter<SimplePropertyAdapter.SimplePropertyViewHolder> {

    private final Map<String, String> properties;
    private final List<String> keys;

    public SimplePropertyAdapter(Map<String, String> properties) {
        this.properties = properties;
        this.keys = new ArrayList<>(properties.keySet());
    }

    @Override
    public int getItemCount() {
        return keys != null ? keys.size() : 0;
    }

    @NonNull
    @Override
    public SimplePropertyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SimplePropertyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.string_property_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SimplePropertyViewHolder holder, int position) {
        String key = keys.get(position);
        String value = properties.get(key);
        holder.bind(key, value);
    }

    public class SimplePropertyViewHolder extends RecyclerView.ViewHolder {

        private final TextView key;
        private final TextView value;

        public SimplePropertyViewHolder(@NonNull View itemView) {
            super(itemView);

            key = itemView.findViewById(R.id.property_name);
            value = itemView.findViewById(R.id.property_description);
        }

        public void bind(String key, String value) {
            this.key.setText(key);
            this.value.setText(value);
        }
    }

}
