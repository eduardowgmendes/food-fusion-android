package br.com.edu.jet.foodfusion.client.shared.dto.contact.info;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class PhoneDTO {

    @SerializedName("prefix")
    private String prefix;

    @SerializedName("phoneNumber")
    private String phoneNumber;

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhoneDTO phoneDTO = (PhoneDTO) o;
        return Objects.equals(prefix, phoneDTO.prefix) && Objects.equals(phoneNumber, phoneDTO.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(prefix, phoneNumber);
    }

    @Override
    public String toString() {
        return "PhoneDTO{" +
                "prefix='" + prefix + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
