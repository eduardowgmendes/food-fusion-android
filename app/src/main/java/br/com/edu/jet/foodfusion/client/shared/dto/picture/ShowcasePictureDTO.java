package br.com.edu.jet.foodfusion.client.shared.dto.picture;

import java.util.Objects;

public class ShowcasePictureDTO {

    private long id;
    private String base64;

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
        ShowcasePictureDTO that = (ShowcasePictureDTO) o;
        return id == that.id && Objects.equals(base64, that.base64);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, base64);
    }

    @Override
    public String toString() {
        return "ShowcasePictureDTO{" +
                "id=" + id +
                ", base64='" + base64 + '\'' +
                '}';
    }
}
