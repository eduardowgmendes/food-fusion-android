package br.com.edu.jet.foodfusion.ui.adapter.delegate.section;

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
import br.com.edu.jet.foodfusion.ui.adapter.delegate.item.CondensedItemViewHolderDelegate;
import br.com.edu.jet.foodfusion.ui.adapter.delegate.item.ConfigurableItemViewHolderDelegate;
import br.com.edu.jet.foodfusion.ui.adapter.delegate.item.GridItemViewHolderDelegate;
import br.com.edu.jet.foodfusion.ui.adapter.delegate.item.ListItemViewHolderDelegate;
import br.com.edu.jet.foodfusion.ui.adapter.delegate.item.LogoItemViewHolderDelegate;
import br.com.edu.jet.foodfusion.ui.adapter.delegate.item.MessageItemViewHolderDelegate;
import br.com.edu.jet.foodfusion.ui.adapter.delegate.item.BasicItemViewHolderDelegate;
import br.com.edu.jet.foodfusion.ui.adapter.delegate.item.SimpleCondensedItemViewHolderDelegate;
import br.com.edu.jet.foodfusion.ui.component.section.DefaultSection;
import br.com.edu.jet.foodfusion.ui.component.section.item.list.Item;
import br.com.edu.jet.foodfusion.ui.component.section.item.enums.PropertyType;

public class DefaultSectionViewHolderDelegate implements ViewHolderDelegate<DefaultSection> {

    @Override
    public RecyclerView.ViewHolder createViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.default_section_layout, parent, false);
        return new DefaultSectionViewHolder(view);
    }

    @Override
    public void bindViewHolder(RecyclerView.ViewHolder viewHolder, DefaultSection item) {
        DefaultSectionViewHolder defaultSectionViewHolder = (DefaultSectionViewHolder) viewHolder;
        defaultSectionViewHolder.bind(item);
    }

    public class DefaultSectionViewHolder extends RecyclerView.ViewHolder {

        private final TextView title;
        private final TextView description;
        private final RecyclerView sectionItems;

        public DefaultSectionViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.section_title);
            description = itemView.findViewById(R.id.section_description);
            sectionItems = itemView.findViewById(R.id.section_items);
        }

        public void bind(DefaultSection defaultSection) {
            title.setText(defaultSection.getTitle());

            if (defaultSection.getDescription() != null) {
                description.setText(defaultSection.getDescription());
            } else {
                description.setVisibility(View.GONE);
            }

            List<Item> properties = defaultSection.getItems();
            ItemsAdapter<Item> adapter = new ItemsAdapter<>(properties);

            adapter.registerDelegate(PropertyType.KEY_VALUE.getCode(), new BasicItemViewHolderDelegate());
            adapter.registerDelegate(PropertyType.KEY_LIST.getCode(), new ListItemViewHolderDelegate());
            adapter.registerDelegate(PropertyType.CONFIGURABLE.getCode(), new ConfigurableItemViewHolderDelegate());
            adapter.registerDelegate(PropertyType.MESSAGE.getCode(), new MessageItemViewHolderDelegate());
            adapter.registerDelegate(PropertyType.CONDENSED.getCode(), new CondensedItemViewHolderDelegate());
            adapter.registerDelegate(PropertyType.SIMPLE_CONDENSED.getCode(), new SimpleCondensedItemViewHolderDelegate());
            adapter.registerDelegate(PropertyType.LOGO.getCode(), new LogoItemViewHolderDelegate());
            adapter.registerDelegate(PropertyType.GRID_ITEM.getCode(), new GridItemViewHolderDelegate());

            sectionItems.setAdapter(adapter);
        }
    }
}
