package br.com.edu.jet.foodfusion.ui.adapter.generic;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Map;

import br.com.edu.jet.foodfusion.R;
import br.com.edu.jet.foodfusion.ui.adapter.SimplePropertyAdapter;
import br.com.edu.jet.foodfusion.ui.component.content.section.PropertiesListSection;

public class OverviewSectionViewHolderDelegate implements ViewHolderDelegate<PropertiesListSection> {
    @Override
    public RecyclerView.ViewHolder createViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.section_properties_list_item_layout, parent, false);
        return new SectionViewHolder(view);
    }

    @Override
    public void bindViewHolder(RecyclerView.ViewHolder viewHolder, PropertiesListSection item) {
        SectionViewHolder sectionViewHolder = (SectionViewHolder) viewHolder;
        sectionViewHolder.bind(item);
    }

    static class SectionViewHolder extends RecyclerView.ViewHolder {

        private final TextView title;
        private final TextView description;
        private final RecyclerView propertiesList;

        public SectionViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.section_title);
            description = itemView.findViewById(R.id.section_description);
            propertiesList = itemView.findViewById(R.id.properties_list);
        }

        public void bind(PropertiesListSection propertiesListSection) {
            title.setText(propertiesListSection.getTitle());

            if (propertiesListSection.getDescription() != null) {
                description.setText(propertiesListSection.getDescription());
            } else {
                description.setVisibility(View.GONE);
            }

            propertiesList.setAdapter(new SimplePropertyAdapter(propertiesListSection.getPropertiesList()));
        }
    }
}
