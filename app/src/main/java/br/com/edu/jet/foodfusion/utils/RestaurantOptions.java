package br.com.edu.jet.foodfusion.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.com.edu.jet.foodfusion.R;
import br.com.edu.jet.foodfusion.ui.component.section.item.list.CondensedItem;
import br.com.edu.jet.foodfusion.ui.component.section.item.list.ConfigurableItem;
import br.com.edu.jet.foodfusion.ui.component.section.item.list.MessageItem;
import br.com.edu.jet.foodfusion.ui.component.section.item.list.Item;
import br.com.edu.jet.foodfusion.ui.component.section.item.list.BasicItem;
import br.com.edu.jet.foodfusion.ui.enums.YesNoEnum;
import br.com.edu.jet.foodfusion.ui.model.restaurant.Restaurant;
import br.com.edu.jet.foodfusion.ui.model.restaurant.address.Address;
import br.com.edu.jet.foodfusion.ui.model.restaurant.menu.Menu;
import br.com.edu.jet.foodfusion.ui.model.restaurant.phone.Phone;

public class RestaurantOptions {

    private final Context context;
    private final Restaurant restaurant;

    public RestaurantOptions(Context context, Restaurant restaurant) {
        this.context = context;
        this.restaurant = restaurant;
    }

    public List<Item> getGeneralInfo() {
        List<Item> properties = new ArrayList<>();
        composeGeneralInfo(restaurant, properties);
        composeManagementDates(restaurant, properties);
        return properties;
    }

    public List<Item> getAddresses() {
        List<Item> properties = new ArrayList<>();
        composeAddresses(restaurant, properties);
        return properties;
    }

    public List<Item> getMenus() {
        List<Item> properties = new ArrayList<>();
        composeMenus(restaurant, properties);
        return properties;
    }

    public List<Item> getPhones() {
        List<Item> properties = new ArrayList<>();
        composePhones(restaurant, properties);
        return properties;
    }

    public List<Item> getManagementDates() {
        List<Item> properties = new ArrayList<>();
        composeManagementDates(restaurant, properties);
        return properties;
    }

    public List<Item> getAll() {
        return attributesOf(restaurant);
    }

    private List<Item> attributesOf(Restaurant restaurant) {
        List<Item> attributes = new ArrayList<>();
        attributes.add(new BasicItem("Id", String.valueOf(restaurant.getId())));

        composeGeneralInfo(restaurant, attributes);
        composeAddresses(restaurant, attributes);
        composeMenus(restaurant, attributes);
        composePhones(restaurant, attributes);
        composeManagementDates(restaurant, attributes);
        composeDeletion(restaurant, attributes);

        return attributes;
    }

    private void composeDeletion(Restaurant restaurant, List<Item> attributes) {
        attributes.add(new BasicItem(context.getResources().getString(R.string.deleted_title), YesNoEnum.valueOf(restaurant.isDeleted())));
    }

    private void composeManagementDates(Restaurant restaurant, List<Item> attributes) {
        LocalDateTime createdAt = restaurant.getCreatedAt();
        if (createdAt != null)
            attributes.add(new ConfigurableItem(context.getResources().getString(R.string.created_at_title), LocalDateTimeUtils.toFriendlyLocalDateTime(restaurant.getCreatedAt().toString()), R.drawable.baseline_access_time_24, context.getResources().getString(R.string.see_something_button_hint), this::showDialog));

        LocalDateTime updatedAt = restaurant.getUpdatedAt();
        if (updatedAt != null)
            attributes.add(new ConfigurableItem(context.getResources().getString(R.string.updated_at_title), LocalDateTimeUtils.toFriendlyLocalDateTime(restaurant.getUpdatedAt().toString()), R.drawable.baseline_access_time_24, context.getResources().getString(R.string.see_something_button_hint), this::showDialog));

        LocalDateTime deletedAt = restaurant.getDeletedAt();
        if (deletedAt != null)
            attributes.add(new BasicItem(context.getResources().getString(R.string.deleted_at_title), LocalDateTimeUtils.toFriendlyLocalDateTime(restaurant.getDeletedAt().toString())));
    }

    private void composeGeneralInfo(Restaurant restaurant, List<Item> attributes) {
        String logo = restaurant.getLogo();
        if (logo != null)
            attributes.add(new BasicItem(context.getResources().getString(R.string.logo_title), restaurant.getLogo()));
        else
            attributes.add(new MessageItem(R.drawable.twotone_broken_image_24, context.getResources().getString(R.string.logo_title), context.getResources().getString(R.string.no_data), R.drawable.baseline_add_24, context.getResources().getString(R.string.add_something_button_hint), this::showDialog));

        attributes.add(BasicItem.create(context.getResources().getString(R.string.name_label), restaurant.getName()));
        attributes.add(BasicItem.create(context.getResources().getString(R.string.description_label), restaurant.getDescription()));
        attributes.add(BasicItem.create(context.getResources().getString(R.string.type_label), restaurant.getType().getDescription()));
    }

    private void composeAddresses(Restaurant restaurant, List<Item> attributes) {
        List<Address> addresses = restaurant.getAddresses();
        if (addresses != null && !addresses.isEmpty()) {
            for (Address address : addresses) {
                attributes.add(new BasicItem(context.getResources().getString(R.string.address_title), address.full()));
            }
        } else {
            attributes.add(new ConfigurableItem(context.getResources().getString(R.string.addresses_title), context.getResources().getString(R.string.no_data), R.drawable.baseline_add_24, context.getResources().getString(R.string.create_something_button_hint), this::showDialog));
        }
    }

    private void composePhones(Restaurant restaurant, List<Item> attributes) {
        List<Phone> phones = restaurant.getPhones();
        attributes.add(ConfigurableItem.create("E-mails", context.getResources().getString(R.string.no_data), R.drawable.baseline_add_24, context.getResources().getString(R.string.create_something_button_hint), this::showDialog));
        if (phones != null && !phones.isEmpty()) {
            for (Phone phone : phones) {
                attributes.add(new BasicItem(context.getResources().getString(R.string.phone_title), String.format("(%s) %s", phone.getPrefix(), phone.getPhoneNumber())));
            }
        } else {
            attributes.add(new ConfigurableItem(context.getResources().getString(R.string.phones_title), context.getResources().getString(R.string.no_data), R.drawable.baseline_add_24, context.getResources().getString(R.string.create_something_button_hint), this::showDialog));
        }
    }

    private void composeMenus(Restaurant restaurant, List<Item> attributes) {
        List<Menu> menus = restaurant.getMenus();
        if (menus != null && !menus.isEmpty()) {
            for (Menu menu : menus) {
                attributes.add(new CondensedItem(menu.getName(), this::showDialog, R.drawable.baseline_chevron_right_24));
            }
        } else {
            attributes.add(new ConfigurableItem(context.getResources().getString(R.string.menus_title), context.getResources().getString(R.string.no_data), R.drawable.baseline_add_24, context.getResources().getString(R.string.create_something_button_hint), this::showDialog));
        }
    }

    private void showDialog(View view) {
        new AlertDialog.Builder(view.getContext())
                .setTitle(R.string.not_available_title)
                .setMessage(R.string.feature_not_implemented_message)
                .setPositiveButton(R.string.ok_button_hint, (dialog, which) -> dialog.dismiss())
                .show();
    }

}
