package br.com.edu.jet.foodfusion.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.edu.jet.foodfusion.R;
import br.com.edu.jet.foodfusion.ui.component.content.section.PropertiesListSection;
import br.com.edu.jet.foodfusion.ui.component.content.section.AbstractSection;

public class OverviewSectionsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<? extends AbstractSection> abstractSections;

    public OverviewSectionsAdapter() {
    }

    public OverviewSectionsAdapter(List<? extends AbstractSection> abstractSections) {
        this.abstractSections = abstractSections;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PropertyListSectionViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.section_properties_list_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        PropertyListSectionViewHolder propertyListSectionViewHolder = (PropertyListSectionViewHolder) holder;
        propertyListSectionViewHolder.bind((PropertiesListSection) abstractSections.get(position));
    }

    @Override
    public int getItemCount() {
        return abstractSections != null ? abstractSections.size() : 0;
    }

    public class PropertyListSectionViewHolder extends RecyclerView.ViewHolder {

        private final TextView title;
        private final TextView description;
        private final RecyclerView propertiesList;

        public PropertyListSectionViewHolder(@NonNull View itemView) {
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
