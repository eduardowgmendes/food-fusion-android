package br.com.edu.jet.foodfusion.ui.component.section.item.list;

import android.view.View;

import br.com.edu.jet.foodfusion.ui.component.section.item.enums.PropertyType;

public class CondensedItem extends Item {

    private final int iconButtonRes;
    private final View.OnClickListener actionListener;

    public CondensedItem(String label, View.OnClickListener actionListener, int iconButtonRes) {
        super(label);
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

    public static CondensedItem create(String label, View.OnClickListener actionListener, int iconBUttonRes) {
        return new CondensedItem(label, actionListener, iconBUttonRes);
    }
}
