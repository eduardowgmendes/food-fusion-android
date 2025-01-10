package br.com.edu.jet.foodfusion.ui.component.section.item.list;

public abstract class Item {

    private String label;

    public Item() {
    }

    public Item(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public abstract int getType();
}

