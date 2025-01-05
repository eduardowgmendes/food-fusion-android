package br.com.edu.jet.foodfusion.client.converter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

import br.com.edu.jet.foodfusion.client.shared.dto.restaurant.RestaurantDTO;
import br.com.edu.jet.foodfusion.ui.model.restaurant.Restaurant;

public class RestaurantConverter extends AttributeConverter<RestaurantDTO, Restaurant> {

    private final MenuConverter menuConverter = new MenuConverter();
    private final PhoneConverter phoneConverter = new PhoneConverter();
    private final AddressConverter addressConverter = new AddressConverter();
    private final EmailConverter emailConverter = new EmailConverter();

    @Override
    public RestaurantDTO to(Restaurant target) {
        RestaurantDTO restaurantDTO = new RestaurantDTO();
        restaurantDTO.setId(target.getId());
        restaurantDTO.setName(target.getName());
        restaurantDTO.setDescription(target.getDescription());
        restaurantDTO.setType(target.getType().name());

        restaurantDTO.setAddresses(target.getAddresses()
                .stream()
                .map(addressConverter::to)
                .collect(Collectors.toList()));

        restaurantDTO.setMenus(target.getMenus()
                .stream()
                .map(menuConverter::to)
                .collect(Collectors.toList()));

        restaurantDTO.setPhones(target.getPhones()
                .stream()
                .map(phoneConverter::to)
                .collect(Collectors.toList()));

        restaurantDTO.setEmails(target.getEmails()
                .stream()
                .map(emailConverter::to)
                .collect(Collectors.toList()));

        LocalDateTime createdAt = target.getCreatedAt();
        restaurantDTO.setCreatedAt(createdAt != null ? createdAt.toString() : null);

        LocalDateTime updatedAt = target.getUpdatedAt();
        restaurantDTO.setUpdatedAt(updatedAt != null ? updatedAt.toString() : null);

        LocalDateTime deletedAt = target.getDeletedAt();
        restaurantDTO.setDeletedAt(deletedAt != null ? deletedAt.toString() : null);

        restaurantDTO.setDeleted(target.isDeleted());
        return restaurantDTO;
    }

    @Override
    public Restaurant from(RestaurantDTO source) {
        Restaurant restaurant = new Restaurant();
        restaurant.setId(source.getId());
        restaurant.setName(source.getName());
        restaurant.setDescription(source.getDescription());
        restaurant.setType(source.getType());

        restaurant.setAddresses(source.getAddresses()
                .stream()
                .map(addressConverter::from)
                .collect(Collectors.toList()));

        restaurant.setMenus(source.getMenus()
                .stream()
                .map(menuConverter::from)
                .collect(Collectors.toList()));

        restaurant.setPhones(source.getPhones()
                .stream()
                .map(phoneConverter::from)
                .collect(Collectors.toList()));

        restaurant.setEmails(source
                .getEmails()
                .stream()
                .map(emailConverter::from)
                .collect(Collectors.toList()));

        restaurant.setCreatedAt(source.getCreatedAt());
        restaurant.setUpdatedAt(source.getUpdatedAt());
        restaurant.setDeletedAt(source.getDeletedAt());
        restaurant.setDeleted(source.isDeleted());
        return restaurant;
    }


}
