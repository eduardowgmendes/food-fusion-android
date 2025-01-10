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
import br.com.edu.jet.foodfusion.ui.component.section.item.enums.PropertyType;
import br.com.edu.jet.foodfusion.ui.component.section.item.list.GridItem;
import br.com.edu.jet.foodfusion.ui.component.section.item.list.Item;

public class GridItemViewHolderDelegate implements ViewHolderDelegate<GridItem> {

    @Override
    public RecyclerView.ViewHolder createViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.grid_item_layout, parent, false);
        return new GridItemViewHolder(view);
    }

    @Override
    public void bindViewHolder(RecyclerView.ViewHolder viewHolder, GridItem item) {
        if (viewHolder instanceof GridItemViewHolder) {
            ((GridItemViewHolder) viewHolder).bind(item);
        }
    }

    static class GridItemViewHolder extends RecyclerView.ViewHolder {

        private final RecyclerView itemsList;

        public GridItemViewHolder(@NonNull View itemView) {
            super(itemView);

            itemsList = itemView.findViewById(R.id.list_items);
        }

        public void bind(GridItem item) {

            List<Item> items = item.getItems();

            if (items != null) {
                ItemsAdapter<Item> adapter = new ItemsAdapter<>(items);
                adapter.registerDelegate(PropertyType.LOGO.getCode(), new LogoItemViewHolderDelegate());
                itemsList.setAdapter(adapter);
            }
        }
    }
}
