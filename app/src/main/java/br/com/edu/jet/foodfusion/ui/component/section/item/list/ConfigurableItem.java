package br.com.edu.jet.foodfusion.ui.component.section.item.list;

import android.view.View;

import br.com.edu.jet.foodfusion.ui.component.section.item.enums.PropertyType;

public class ConfigurableItem extends Item {

    private final int actionIcon;
    private final String actionLabel;
    private final View.OnClickListener actionListener;
    private final String description;

    public ConfigurableItem(String label, String description, int actionIcon, String actionLabel, View.OnClickListener actionListener) {
        super(label);
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

    public static ConfigurableItem create(String label, String description, int actionIcon, String actionLabel, View.OnClickListener actionListener) {
        return new ConfigurableItem(label, description, actionIcon, actionLabel, actionListener);
    }
}
