package br.com.edu.jet.foodfusion.ui.component.content.section.property;

import br.com.edu.jet.foodfusion.ui.component.content.section.property.enums.PropertyType;

public class StringProperty extends Property {

    private String value;

    public StringProperty(String key, String value) {
        super(key);
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public int getType() {
        return PropertyType.KEY_VALUE.getCode();
    }

    public static StringProperty create(String key, String value) {
        return new StringProperty(key, value);
    }
}
