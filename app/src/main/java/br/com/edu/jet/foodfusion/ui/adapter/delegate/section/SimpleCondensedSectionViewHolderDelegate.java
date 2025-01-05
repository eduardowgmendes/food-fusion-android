package br.com.edu.jet.foodfusion.ui.adapter.delegate.section;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import br.com.edu.jet.foodfusion.R;
import br.com.edu.jet.foodfusion.ui.adapter.ItemsAdapter;
import br.com.edu.jet.foodfusion.ui.adapter.delegate.ViewHolderDelegate;
import br.com.edu.jet.foodfusion.ui.adapter.delegate.item.SimpleCondensedItemViewHolderDelegate;
import br.com.edu.jet.foodfusion.ui.component.section.item.SimpleCondensedSection;
import br.com.edu.jet.foodfusion.ui.component.section.item.enums.PropertyType;
import br.com.edu.jet.foodfusion.ui.component.section.item.list.Item;

public class SimpleCondensedSectionViewHolderDelegate implements ViewHolderDelegate<SimpleCondensedSection> {

    @Override
    public RecyclerView.ViewHolder createViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.simple_section_layout, parent, false);
        return new SimpleCondensedSectionViewHolder(view);
    }

    @Override
    public void bindViewHolder(RecyclerView.ViewHolder viewHolder, SimpleCondensedSection item) {
        if (viewHolder instanceof SimpleCondensedSectionViewHolder) {
            ((SimpleCondensedSectionViewHolder) viewHolder).bind(item);
        }
    }

    static class SimpleCondensedSectionViewHolder extends RecyclerView.ViewHolder {

        private final TextView sectionTitle, sectionDescription;
        private final RecyclerView sectionItems;

        public SimpleCondensedSectionViewHolder(@NonNull View itemView) {
            super(itemView);

            sectionTitle = itemView.findViewById(R.id.section_title);
            sectionDescription = itemView.findViewById(R.id.section_description);
            sectionItems = itemView.findViewById(R.id.section_items);
        }

        public void bind(SimpleCondensedSection item) {
            sectionTitle.setText(item.getTitle());
            sectionDescription.setText(item.getDescription());
            ItemsAdapter<Item> adapter = new ItemsAdapter<>(item.getItems());
            adapter.registerDelegate(PropertyType.SIMPLE_CONDENSED.getCode(), new SimpleCondensedItemViewHolderDelegate());
            sectionItems.setAdapter(adapter);
        }
    }
}
