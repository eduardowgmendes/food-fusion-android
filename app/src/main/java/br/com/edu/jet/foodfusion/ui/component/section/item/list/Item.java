package br.com.edu.jet.foodfusion.ui.component.section.item.list;

public abstract class Item {

    private final String label;

    public Item(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public abstract int getType();
}

