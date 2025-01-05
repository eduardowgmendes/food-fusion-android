package br.com.edu.jet.foodfusion.ui.component.section.item.list;

import br.com.edu.jet.foodfusion.ui.component.section.item.enums.PropertyType;

public class SimpleCondensedItem extends Item {

    private final String description;

    public SimpleCondensedItem(String label, String description) {
        super(label);
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public int getType() {
        return PropertyType.SIMPLE_CONDENSED.getCode();
    }

    public static SimpleCondensedItem create(String label, String description) {
        return new SimpleCondensedItem(label, description);
    }

}
