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
import br.com.edu.jet.foodfusion.ui.component.section.item.list.CondensedItem;

public class CondensedItemViewHolderDelegate implements ViewHolderDelegate<CondensedItem> {

    @Override
    public RecyclerView.ViewHolder createViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.condensed_item_layout, parent, false);
        return new CondensedPropertyViewHolder(view);
    }

    @Override
    public void bindViewHolder(RecyclerView.ViewHolder viewHolder, CondensedItem item) {
        if (viewHolder instanceof CondensedPropertyViewHolder) {
            ((CondensedPropertyViewHolder) viewHolder).bind(item);
        }
    }

    static class CondensedPropertyViewHolder extends RecyclerView.ViewHolder {

        private final TextView key;
        private final MaterialButton actionButton;

        public CondensedPropertyViewHolder(@NonNull View itemView) {
            super(itemView);

            key = itemView.findViewById(R.id.item_label);
            actionButton = itemView.findViewById(R.id.action_button);
        }

        public void bind(CondensedItem property) {
            key.setText(property.getLabel());
            actionButton.setIconResource(property.getIconButtonRes());
            itemView.setOnClickListener(property.getActionListener());
            actionButton.setOnClickListener(property.getActionListener());
        }
    }
}
