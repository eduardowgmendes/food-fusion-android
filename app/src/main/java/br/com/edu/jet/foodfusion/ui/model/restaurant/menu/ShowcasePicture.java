package br.com.edu.jet.foodfusion.ui.model.restaurant.menu;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.Objects;

public class ShowcasePicture implements Parcelable {
    private long id;
    private String base64;

    public ShowcasePicture() {}

    protected ShowcasePicture(Parcel in) {
        id = in.readLong();
        base64 = in.readString();
    }

    public static final Creator<ShowcasePicture> CREATOR = new Creator<ShowcasePicture>() {
        @Override
        public ShowcasePicture createFromParcel(Parcel in) {
            return new ShowcasePicture(in);
        }

        @Override
        public ShowcasePicture[] newArray(int size) {
            return new ShowcasePicture[size];
        }
    };

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShowcasePicture that = (ShowcasePicture) o;
        return id == that.id && Objects.equals(base64, that.base64);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, base64);
    }

    @Override
    public String toString() {
        return "ShowcasePicture{" +
                "id=" + id +
                ", base64='" + base64 + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(base64);
    }
}
