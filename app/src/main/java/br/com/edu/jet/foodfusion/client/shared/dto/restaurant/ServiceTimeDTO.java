package br.com.edu.jet.foodfusion.client.shared.dto.restaurant;

import com.google.gson.annotations.SerializedName;

import java.time.LocalTime;
import java.util.Objects;

import br.com.edu.jet.foodfusion.utils.LocalDateTimeUtils;

public class ServiceTimeDTO {

    @SerializedName("name")
    private String name;

    @SerializedName("opensAt")
    private String opensAt;

    @SerializedName("closesAt")
    private String closesAt;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalTime getOpensAt() {
        if (opensAt == null) return null;
        return LocalDateTimeUtils.getLocalTime(opensAt);
    }

    public void setOpensAt(String opensAt) {
        this.opensAt = opensAt;
    }

    public LocalTime getClosesAt() {
        if (closesAt == null) return null;
        return LocalDateTimeUtils.getLocalTime(closesAt);
    }

    public void setClosesAt(String closesAt) {
        this.closesAt = closesAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ServiceTimeDTO)) return false;
        ServiceTimeDTO that = (ServiceTimeDTO) o;
        return Objects.equals(name, that.name) && Objects.equals(opensAt, that.opensAt) && Objects.equals(closesAt, that.closesAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, opensAt, closesAt);
    }

    @Override
    public String toString() {
        return "ServiceTimeDTO{" +
                "name='" + name + '\'' +
                ", opensAt='" + opensAt + '\'' +
                ", closesAt='" + closesAt + '\'' +
                '}';
    }
}
