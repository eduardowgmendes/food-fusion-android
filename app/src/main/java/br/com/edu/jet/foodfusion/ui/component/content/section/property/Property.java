package br.com.edu.jet.foodfusion.ui.component.content.section.property;

public abstract class Property {

    private final String key;

    public Property(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public abstract int getType();
}

