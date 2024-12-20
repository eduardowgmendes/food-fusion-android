package br.com.edu.jet.foodfusion.utils;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import br.com.edu.jet.foodfusion.ui.enums.YesNoEnum;
import br.com.edu.jet.foodfusion.ui.model.restaurant.Restaurant;
import br.com.edu.jet.foodfusion.ui.model.restaurant.address.Address;
import br.com.edu.jet.foodfusion.ui.model.restaurant.menu.Menu;
import br.com.edu.jet.foodfusion.ui.model.restaurant.phone.Phone;

public class RestaurantUtils {

    public static Map<String, String> attributesOf(Restaurant restaurant) {
        Map<String, String> attributes = new LinkedHashMap<>();
        attributes.put("Id", String.valueOf(restaurant.getId()));
        attributes.put("Logo", restaurant.getLogo());
        attributes.put("Name", restaurant.getName());
        attributes.put("Description", restaurant.getDescription());
        attributes.put("Type", restaurant.getType().getDescription());

        List<Address> addresses = restaurant.getAddresses();
        if (addresses != null && !addresses.isEmpty()) {
            String addressesFormatted = addresses.stream().map(Address::full).collect(Collectors.joining("\n"));
            attributes.put("Addresses", addressesFormatted);
        } else {
            attributes.put("Addresses", "No addresses available");
        }

        List<Menu> menus = restaurant.getMenus();
        if (menus != null && !menus.isEmpty()) {
            for (Menu menu : menus) {
                attributes.put("Menu Name", menu.getName());
                attributes.put("Menu Description", menu.getDescription());
            }
        } else {
            attributes.put("Menus", "No menus available");
        }

        List<Phone> phones = restaurant.getPhones();
        if (phones != null && !phones.isEmpty()) {
            String phonesFormatted = phones.stream().map(Phone::toString).collect(Collectors.joining("\n"));
            attributes.put("Phones", phonesFormatted);
        } else {
            attributes.put("Phones", "No phones available");
        }

        LocalDateTime createdAt = restaurant.getCreatedAt();
        if (createdAt != null)
            attributes.put("Created At", restaurant.getCreatedAt().toString());

        LocalDateTime updatedAt = restaurant.getUpdatedAt();
        if (updatedAt != null)
            attributes.put("Updated At", restaurant.getUpdatedAt().toString());

        LocalDateTime deletedAt = restaurant.getDeletedAt();
        if (deletedAt != null)
            attributes.put("Deleted At", restaurant.getDeletedAt().toString());

        attributes.put("Deleted", YesNoEnum.valueOf(restaurant.isDeleted()));

        return attributes;
    }

    public static Map<String, String> someAttributesOf(Restaurant restaurant, String... keys) {
        Map<String, String> someAttributes = new LinkedHashMap<>();
        Map<String, String> allAttributes = attributesOf(restaurant);

        for (String key : keys) {
            if (allAttributes.containsKey(key)) {
                someAttributes.put(key, allAttributes.get(key));
            }
        }

        return new LinkedHashMap<>(someAttributes);
    }


    public static String formatMap(Map<String, String> map) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            formatEntry(stringBuilder, entry.getKey(), entry.getValue(), 10);
        }
        return stringBuilder.toString();
    }

    private static void formatEntry(StringBuilder builder, String key, String value, int indentLevel) {
        String indent = " ".repeat(indentLevel);
        builder.append(indent).append(key).append(" - ").append(value).append("\n");
    }

}
