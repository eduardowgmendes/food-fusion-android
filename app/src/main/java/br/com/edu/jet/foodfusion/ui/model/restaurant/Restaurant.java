package br.com.edu.jet.foodfusion.ui.model.restaurant;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import br.com.edu.jet.foodfusion.ui.model.restaurant.address.Address;
import br.com.edu.jet.foodfusion.ui.model.restaurant.enums.CuisineType;
import br.com.edu.jet.foodfusion.ui.model.restaurant.menu.Menu;
import br.com.edu.jet.foodfusion.ui.model.restaurant.phone.Phone;

public class Restaurant implements Parcelable {

    private long id;
    private String name;
    private String description;
    private CuisineType type;
    private String logo;
    private List<Address> addresses = new ArrayList<>();
    private List<Phone> phones = new ArrayList<>();
    private List<Menu> menus = new ArrayList<>();
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
    private boolean isDeleted;

    public Restaurant() {
    }

    public Restaurant(Parcel in) {
        id = in.readLong();
        name = in.readString();
        description = in.readString();
        logo = in.readString();
        type = CuisineType.valueOf(in.readString());
        addresses = in.readArrayList(Address.class.getClassLoader(), Address.class);
        phones = in.readArrayList(Phone.class.getClassLoader(), Phone.class);
        menus = in.readArrayList(Menu.class.getClassLoader(), Menu.class);
        createdAt = in.readString() != null ? LocalDateTime.parse(in.readString(), DateTimeFormatter.ISO_LOCAL_DATE_TIME) : null;
        updatedAt = in.readString() != null ? LocalDateTime.parse(in.readString(), DateTimeFormatter.ISO_LOCAL_DATE_TIME) : null;
        deletedAt = in.readString() != null ? LocalDateTime.parse(in.readString(), DateTimeFormatter.ISO_LOCAL_DATE_TIME) : null;
        isDeleted = in.readByte() != 0;
    }

    public static final Creator<Restaurant> CREATOR = new Creator<Restaurant>() {
        @Override
        public Restaurant createFromParcel(Parcel in) {
            return new Restaurant(in);
        }

        @Override
        public Restaurant[] newArray(int size) {
            return new Restaurant[size];
        }
    };

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
        return type;
    }

    public void setType(CuisineType type) {
        this.type = type;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Restaurant that = (Restaurant) o;
        return id == that.id && isDeleted == that.isDeleted && Objects.equals(name, that.name) && Objects.equals(description, that.description) && type == that.type && Objects.equals(logo, that.logo) && Objects.equals(addresses, that.addresses) && Objects.equals(phones, that.phones) && Objects.equals(menus, that.menus) && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt) && Objects.equals(deletedAt, that.deletedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, type, logo, addresses, phones, menus, createdAt, updatedAt, deletedAt, isDeleted);
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", type=" + type +
                ", logo='" + logo + '\'' +
                ", addresses=" + addresses +
                ", phones=" + phones +
                ", menus=" + menus +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", deletedAt=" + deletedAt +
                ", isDeleted=" + isDeleted +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(logo);
        dest.writeString(type.name());
        dest.writeList(addresses);
        dest.writeList(phones);
        dest.writeList(menus);
        dest.writeString(createdAt != null ? createdAt.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) : null);
        dest.writeString(updatedAt != null ? updatedAt.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) : null);
        dest.writeString(deletedAt != null ? deletedAt.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) : null);
        dest.writeByte((byte) (isDeleted ? 1 : 0));
    }
}
