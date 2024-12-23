package br.com.edu.jet.foodfusion.ui.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.edu.jet.foodfusion.ui.adapter.delegate.ViewHolderDelegate;
import br.com.edu.jet.foodfusion.ui.component.section.item.list.Item;
import br.com.edu.jet.foodfusion.ui.component.section.item.enums.PropertyType;

public class ItemsAdapter<T extends Item> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<T> items;
    private final Map<Integer, ViewHolderDelegate<? extends T>> viewHolderDelegateMap = new HashMap<>();

    public ItemsAdapter(List<T> items) {
        this.items = items;
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
        T item = items.get(position);
        ViewHolderDelegate<? super T> viewHolderDelegate = (ViewHolderDelegate<? super T>) viewHolderDelegateMap.get(getItemViewType(position));
        if (viewHolderDelegate != null) {
            viewHolderDelegate.bindViewHolder(holder, item);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return PropertyType
                .fromCode(items.get(position)
                        .getType()).getCode();
    }

    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }

}




