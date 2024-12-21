package br.com.edu.jet.foodfusion.ui.adapter.generic.viewholder.property;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import br.com.edu.jet.foodfusion.ui.component.content.section.property.Property;

public abstract class AbstractPropertyViewHolder<T extends Property> extends RecyclerView.ViewHolder {

    public AbstractPropertyViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public abstract void bind(T property);
}
