package br.com.edu.jet.foodfusion.ui.component.content.section.property;

import android.view.View;

import br.com.edu.jet.foodfusion.ui.component.content.section.property.enums.PropertyType;

public class CondensedProperty extends Property {

    private final int iconButtonRes;
    private final View.OnClickListener actionListener;

    public CondensedProperty(String key, View.OnClickListener actionListener, int iconButtonRes) {
        super(key);
        this.actionListener = actionListener;
        this.iconButtonRes = iconButtonRes;
    }

    public int getIconButtonRes() {
        return iconButtonRes;
    }

    public View.OnClickListener getActionListener() {
        return actionListener;
    }

    @Override
    public int getType() {
        return PropertyType.CONDENSED.getCode();
    }
}
