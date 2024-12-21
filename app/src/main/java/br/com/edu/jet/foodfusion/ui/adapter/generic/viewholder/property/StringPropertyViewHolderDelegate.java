package br.com.edu.jet.foodfusion.ui.adapter.generic.viewholder.property;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import br.com.edu.jet.foodfusion.R;
import br.com.edu.jet.foodfusion.ui.adapter.generic.ViewHolderDelegate;
import br.com.edu.jet.foodfusion.ui.component.content.section.property.StringProperty;

public class StringPropertyViewHolderDelegate implements ViewHolderDelegate<StringProperty> {

    @Override
    public RecyclerView.ViewHolder createViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.string_property_layout, parent, false);
        return new StringPropertyViewHolder(view);
    }

    @Override
    public void bindViewHolder(RecyclerView.ViewHolder viewHolder, StringProperty item) {
        if (viewHolder instanceof StringPropertyViewHolder) {
            ((StringPropertyViewHolder) viewHolder).bind(item);
        }
    }

    static class StringPropertyViewHolder extends RecyclerView.ViewHolder {

        private final TextView propertyName;
        private final TextView propertyDescription;

        public StringPropertyViewHolder(@NonNull View itemView) {
            super(itemView);
            propertyName = itemView.findViewById(R.id.property_name);
            propertyDescription = itemView.findViewById(R.id.property_description);
        }

        public void bind(StringProperty property) {
            propertyName.setText(property.getKey());
            propertyDescription.setText(property.getValue());
        }
    }
}
