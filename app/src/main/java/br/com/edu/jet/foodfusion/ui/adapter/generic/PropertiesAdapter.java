package br.com.edu.jet.foodfusion.ui.adapter.generic;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.edu.jet.foodfusion.ui.component.content.section.property.Property;
import br.com.edu.jet.foodfusion.ui.component.content.section.property.enums.PropertyType;

public class PropertiesAdapter<T extends Property> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<T> properties;
    private final Map<Integer, ViewHolderDelegate<? extends T>> viewHolderDelegateMap = new HashMap<>();

    public PropertiesAdapter(List<T> properties) {
        this.properties = properties;
    }

    public <E extends T> void registerDelegate(int viewType, ViewHolderDelegate<E> delegate) {
        viewHolderDelegateMap.put(viewType, delegate);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewHolderDelegate<? extends T> viewHolderDelegate = viewHolderDelegateMap.get(viewType);
        if (viewHolderDelegate == null) {
            throw new IllegalArgumentException("No delegate registered for viewType: " + viewType);
        }
        return viewHolderDelegate.createViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        T item = properties.get(position);
        ViewHolderDelegate<? super T> viewHolderDelegate = (ViewHolderDelegate<? super T>) viewHolderDelegateMap.get(getItemViewType(position));
        if (viewHolderDelegate != null) {
            viewHolderDelegate.bindViewHolder(holder, item);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return PropertyType
                .fromCode(properties.get(position)
                        .getType()).getCode();
    }

    @Override
    public int getItemCount() {
        return properties != null ? properties.size() : 0;
    }

}




