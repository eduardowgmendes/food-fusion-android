package br.com.edu.jet.foodfusion.ui.model.restaurant.servicetime;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ServiceTime implements Parcelable {

    private String name;
    private LocalTime opensAt, closesAt;

    private static final DateTimeFormatter format = DateTimeFormatter.ISO_LOCAL_TIME;

    public ServiceTime() {
    }

    protected ServiceTime(Parcel in) {
        name = in.readString();

        String opensAtString = in.readString();
        opensAt = opensAtString != null ? LocalTime.parse(opensAtString, format) : null;

        String closesAtString = in.readString();
        closesAt = closesAtString != null ? LocalTime.parse(closesAtString, format) : null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalTime getOpensAt() {
        return opensAt;
    }

    public void setOpensAt(LocalTime opensAt) {
        this.opensAt = opensAt;
    }

    public LocalTime getClosesAt() {
        return closesAt;
    }

    public void setClosesAt(LocalTime closesAt) {
        this.closesAt = closesAt;
    }

    public static final Creator<ServiceTime> CREATOR = new Creator<ServiceTime>() {
        @Override
        public ServiceTime createFromParcel(Parcel in) {
            return new ServiceTime(in);
        }

        @Override
        public ServiceTime[] newArray(int size) {
            return new ServiceTime[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(opensAt != null ? opensAt.format(format) : null);
        dest.writeString(closesAt != null ? closesAt.format(format) : null);
    }
}
