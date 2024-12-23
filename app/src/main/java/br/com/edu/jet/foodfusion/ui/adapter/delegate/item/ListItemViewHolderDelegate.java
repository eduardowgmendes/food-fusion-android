package br.com.edu.jet.foodfusion.ui.adapter.delegate.item;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.edu.jet.foodfusion.R;
import br.com.edu.jet.foodfusion.ui.adapter.ItemsAdapter;
import br.com.edu.jet.foodfusion.ui.adapter.delegate.ViewHolderDelegate;
import br.com.edu.jet.foodfusion.ui.component.section.item.list.ListItem;
import br.com.edu.jet.foodfusion.ui.component.section.item.list.Item;
import br.com.edu.jet.foodfusion.ui.component.section.item.enums.PropertyType;

public class ListItemViewHolderDelegate implements ViewHolderDelegate<ListItem> {

    @Override
    public RecyclerView.ViewHolder createViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_item_layout, parent, false);
        return new ListPropertyViewHolder(view);
    }

    @Override
    public void bindViewHolder(RecyclerView.ViewHolder viewHolder, ListItem item) {
        if (viewHolder instanceof ListPropertyViewHolder) {
            ((ListPropertyViewHolder) viewHolder).bind(item);
        }
    }

    static class ListPropertyViewHolder extends RecyclerView.ViewHolder {

        private final TextView propertyName;
        private final RecyclerView properties;

        public ListPropertyViewHolder(@NonNull View itemView) {
            super(itemView);
            propertyName = itemView.findViewById(R.id.item_label);
            properties = itemView.findViewById(R.id.list_items);
        }

        public void bind(ListItem listItem) {
            propertyName.setText(listItem.getLabel());

            List<Item> childProperties = listItem.getProperties();
            if (childProperties != null) {
                ItemsAdapter<Item> adapter = new ItemsAdapter<>(childProperties);
                adapter.registerDelegate(PropertyType.KEY_VALUE.getCode(), new BasicItemViewHolderDelegate());
                adapter.registerDelegate(PropertyType.KEY_LIST.getCode(), new ListItemViewHolderDelegate());

                properties.setAdapter(adapter);
            }
        }
    }
}
