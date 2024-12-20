package br.com.edu.jet.foodfusion.ui.component.content.section;

import java.util.Objects;

public abstract class AbstractSection {

    private String title;
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractSection)) return false;
        AbstractSection that = (AbstractSection) o;
        return Objects.equals(title, that.title) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description);
    }

    @Override
    public String toString() {
        return "AbstractSection{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
