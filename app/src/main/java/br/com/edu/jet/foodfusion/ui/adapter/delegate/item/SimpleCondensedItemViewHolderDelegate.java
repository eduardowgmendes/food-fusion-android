package br.com.edu.jet.foodfusion.ui.adapter.delegate.item;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import br.com.edu.jet.foodfusion.R;
import br.com.edu.jet.foodfusion.ui.adapter.delegate.ViewHolderDelegate;
import br.com.edu.jet.foodfusion.ui.component.section.item.list.SimpleCondensedItem;

public class SimpleCondensedItemViewHolderDelegate implements ViewHolderDelegate<SimpleCondensedItem> {

    @Override
    public RecyclerView.ViewHolder createViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.simple_condensed_item_layout, parent, false);
        return new SimpleCondensedViewHolder(view);
    }

    @Override
    public void bindViewHolder(RecyclerView.ViewHolder viewHolder, SimpleCondensedItem item) {
        if (viewHolder instanceof SimpleCondensedViewHolder) {
            ((SimpleCondensedViewHolder) viewHolder).bind(item);
        }
    }

    static class SimpleCondensedViewHolder extends RecyclerView.ViewHolder {

        private final TextView label, description;

        public SimpleCondensedViewHolder(@NonNull View itemView) {
            super(itemView);

            label = itemView.findViewById(R.id.item_name);
            description = itemView.findViewById(R.id.item_description);
        }

        public void bind(SimpleCondensedItem item) {
            label.setText(item.getLabel());
            description.setText(item.getDescription());
        }
    }
}
