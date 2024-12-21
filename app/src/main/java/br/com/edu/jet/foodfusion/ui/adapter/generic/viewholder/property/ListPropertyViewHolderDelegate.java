package br.com.edu.jet.foodfusion.ui.adapter.generic.viewholder.property;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.edu.jet.foodfusion.R;
import br.com.edu.jet.foodfusion.ui.adapter.generic.PropertiesAdapter;
import br.com.edu.jet.foodfusion.ui.adapter.generic.ViewHolderDelegate;
import br.com.edu.jet.foodfusion.ui.component.content.section.property.ListProperty;
import br.com.edu.jet.foodfusion.ui.component.content.section.property.Property;
import br.com.edu.jet.foodfusion.ui.component.content.section.property.enums.PropertyType;

public class ListPropertyViewHolderDelegate implements ViewHolderDelegate<ListProperty> {

    @Override
    public RecyclerView.ViewHolder createViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_property_layout, parent, false);
        return new ListPropertyViewHolder(view);
    }

    @Override
    public void bindViewHolder(RecyclerView.ViewHolder viewHolder, ListProperty item) {
        if (viewHolder instanceof ListPropertyViewHolder) {
            ((ListPropertyViewHolder) viewHolder).bind(item);
        }
    }

    static class ListPropertyViewHolder extends RecyclerView.ViewHolder {

        private final TextView propertyName;
        private final RecyclerView properties;

        public ListPropertyViewHolder(@NonNull View itemView) {
            super(itemView);
            propertyName = itemView.findViewById(R.id.property_name);
            properties = itemView.findViewById(R.id.properties);
        }

        public void bind(ListProperty listProperty) {
            propertyName.setText(listProperty.getKey());

            List<Property> childProperties = listProperty.getProperties();
            if (childProperties != null) {
                PropertiesAdapter<Property> adapter = new PropertiesAdapter<>(childProperties);
                adapter.registerDelegate(PropertyType.KEY_VALUE.getCode(), new StringPropertyViewHolderDelegate());
                adapter.registerDelegate(PropertyType.KEY_LIST.getCode(), new ListPropertyViewHolderDelegate());

                properties.setAdapter(adapter);
            }
        }
    }
}
