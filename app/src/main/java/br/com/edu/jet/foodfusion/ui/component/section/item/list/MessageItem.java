package br.com.edu.jet.foodfusion.ui.component.section.item.list;

import android.view.View;

import br.com.edu.jet.foodfusion.ui.component.section.item.enums.PropertyType;

public class MessageItem extends ConfigurableItem {

    private final int iconMessage;

    public MessageItem(int iconMessage, String label, String description, int actionIcon, String actionLabel, View.OnClickListener actionListener) {
        super(label, description, actionIcon, actionLabel, actionListener);
        this.iconMessage = iconMessage;
    }

    public int getIconMessage() {
        return iconMessage;
    }

    @Override
    public int getType() {
        return PropertyType.MESSAGE.getCode();
    }

    public static MessageItem create(int iconMessage, String label, String description, int actionIcon, String actionLabel, View.OnClickListener actionListener) {
        return new MessageItem(iconMessage, label, description, actionIcon, actionLabel, actionListener);
    }
}
