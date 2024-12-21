package br.com.edu.jet.foodfusion.ui.component.content.section.property;

import java.util.List;

import br.com.edu.jet.foodfusion.ui.component.content.section.property.enums.PropertyType;

public class ListProperty extends Property {

    private final List<Property> properties;

    public ListProperty(String key, List<Property> properties) {
        super(key);
        this.properties = properties;
    }

    public List<Property> getProperties() {
        return properties;
    }

    @Override
    public int getType() {
        return PropertyType.KEY_LIST.getCode();
    }

    public static ListProperty create(String key, List<Property> properties) {
        return new ListProperty(key, properties);
    }
}
