package br.com.edu.jet.foodfusion.ui.adapter.generic.viewholder;

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
import br.com.edu.jet.foodfusion.ui.adapter.generic.viewholder.property.ConfigurablePropertyViewHolderDelegate;
import br.com.edu.jet.foodfusion.ui.adapter.generic.viewholder.property.ListPropertyViewHolderDelegate;
import br.com.edu.jet.foodfusion.ui.adapter.generic.viewholder.property.MessagePropertyViewHolderDelegate;
import br.com.edu.jet.foodfusion.ui.adapter.generic.viewholder.property.StringPropertyViewHolderDelegate;
import br.com.edu.jet.foodfusion.ui.component.content.section.DefaultSection;
import br.com.edu.jet.foodfusion.ui.component.content.section.property.Property;
import br.com.edu.jet.foodfusion.ui.component.content.section.property.enums.PropertyType;

public class OverviewSectionViewHolderDelegate implements ViewHolderDelegate<DefaultSection> {

    @Override
    public RecyclerView.ViewHolder createViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.default_section_layout, parent, false);
        return new SectionViewHolder(view);
    }

    @Override
    public void bindViewHolder(RecyclerView.ViewHolder viewHolder, DefaultSection item) {
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

        public void bind(DefaultSection defaultSection) {
            title.setText(defaultSection.getTitle());

            if (defaultSection.getDescription() != null) {
                description.setText(defaultSection.getDescription());
            } else {
                description.setVisibility(View.GONE);
            }

            List<Property> properties = defaultSection.getPropertiesList();
            PropertiesAdapter<Property> adapter = new PropertiesAdapter<>(properties);

            adapter.registerDelegate(PropertyType.KEY_VALUE.getCode(), new StringPropertyViewHolderDelegate());
            adapter.registerDelegate(PropertyType.KEY_LIST.getCode(), new ListPropertyViewHolderDelegate());
            adapter.registerDelegate(PropertyType.CONFIGURABLE.getCode(), new ConfigurablePropertyViewHolderDelegate());
            adapter.registerDelegate(PropertyType.MESSAGE.getCode(), new MessagePropertyViewHolderDelegate());

            propertiesList.setAdapter(adapter);
        }
    }
}
