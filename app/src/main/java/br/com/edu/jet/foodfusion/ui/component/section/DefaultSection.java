package br.com.edu.jet.foodfusion.ui.component.section;

import java.util.List;

import br.com.edu.jet.foodfusion.ui.component.section.item.list.Item;
import br.com.edu.jet.foodfusion.ui.component.section.enums.SectionType;

public class DefaultSection extends Section {

    private List<Item> items;

    public DefaultSection(String title) {
        super(title, null);
    }

    public DefaultSection(String title, String description) {
        super(title, description);
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public int getType() {
        return SectionType.DEFAULT_SECTION.getCode();
    }
}
