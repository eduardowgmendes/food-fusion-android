package br.com.edu.jet.foodfusion.client.shared.dto.restaurant;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import br.com.edu.jet.foodfusion.client.shared.dto.contact.info.AddressDTO;
import br.com.edu.jet.foodfusion.client.shared.dto.contact.info.PhoneDTO;
import br.com.edu.jet.foodfusion.ui.model.restaurant.enums.CuisineType;
import br.com.edu.jet.foodfusion.utils.LocalDateTimeUtils;

public class RestaurantDTO {

    @SerializedName("id")
    private long id;

    @SerializedName("name")
    private String name;

    @SerializedName("description")
    private String description;

    @SerializedName("type")
    private String type;

    @SerializedName("logo")
    private String logo;

    @SerializedName("addresses")
    private List<AddressDTO> addresses = new ArrayList<>();

    @SerializedName("phones")
    private List<PhoneDTO> phones = new ArrayList<>();

    @SerializedName("menus")
    private List<MenuDTO> menus = new ArrayList<>();

    @SerializedName("createdAt")
    private String createdAt;

    @SerializedName("updatedAt")
    private String updatedAt;

    @SerializedName("deletedAt")
    private String deletedAt;

    @SerializedName("deleted")
    private boolean deleted;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public CuisineType getType() {
        return CuisineType.valueOf(type);
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public List<AddressDTO> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<AddressDTO> addresses) {
        this.addresses = addresses;
    }

    public List<PhoneDTO> getPhones() {
        return phones;
    }

    public void setPhones(List<PhoneDTO> phones) {
        this.phones = phones;
    }

    public List<MenuDTO> getMenus() {
        return menus;
    }

    public void setMenus(List<MenuDTO> menus) {
        this.menus = menus;
    }

    public LocalDateTime getCreatedAt() {
        if (createdAt == null) return null;
        return LocalDateTimeUtils.getLocalDateTime(createdAt);
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        if (updatedAt == null) return null;
        return LocalDateTimeUtils.getLocalDateTime(updatedAt);
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getDeletedAt() {
        if (deletedAt == null) return null;
        return LocalDateTimeUtils.getLocalDateTime(deletedAt);
    }

    public void setDeletedAt(String deletedAt) {
        this.deletedAt = deletedAt;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RestaurantDTO)) return false;
        RestaurantDTO that = (RestaurantDTO) o;
        return id == that.id && deleted == that.deleted && Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(type, that.type) && Objects.equals(logo, that.logo) && Objects.equals(addresses, that.addresses) && Objects.equals(phones, that.phones) && Objects.equals(menus, that.menus) && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt) && Objects.equals(deletedAt, that.deletedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, type, logo, addresses, phones, menus, createdAt, updatedAt, deletedAt, deleted);
    }

    @Override
    public String toString() {
        return "RestaurantDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", logo='" + logo + '\'' +
                ", addresses=" + addresses +
                ", phones=" + phones +
                ", menus=" + menus +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", deletedAt='" + deletedAt + '\'' +
                ", deleted=" + deleted +
                '}';
    }
}
