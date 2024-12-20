package br.com.edu.jet.foodfusion.client.converter;

import java.util.stream.Collectors;

import br.com.edu.jet.foodfusion.client.shared.dto.restaurant.MenuItemDTO;
import br.com.edu.jet.foodfusion.ui.model.restaurant.menu.MenuItem;

public class MenuItemConverter extends AttributeConverter<MenuItemDTO, MenuItem> {

    private final ShowcasePictureConverter showcasePictureConverter = new ShowcasePictureConverter();

    @Override
    public MenuItemDTO to(MenuItem target) {
        MenuItemDTO menuItemDTO = new MenuItemDTO();
        menuItemDTO.setName(target.getName());
        menuItemDTO.setDescription(target.getDescription());
        menuItemDTO.setShowcasePictures(target.getShowcasePictures()
                .stream()
                .map(showcasePictureConverter::to)
                .collect(Collectors.toList()));
        return menuItemDTO;
    }

    @Override
    public MenuItem from(MenuItemDTO source) {
        MenuItem menuItem = new MenuItem();
        menuItem.setName(source.getName());
        menuItem.setDescription(source.getDescription());
        menuItem.setShowcasePictures(source.getShowcasePictures()
                .stream()
                .map(showcasePictureConverter::from)
                .collect(Collectors.toList()));
        return menuItem;
    }
}
