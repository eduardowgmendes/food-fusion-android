package br.com.edu.jet.foodfusion.ui.component.content.section;

import java.util.List;
import java.util.Objects;

import br.com.edu.jet.foodfusion.ui.component.content.section.property.Property;
import br.com.edu.jet.foodfusion.ui.enums.SectionType;

public class DefaultSection extends Section {

    private List<Property> propertiesList;

    public DefaultSection(String title) {
        super(title, null);
    }

    public DefaultSection(String title, String description) {
        super(title, description);
    }

    public List<Property> getPropertiesList() {
        return propertiesList;
    }

    public void setPropertiesList(List<Property> propertiesList) {
        this.propertiesList = propertiesList;
    }

    @Override
    public int getType() {
        return SectionType.DEFAULT_SECTION.getCode();
    }
}
