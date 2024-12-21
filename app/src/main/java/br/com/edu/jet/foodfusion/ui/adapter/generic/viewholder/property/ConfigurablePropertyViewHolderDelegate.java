package br.com.edu.jet.foodfusion.ui.adapter.generic.viewholder.property;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;

import br.com.edu.jet.foodfusion.R;
import br.com.edu.jet.foodfusion.ui.adapter.generic.ViewHolderDelegate;
import br.com.edu.jet.foodfusion.ui.component.content.section.property.ConfigurableProperty;

public class ConfigurablePropertyViewHolderDelegate implements ViewHolderDelegate<ConfigurableProperty> {
    @Override
    public RecyclerView.ViewHolder createViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.configurable_property_layout, parent, false);
        return new ConfigurablePropertyViewHolder(view);
    }

    @Override
    public void bindViewHolder(RecyclerView.ViewHolder viewHolder, ConfigurableProperty item) {
        if (viewHolder instanceof ConfigurablePropertyViewHolder) {
            ((ConfigurablePropertyViewHolder) viewHolder).bind(item);
        }
    }

    static class ConfigurablePropertyViewHolder extends RecyclerView.ViewHolder {

        private final TextView propertyName, propertyDescription;
        private final MaterialButton actionButton;

        public ConfigurablePropertyViewHolder(@NonNull View itemView) {
            super(itemView);

            propertyName = itemView.findViewById(R.id.property_name);
            propertyDescription = itemView.findViewById(R.id.property_description);
            actionButton = itemView.findViewById(R.id.action_button);
        }

        public void bind(ConfigurableProperty property) {
            String key = property.getKey();
            propertyName.setText(key);

            String description = property.getDescription();
            if (description != null)
                propertyDescription.setText(description);
            else propertyDescription.setVisibility(View.GONE);

            actionButton.setOnClickListener(property.getActionListener());
        }
    }
}
