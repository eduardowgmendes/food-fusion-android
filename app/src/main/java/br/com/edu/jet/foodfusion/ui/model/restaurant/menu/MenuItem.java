package br.com.edu.jet.foodfusion.ui.model.restaurant.menu;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.List;
import java.util.Objects;

public class MenuItem implements Parcelable {
    private String name;
    private String description;
    private List<ShowcasePicture> showcasePictures;

    public MenuItem() {
    }

    protected MenuItem(Parcel in) {
        name = in.readString();
        description = in.readString();
    }

    public static final Creator<MenuItem> CREATOR = new Creator<MenuItem>() {
        @Override
        public MenuItem createFromParcel(Parcel in) {
            return new MenuItem(in);
        }

        @Override
        public MenuItem[] newArray(int size) {
            return new MenuItem[size];
        }
    };

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

    public List<ShowcasePicture> getShowcasePictures() {
        return showcasePictures;
    }

    public void setShowcasePictures(List<ShowcasePicture> showcasePictures) {
        this.showcasePictures = showcasePictures;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MenuItem menuItem = (MenuItem) o;
        return Objects.equals(name, menuItem.name) && Objects.equals(description, menuItem.description) && Objects.equals(showcasePictures, menuItem.showcasePictures);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, showcasePictures);
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", showcasePictures=" + showcasePictures +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(description);
    }
}
