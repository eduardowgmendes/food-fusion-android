package br.com.edu.jet.foodfusion.utils;

import static br.com.edu.jet.foodfusion.utils.ResourceUtils.getString;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.com.edu.jet.foodfusion.R;
import br.com.edu.jet.foodfusion.ui.component.section.item.list.CondensedItem;
import br.com.edu.jet.foodfusion.ui.component.section.item.list.ConfigurableItem;
import br.com.edu.jet.foodfusion.ui.component.section.item.list.GridItem;
import br.com.edu.jet.foodfusion.ui.component.section.item.list.LogoItem;
import br.com.edu.jet.foodfusion.ui.component.section.item.list.MessageItem;
import br.com.edu.jet.foodfusion.ui.component.section.item.list.Item;
import br.com.edu.jet.foodfusion.ui.component.section.item.list.BasicItem;
import br.com.edu.jet.foodfusion.ui.enums.YesNoEnum;
import br.com.edu.jet.foodfusion.ui.model.restaurant.Restaurant;
import br.com.edu.jet.foodfusion.ui.model.restaurant.servicetime.ServiceTime;
import br.com.edu.jet.foodfusion.ui.model.restaurant.address.Address;
import br.com.edu.jet.foodfusion.ui.model.restaurant.email.Email;
import br.com.edu.jet.foodfusion.ui.model.restaurant.menu.Menu;
import br.com.edu.jet.foodfusion.ui.model.restaurant.phone.Phone;
import br.com.edu.jet.foodfusion.ui.utils.CuisineTypeTranslator;

public class RestaurantSettingsComposer {

    private final Context context;
    private final Restaurant restaurant;

    public RestaurantSettingsComposer(Context context, Restaurant restaurant) {
        this.context = context;
        this.restaurant = restaurant;
        if (context != null) ResourceUtils.init(context);
    }

    public List<Item> getAll() {
        return composeFrom(restaurant);
    }

    public List<Item> getGeneralInfo() {
        List<Item> properties = new ArrayList<>();
        composeGeneralInfo(restaurant, properties);
        return properties;
    }

    public List<Item> getVisual() {
        List<Item> properties = new ArrayList<>();
        composeVisual(restaurant, properties);
        return properties;
    }

    public List<Item> getAddresses() {
        List<Item> properties = new ArrayList<>();
        composeAddresses(restaurant, properties);
        return properties;
    }

