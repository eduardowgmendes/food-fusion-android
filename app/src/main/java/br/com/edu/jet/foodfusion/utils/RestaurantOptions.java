package br.com.edu.jet.foodfusion.utils;

import android.app.AlertDialog;
import android.view.View;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.com.edu.jet.foodfusion.R;
import br.com.edu.jet.foodfusion.ui.component.content.section.property.CondensedProperty;
import br.com.edu.jet.foodfusion.ui.component.content.section.property.ConfigurableProperty;
import br.com.edu.jet.foodfusion.ui.component.content.section.property.MessageProperty;
import br.com.edu.jet.foodfusion.ui.component.content.section.property.Property;
import br.com.edu.jet.foodfusion.ui.component.content.section.property.StringProperty;
import br.com.edu.jet.foodfusion.ui.enums.YesNoEnum;
import br.com.edu.jet.foodfusion.ui.model.restaurant.Restaurant;
import br.com.edu.jet.foodfusion.ui.model.restaurant.address.Address;
import br.com.edu.jet.foodfusion.ui.model.restaurant.menu.Menu;
import br.com.edu.jet.foodfusion.ui.model.restaurant.phone.Phone;

public class RestaurantOptions {

    private final Restaurant restaurant;

    public RestaurantOptions(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public List<Property> getGeneralInfo() {
        List<Property> properties = new ArrayList<>();
        composeGeneralInfo(restaurant, properties);
        composeManagementDates(restaurant, properties);
        return properties;
    }

    public List<Property> getAddresses() {
        List<Property> properties = new ArrayList<>();
        composeAddresses(restaurant, properties);
        return properties;
    }

    public List<Property> getMenus() {
        List<Property> properties = new ArrayList<>();
        composeMenus(restaurant, properties);
        return properties;
    }

    public List<Property> getPhones() {
        List<Property> properties = new ArrayList<>();
        composePhones(restaurant, properties);
        return properties;
    }

    public List<Property> getManagementDates() {
        List<Property> properties = new ArrayList<>();
        composeManagementDates(restaurant, properties);
        return properties;
    }

    public List<Property> getAll() {
        return attributesOf(restaurant);
    }

    private List<Property> attributesOf(Restaurant restaurant) {
        List<Property> attributes = new ArrayList<>();
        attributes.add(new StringProperty("Id", String.valueOf(restaurant.getId())));

        composeGeneralInfo(restaurant, attributes);
        composeAddresses(restaurant, attributes);
        composeMenus(restaurant, attributes);
        composePhones(restaurant, attributes);
        composeManagementDates(restaurant, attributes);
        composeDeletion(restaurant, attributes);

        return attributes;
    }

    private void composeDeletion(Restaurant restaurant, List<Property> attributes) {
        attributes.add(new StringProperty("Deleted", YesNoEnum.valueOf(restaurant.isDeleted())));
    }

    private void composeManagementDates(Restaurant restaurant, List<Property> attributes) {
        LocalDateTime createdAt = restaurant.getCreatedAt();
        if (createdAt != null)
            attributes.add(new ConfigurableProperty("Created At", LocalDateTimeUtils.toFriendlyLocalDateTime(restaurant.getCreatedAt().toString()), R.drawable.baseline_access_time_24, "Timeline", this::showDialog));

        LocalDateTime updatedAt = restaurant.getUpdatedAt();
        if (updatedAt != null)
            attributes.add(new ConfigurableProperty("Updated At", LocalDateTimeUtils.toFriendlyLocalDateTime(restaurant.getUpdatedAt().toString()), R.drawable.baseline_access_time_24, "Timeline", this::showDialog));

        LocalDateTime deletedAt = restaurant.getDeletedAt();
        if (deletedAt != null)
            attributes.add(new StringProperty("Deleted At", LocalDateTimeUtils.toFriendlyLocalDateTime(restaurant.getDeletedAt().toString())));
    }

    private void composeGeneralInfo(Restaurant restaurant, List<Property> attributes) {
        String logo = restaurant.getLogo();
        if (logo != null)
            attributes.add(new StringProperty("Logo", restaurant.getLogo()));
        else
            attributes.add(new MessageProperty(R.drawable.baseline_error_24, "Logo", "No logo available", R.drawable.baseline_add_24, "Add", this::showDialog));
        attributes.add(new StringProperty("Name", restaurant.getName()));
        attributes.add(new StringProperty("Description", restaurant.getDescription()));
        attributes.add(new StringProperty("Type", restaurant.getType().getDescription()));
    }

    private void composeAddresses(Restaurant restaurant, List<Property> attributes) {
        List<Address> addresses = restaurant.getAddresses();
        if (addresses != null && !addresses.isEmpty()) {
            for (Address address : addresses) {
                attributes.add(new StringProperty("Address", address.full()));
            }
        } else {
            attributes.add(new ConfigurableProperty("Addresses", "No addresses available", R.drawable.baseline_add_24, "New", this::showDialog));
        }
    }

    private void composePhones(Restaurant restaurant, List<Property> attributes) {
        List<Phone> phones = restaurant.getPhones();
        if (phones != null && !phones.isEmpty()) {
            for (Phone phone : phones) {
                attributes.add(new StringProperty("Phone", String.format("(%s) %s", phone.getPrefix(), phone.getPhoneNumber())));
            }
        } else {
            attributes.add(new ConfigurableProperty("Phones", "No phones available", R.drawable.baseline_add_24, "New", this::showDialog));
        }
    }

    private void composeMenus(Restaurant restaurant, List<Property> attributes) {
        List<Menu> menus = restaurant.getMenus();
        if (menus != null && !menus.isEmpty()) {
            for (Menu menu : menus) {
                attributes.add(new CondensedProperty(menu.getName(), this::showDialog, R.drawable.baseline_chevron_right_24));
            }
        } else {
            attributes.add(new ConfigurableProperty("Menus", "No menus available", R.drawable.baseline_add_24, "New", this::showDialog));
        }
    }

    private void showDialog(View view) {
        new AlertDialog.Builder(view.getContext())
                .setTitle("Not Available")
                .setMessage("This feature is not implemented yet!")
                .setPositiveButton("Ok", (dialog, which) -> dialog.dismiss())
                .show();
    }

}
