package br.com.edu.jet.foodfusion.client.shared.dto.contact.info;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class EmailDTO {

    @SerializedName("email")
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmailDTO)) return false;
        EmailDTO emailDTO = (EmailDTO) o;
        return Objects.equals(email, emailDTO.email);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(email);
    }

    @Override
    public String toString() {
        return "EmailDTO{" +
                "email='" + email + '\'' +
                '}';
    }
}