    public List<Item> getHistory() {
        List<Item> properties = new ArrayList<>();
        composeManagementDates(restaurant, properties);
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

    public List<Item> getEmails() {
        List<Item> properties = new ArrayList<>();
        composeEmails(restaurant, properties);
        return properties;
    }

    public List<Item> getManagementDates() {
        List<Item> properties = new ArrayList<>();
        composeManagementDates(restaurant, properties);
        return properties;
    }

    public List<Item> getServiceTimes(Restaurant restaurant) {
        List<Item> properties = new ArrayList<>();
        composeServiceTimes(restaurant, properties);
        return properties;
    }

    public List<Item> getStatus(Restaurant restaurant) {
        List<Item> properties = new ArrayList<>();
        composeStatus(restaurant, properties);
        return properties;
    }

    private List<Item> composeFrom(Restaurant restaurant) {
        if (restaurant != null) {
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

        return null;
    }

    private void composeStatus(Restaurant restaurant, List<Item> attributes) {
        if (restaurant.isDeleted()) {
            attributes.add(ConfigurableItem.create(
                    getString(R.string.restaurant_status_hint),
                    getString(R.string.deactivated),
                    R.drawable.baseline_check_circle_outline_24,
                    getString(R.string.activate),
                    this::showDialog));
        } else {
            attributes.add(ConfigurableItem.create(getString(R.string.restaurant_status_hint), restaurant.isDeleted() ? getString(R.string.deactivated) : getString(R.string.activated), R.drawable.baseline_error_outline_24, getString(R.string.deactivate), this::showDialog));
        }
    }

    private void composeServiceTimes(Restaurant restaurant, List<Item> attributes) {
        if (restaurant.getServiceTimes().isEmpty()) {
            attributes.add(new ConfigurableItem(
                    getString(R.string.service_time_title),
                    getString(R.string.no_data),
                    R.drawable.baseline_add_24,
                    getString(R.string.create_something_button_hint),
                    this::showDialog));
        } else {
            for (ServiceTime serviceTime : restaurant.getServiceTimes()) {
                attributes.add(new BasicItem(serviceTime.getName(), String.format("%s - %s", serviceTime.getOpensAt(), serviceTime.getClosesAt())));
            }
        }
    }

    private void composeDeletion(Restaurant restaurant, List<Item> attributes) {
        attributes.add(new BasicItem(getString(R.string.deleted_title), YesNoEnum.valueOf(restaurant.isDeleted())));
    }

    private void composeManagementDates(Restaurant restaurant, List<Item> attributes) {
        if (restaurant != null) {
            LocalDateTime createdAt = restaurant.getCreatedAt();
            if (createdAt != null)
                attributes.add(new ConfigurableItem(getString(R.string.created_at_title), LocalDateTimeUtils.toFriendlyLocalDateTime(restaurant.getCreatedAt().toString()), R.drawable.baseline_access_time_24, getString(R.string.see_something_button_hint), this::showDialog));

            LocalDateTime updatedAt = restaurant.getUpdatedAt();
            if (updatedAt != null)
                attributes.add(new ConfigurableItem(getString(R.string.updated_at_title), LocalDateTimeUtils.toFriendlyLocalDateTime(restaurant.getUpdatedAt().toString()), R.drawable.baseline_access_time_24, getString(R.string.see_something_button_hint), this::showDialog));

            LocalDateTime deletedAt = restaurant.getDeletedAt();
            if (deletedAt != null)
                attributes.add(new ConfigurableItem(getString(R.string.deleted_at_title), LocalDateTimeUtils.toFriendlyLocalDateTime(restaurant.getDeletedAt().toString()), R.drawable.baseline_access_time_24, getString(R.string.see_something_button_hint), this::showDialog));
        }
    }

    private void composeVisual(Restaurant restaurant, List<Item> attributes) {
        String logo = restaurant.getLogo();
        String backdrop = restaurant.getBackdrop();

        GridItem gridItem = new GridItem(getString(R.string.logo_backdrop_title), getString(R.string.logo_backdrop_message), new ArrayList<>());

        if (logo != null) {
            gridItem.add(new LogoItem(getString(R.string.logo_title), logo));
        } else {
            attributes.add(new MessageItem(R.drawable.twotone_broken_image_24, getString(R.string.logo_title), getString(R.string.no_data), R.drawable.baseline_add_24, getString(R.string.add_something_button_hint), this::showDialog));
        }

        if (backdrop != null) {
            gridItem.add(new LogoItem(getString(R.string.backdrop_title), backdrop));
        } else {
            attributes.add(new MessageItem(R.drawable.twotone_broken_image_24, getString(R.string.backdrop_title), getString(R.string.no_data), R.drawable.baseline_add_24, getString(R.string.add_something_button_hint), this::showDialog));
        }

        attributes.add(gridItem);
    }

    private void composeGeneralInfo(Restaurant restaurant, List<Item> attributes) {
        attributes.add(BasicItem.create(getString(R.string.name_label), restaurant.getName()));
        attributes.add(BasicItem.create(getString(R.string.description_label), restaurant.getDescription()));
        attributes.add(BasicItem.create(getString(R.string.type_label), CuisineTypeTranslator.translateType(restaurant.getType(), context)));
    }

    private void composeAddresses(Restaurant restaurant, List<Item> attributes) {
        if (restaurant != null) {
            List<Address> addresses = restaurant.getAddresses();
            if (addresses != null && !addresses.isEmpty()) {
                for (Address address : addresses) {
                    attributes.add(new BasicItem(getString(R.string.addresses_title), address.full()));
                }
            } else {
                attributes.add(new ConfigurableItem(getString(R.string.addresses_title), getString(R.string.no_data), R.drawable.baseline_add_24, getString(R.string.create_something_button_hint), this::showDialog));
            }
        } else {
            attributes.add(new ConfigurableItem(getString(R.string.addresses_title), getString(R.string.no_data), R.drawable.baseline_add_24, getString(R.string.create_something_button_hint), this::showDialog));
        }
    }

    private void composePhones(Restaurant restaurant, List<Item> attributes) {
        if (restaurant != null) {
            List<Phone> phones = restaurant.getPhones();
            if (phones != null && !phones.isEmpty()) {
                for (Phone phone : phones) {
                    attributes.add(new BasicItem(getString(R.string.phone_title), String.format("(%s) %s", phone.getPrefix(), phone.getPhoneNumber())));
                }
            } else {
                attributes.add(new ConfigurableItem(getString(R.string.phones_title), getString(R.string.no_data), R.drawable.baseline_add_24, getString(R.string.create_something_button_hint), this::showDialog));
            }
        } else {
            attributes.add(new ConfigurableItem(getString(R.string.phones_title), getString(R.string.no_data), R.drawable.baseline_add_24, getString(R.string.create_something_button_hint), this::showDialog));
        }
    }

    private void composeEmails(Restaurant restaurant, List<Item> attributes) {
        if (restaurant != null) {
            List<Email> emails = restaurant.getEmails();
            if (emails != null && !emails.isEmpty()) {
                for (Email email : emails) {
                    attributes.add(new BasicItem(getString(R.string.email_title), email.getEmail()));
                }
            } else {
                attributes.add(new ConfigurableItem(getString(R.string.email_title), getString(R.string.no_data), R.drawable.baseline_add_24, getString(R.string.create_something_button_hint), this::showDialog));
            }
        } else {
            attributes.add(new ConfigurableItem(getString(R.string.email_title), getString(R.string.no_data), R.drawable.baseline_add_24, getString(R.string.create_something_button_hint), this::showDialog));
        }
    }

    private void composeMenus(Restaurant restaurant, List<Item> attributes) {
        if (restaurant != null) {
            List<Menu> menus = restaurant.getMenus();
            if (menus != null && !menus.isEmpty()) {
                for (Menu menu : menus) {
                    attributes.add(new CondensedItem(menu.getName(), this::showDialog, R.drawable.baseline_chevron_right_24));
                }
            } else {
                attributes.add(new ConfigurableItem(getString(R.string.menus_title), getString(R.string.no_data), R.drawable.baseline_add_24, getString(R.string.create_something_button_hint), this::showDialog));
            }
        } else {
            attributes.add(new ConfigurableItem(getString(R.string.menus_title), getString(R.string.no_data), R.drawable.baseline_add_24, getString(R.string.create_something_button_hint), this::showDialog));
        }
    }

    private void showDialog(View view) {
        new AlertDialog.Builder(view.getContext()).setTitle(R.string.not_available_title).setMessage(R.string.feature_not_implemented_message).setPositiveButton(R.string.ok_button_hint, (dialog, which) -> dialog.dismiss()).show();
    }

}
