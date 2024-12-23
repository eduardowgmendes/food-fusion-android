package br.com.edu.jet.foodfusion.ui.component.section;

public abstract class Section {

    private final String title;
    private final String description;

    public Section(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public abstract int getType();
}
