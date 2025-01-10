package br.com.edu.jet.foodfusion.ui.component.section.item.list;

import java.util.List;

import br.com.edu.jet.foodfusion.ui.component.section.item.enums.PropertyType;

public class GridItem extends Item {

    private String description;
    private List<Item> items;

    public GridItem(String label) {
        super(label);
    }

    public GridItem(String label, String description, List<Item> items) {
        super(label);
        this.description = description;
        this.items = items;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void add(Item item) {
        items.add(item);
    }

    public void remove(Item item) {
        items.remove(item);
    }

    @Override
    public int getType() {
        return PropertyType.GRID_ITEM.getCode();
    }
}
