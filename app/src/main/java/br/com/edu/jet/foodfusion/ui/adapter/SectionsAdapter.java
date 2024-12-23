package br.com.edu.jet.foodfusion.ui.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.edu.jet.foodfusion.ui.adapter.delegate.ViewHolderDelegate;
import br.com.edu.jet.foodfusion.ui.component.section.Section;
import br.com.edu.jet.foodfusion.ui.component.section.enums.SectionType;

public class SectionsAdapter<T extends Section> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<T> sections;
    private final Map<Integer, ViewHolderDelegate<? extends T>> viewHolderDelegateMap = new HashMap<>();

    public SectionsAdapter(List<T> sections) {
        this.sections = sections;
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
        T section = sections.get(position);
        ViewHolderDelegate<? super T> viewHolderDelegate = (ViewHolderDelegate<? super T>) viewHolderDelegateMap.get(getItemViewType(position));
        if (viewHolderDelegate != null) {
            viewHolderDelegate.bindViewHolder(holder, section);
        }
    }

    @Override
    public int getItemCount() {
        return sections != null ? sections.size() : 0;
    }

    @Override
    public int getItemViewType(int position) {
        return SectionType.fromCode(sections.get(position).getType()).getCode();
    }
}
