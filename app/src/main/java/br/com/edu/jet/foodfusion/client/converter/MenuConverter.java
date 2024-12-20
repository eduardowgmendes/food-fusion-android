package br.com.edu.jet.foodfusion.client.converter;

import java.util.stream.Collectors;

import br.com.edu.jet.foodfusion.client.shared.dto.restaurant.MenuDTO;
import br.com.edu.jet.foodfusion.ui.model.restaurant.menu.Menu;

public class MenuConverter extends AttributeConverter<MenuDTO, Menu> {

    private MenuItemConverter menuItemConverter = new MenuItemConverter();

    @Override
    public MenuDTO to(Menu target) {
        MenuDTO menuDTO = new MenuDTO();
        menuDTO.setName(target.getName());
        menuDTO.setDescription(target.getDescription());
        menuDTO.setItems(target.getItems()
                .stream()
                .map(menuItemConverter::to)
                .collect(Collectors.toList()));
        return menuDTO;
    }

    @Override
    public Menu from(MenuDTO source) {
        Menu menu = new Menu();
        menu.setName(source.getName());
        menu.setDescription(source.getDescription());
        menu.setItems(source.getItems()
                .stream()
                .map(menuItemConverter::from)
                .collect(Collectors.toList()));
        return menu;
    }
}
