package br.com.edu.jet.foodfusion.ui.adapter.delegate.item;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;

import br.com.edu.jet.foodfusion.R;
import br.com.edu.jet.foodfusion.ui.adapter.delegate.ViewHolderDelegate;
import br.com.edu.jet.foodfusion.ui.component.section.item.list.ConfigurableItem;

public class ConfigurableItemViewHolderDelegate implements ViewHolderDelegate<ConfigurableItem> {
    @Override
    public RecyclerView.ViewHolder createViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.configurable_item_layout, parent, false);
        return new ConfigurablePropertyViewHolder(view);
    }

    @Override
    public void bindViewHolder(RecyclerView.ViewHolder viewHolder, ConfigurableItem item) {
        if (viewHolder instanceof ConfigurablePropertyViewHolder) {
            ((ConfigurablePropertyViewHolder) viewHolder).bind(item);
        }
    }

    static class ConfigurablePropertyViewHolder extends RecyclerView.ViewHolder {

        private final TextView propertyName, propertyDescription;
        private final MaterialButton actionButton;

        public ConfigurablePropertyViewHolder(@NonNull View itemView) {
            super(itemView);

            propertyName = itemView.findViewById(R.id.item_label);
            propertyDescription = itemView.findViewById(R.id.item_description);
            actionButton = itemView.findViewById(R.id.action_button);
        }

        public void bind(ConfigurableItem property) {
            String key = property.getLabel();
            propertyName.setText(key);

            String description = property.getDescription();
            if (description != null)
                propertyDescription.setText(description);
            else propertyDescription.setVisibility(View.GONE);

            actionButton.setOnClickListener(property.getActionListener());
            itemView.setOnClickListener(property.getActionListener());
            actionButton.setText(property.getActionLabel());
            actionButton.setIconResource(property.getActionIcon());
        }
    }
}
