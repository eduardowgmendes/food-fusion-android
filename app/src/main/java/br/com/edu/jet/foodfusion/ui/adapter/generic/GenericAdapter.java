package br.com.edu.jet.foodfusion.ui.adapter.generic;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenericAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<T> items;
    private final Map<Integer, ViewHolderDelegate<T>> viewHolderDelegateMap = new HashMap<>();

    public GenericAdapter(List<T> items) {
        this.items = items;
    }

    public void registerDelegate(int viewType, ViewHolderDelegate<T> delegate) {
        viewHolderDelegateMap.put(viewType, delegate);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewHolderDelegate<T> viewHolderDelegate = viewHolderDelegateMap.get(viewType);
        if (viewHolderDelegate == null)
            throw new IllegalArgumentException("No delegate registered for viewType");
        return viewHolderDelegate.createViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        T item = items.get(position);
        ViewHolderDelegate<T> viewHolderDelegate = viewHolderDelegateMap.get(getItemViewType(position));
        if (viewHolderDelegate != null) {
            viewHolderDelegate.bindViewHolder(holder, item);
        }
    }

    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }
}
