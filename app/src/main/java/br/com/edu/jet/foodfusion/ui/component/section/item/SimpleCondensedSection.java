package br.com.edu.jet.foodfusion.ui.component.section.item;

import br.com.edu.jet.foodfusion.ui.component.section.DefaultSection;
import br.com.edu.jet.foodfusion.ui.component.section.enums.SectionType;

public class SimpleCondensedSection extends DefaultSection {

    public SimpleCondensedSection(String title) {
        super(title);
    }

    public SimpleCondensedSection(String title, String description) {
        super(title, description);
    }

    @Override
    public int getType() {
        return SectionType.SIMPLE_CONDENSED_SECTION.getCode();
    }
}
