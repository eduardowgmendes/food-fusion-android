package br.com.edu.jet.foodfusion.ui.component.section.item.list;

import java.util.List;

import br.com.edu.jet.foodfusion.ui.component.section.item.enums.PropertyType;

public class ListItem extends Item {

    private final List<Item> properties;

    public ListItem(String label, List<Item> items) {
        super(label);
        this.properties = items;
    }

    public List<Item> getProperties() {
        return properties;
    }

    @Override
    public int getType() {
        return PropertyType.KEY_LIST.getCode();
    }

    public static ListItem create(String key, List<Item> items) {
        return new ListItem(key, items);
    }
}
