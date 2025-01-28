package br.com.edu.jet.foodfusion.client.converter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import br.com.edu.jet.foodfusion.client.shared.dto.contact.info.AddressDTO;
import br.com.edu.jet.foodfusion.client.shared.dto.contact.info.EmailDTO;
import br.com.edu.jet.foodfusion.client.shared.dto.contact.info.PhoneDTO;
import br.com.edu.jet.foodfusion.client.shared.dto.restaurant.MenuDTO;
import br.com.edu.jet.foodfusion.client.shared.dto.restaurant.RestaurantDTO;
import br.com.edu.jet.foodfusion.ui.model.restaurant.Restaurant;
import br.com.edu.jet.foodfusion.ui.model.restaurant.servicetime.ServiceTime;
import br.com.edu.jet.foodfusion.ui.model.restaurant.address.Address;
import br.com.edu.jet.foodfusion.ui.model.restaurant.email.Email;
import br.com.edu.jet.foodfusion.ui.model.restaurant.menu.Menu;
import br.com.edu.jet.foodfusion.ui.model.restaurant.phone.Phone;

public class RestaurantConverter extends AttributeConverter<RestaurantDTO, Restaurant> {

    private final MenuConverter menuConverter = new MenuConverter();
    private final PhoneConverter phoneConverter = new PhoneConverter();
    private final AddressConverter addressConverter = new AddressConverter();
    private final EmailConverter emailConverter = new EmailConverter();
    private final ServiceTimeConverter serviceTimeConverter = new ServiceTimeConverter();

    @Override
    public RestaurantDTO to(Restaurant target) {

        Objects.requireNonNull(target, "Target cannot be null!");

        RestaurantDTO restaurantDTO = new RestaurantDTO();
        restaurantDTO.setId(target.getId());
        restaurantDTO.setName(target.getName());
        restaurantDTO.setDescription(target.getDescription());
        restaurantDTO.setType(target.getType().name());
        restaurantDTO.setBackdrop(target.getBackdrop());
        restaurantDTO.setLogo(target.getLogo());

        List<Address> addresses = target.getAddresses();
        if (addresses != null)
            if (!addresses.isEmpty())
                restaurantDTO.setAddresses(addresses
                        .stream()
                        .map(addressConverter::to)
                        .collect(Collectors.toList()));

        List<Menu> menus = target.getMenus();
        if (menus != null)
            if (!menus.isEmpty())
                restaurantDTO.setMenus(menus
                        .stream()
                        .map(menuConverter::to)
                        .collect(Collectors.toList()));


        List<Phone> phones = target.getPhones();
        if (phones != null)
            if (!phones.isEmpty())
                restaurantDTO.setPhones(phones
                        .stream()
                        .map(phoneConverter::to)
                        .collect(Collectors.toList()));

        List<Email> emails = target.getEmails();
        if (emails != null)
            if (!emails.isEmpty())
                restaurantDTO.setEmails(target.getEmails()
                        .stream()
                        .map(emailConverter::to)
                        .collect(Collectors.toList()));

        List<ServiceTime> serviceTimes = target.getServiceTimes();
        if (serviceTimes != null)
            if (!serviceTimes.isEmpty())
                restaurantDTO.setServiceTimes(serviceTimes
                        .stream()
                        .map(serviceTimeConverter::to)
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

        Objects.requireNonNull(source, "source cannot be null!");

        Restaurant restaurant = new Restaurant();
        restaurant.setId(source.getId());
        restaurant.setName(source.getName());
        restaurant.setDescription(source.getDescription());
        restaurant.setType(source.getType());
        restaurant.setLogo(source.getLogo());
        restaurant.setBackdrop(source.getBackdrop());

        List<AddressDTO> addresses = source.getAddresses();
        if (!addresses.isEmpty())
            restaurant.setAddresses(addresses
                    .stream()
                    .map(addressConverter::from)
                    .collect(Collectors.toList()));

        List<MenuDTO> menus = source.getMenus();
        if (!menus.isEmpty())
            restaurant.setMenus(menus
                    .stream()
                    .map(menuConverter::from)
                    .collect(Collectors.toList()));

        List<PhoneDTO> phones = source.getPhones();
        if (!phones.isEmpty())
            restaurant.setPhones(phones
                    .stream()
                    .map(phoneConverter::from)
                    .collect(Collectors.toList()));

        List<EmailDTO> emails = source.getEmails();
        if (!emails.isEmpty())
            restaurant.setEmails(emails
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
