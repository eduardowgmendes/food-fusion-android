package br.com.edu.jet.foodfusion.client.shared.dto.restaurant;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Objects;


public class MenuDTO {

    @SerializedName("name")
    private String name;
    @SerializedName("description")
    private String description;
    @SerializedName("items")
    private List<MenuItemDTO> items;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<MenuItemDTO> getItems() {
        return items;
    }

    public void setItems(List<MenuItemDTO> items) {
        this.items = items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MenuDTO menuDTO = (MenuDTO) o;
        return Objects.equals(name, menuDTO.name) && Objects.equals(description, menuDTO.description) && Objects.equals(items, menuDTO.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, items);
    }

    @Override
    public String toString() {
        return "MenuDTO{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", items=" + items +
                '}';
    }
}
