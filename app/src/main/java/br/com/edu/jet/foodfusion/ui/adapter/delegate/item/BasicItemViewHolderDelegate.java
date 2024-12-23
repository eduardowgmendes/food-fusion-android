package br.com.edu.jet.foodfusion.ui.adapter.delegate.item;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import br.com.edu.jet.foodfusion.R;
import br.com.edu.jet.foodfusion.ui.adapter.delegate.ViewHolderDelegate;
import br.com.edu.jet.foodfusion.ui.component.section.item.list.BasicItem;

public class BasicItemViewHolderDelegate implements ViewHolderDelegate<BasicItem> {

    @Override
    public RecyclerView.ViewHolder createViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.basic_item_layout, parent, false);
        return new StringPropertyViewHolder(view);
    }

    @Override
    public void bindViewHolder(RecyclerView.ViewHolder viewHolder, BasicItem item) {
        if (viewHolder instanceof StringPropertyViewHolder) {
            ((StringPropertyViewHolder) viewHolder).bind(item);
        }
    }

    static class StringPropertyViewHolder extends RecyclerView.ViewHolder {

        private final TextView propertyName;
        private final TextView propertyDescription;

        public StringPropertyViewHolder(@NonNull View itemView) {
            super(itemView);
            propertyName = itemView.findViewById(R.id.item_label);
            propertyDescription = itemView.findViewById(R.id.item_description);
        }

        public void bind(BasicItem property) {
            propertyName.setText(property.getLabel());
            propertyDescription.setText(property.getDescription());
        }
    }
}
