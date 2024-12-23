package br.com.edu.jet.foodfusion.ui.adapter.delegate;

import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

public interface ViewHolderDelegate<T> {

    RecyclerView.ViewHolder createViewHolder(ViewGroup parent, int viewType);

    void bindViewHolder(RecyclerView.ViewHolder viewHolder, T item);
}
