package br.com.edu.jet.foodfusion.ui.component.section.item.list;

import br.com.edu.jet.foodfusion.ui.component.section.item.enums.PropertyType;

public class BasicItem extends Item {

    private String description;

    public BasicItem(String label, String description) {
        super(label);
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int getType() {
        return PropertyType.KEY_VALUE.getCode();
    }

    public static BasicItem create(String key, String value) {
        return new BasicItem(key, value);
    }
}
