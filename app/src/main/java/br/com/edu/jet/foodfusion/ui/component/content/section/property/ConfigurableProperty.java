package br.com.edu.jet.foodfusion.ui.component.content.section.property;

import android.view.View;

import br.com.edu.jet.foodfusion.ui.component.content.section.property.enums.PropertyType;

public class ConfigurableProperty extends Property {

    private final int actionIcon;
    private final String actionLabel;
    private final View.OnClickListener actionListener;

    private final String description;

    public ConfigurableProperty(String key, String description, int actionIcon, String actionLabel, View.OnClickListener actionListener) {
        super(key);
        this.description = description;
        this.actionIcon = actionIcon;
        this.actionLabel = actionLabel;
        this.actionListener = actionListener;
    }

    public View.OnClickListener getActionListener() {
        return actionListener;
    }

    public String getDescription() {
        return description;
    }

    public int getActionIcon() {
        return actionIcon;
    }

    public String getActionLabel() {
        return actionLabel;
    }

    @Override
    public int getType() {
        return PropertyType.CONFIGURABLE.getCode();
    }
}
