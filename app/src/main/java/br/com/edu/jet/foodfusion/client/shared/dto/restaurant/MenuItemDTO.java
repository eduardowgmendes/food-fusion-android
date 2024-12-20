package br.com.edu.jet.foodfusion.client.shared.dto.restaurant;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Objects;

import br.com.edu.jet.foodfusion.client.shared.dto.picture.ShowcasePictureDTO;

public class MenuItemDTO {

    @SerializedName("name")
    private String name;

    @SerializedName("description")
    private String description;

    @SerializedName("showcasePictures")
    private List<ShowcasePictureDTO> showcasePictures;

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

    public List<ShowcasePictureDTO> getShowcasePictures() {
        return showcasePictures;
    }

    public void setShowcasePictures(List<ShowcasePictureDTO> showcasePictures) {
        this.showcasePictures = showcasePictures;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MenuItemDTO)) return false;
        MenuItemDTO that = (MenuItemDTO) o;
        return Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(showcasePictures, that.showcasePictures);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, showcasePictures);
    }

    @Override
    public String toString() {
        return "MenuItemDTO{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", showcasePictures=" + showcasePictures +
                '}';
    }
}
