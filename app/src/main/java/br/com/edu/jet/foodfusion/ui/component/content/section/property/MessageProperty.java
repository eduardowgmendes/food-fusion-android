package br.com.edu.jet.foodfusion.ui.component.content.section.property;

import android.view.View;

import br.com.edu.jet.foodfusion.ui.component.content.section.property.enums.PropertyType;

public class MessageProperty extends ConfigurableProperty {

    private final int icon;

    public MessageProperty(int icon, String key, String description, int actionIcon, String actionLabel, View.OnClickListener actionListener) {
        super(key, description, actionIcon, actionLabel, actionListener);
        this.icon = icon;
    }

    public int getIcon() {
        return icon;
    }

    @Override
    public int getType() {
        return PropertyType.MESSAGE.getCode();
    }
}
