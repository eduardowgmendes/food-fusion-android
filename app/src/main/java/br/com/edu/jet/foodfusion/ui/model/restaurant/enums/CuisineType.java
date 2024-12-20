package br.com.edu.jet.foodfusion.ui.model.restaurant.enums;

public enum CuisineType {
    ITALIAN(1, "Italian"),
    JAPANESE(2, "Japanese"),
    THAI(3, "Thai"),
    CHINESE(4, "Chinese"),
    MEXICAN(5, "Mexican"),
    INDIAN(6, "Indian"),
    AMERICAN(7, "American"),
    FRENCH(8, "French"),
    BRAZILIAN(9, "Brazilian"),
    MEDITERRANEAN(10, "Mediterranean"),
    SPANISH(11, "Spanish"),
    GREEK(12, "Greek"),
    KOREAN(13, "Korean"),
    VIETNAMESE(15, "Vietnamese"),
    TURKISH(16, "Turkish"),
    ARABIC(17, "Arabic");

    private final int code;
    private final String description;

    CuisineType(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static CuisineType fromCode(int code) {
        for (CuisineType cuisineType : values())
            if (cuisineType.getCode() == code) return cuisineType;
        throw new IllegalArgumentException("Cuisine not found with given code: " + code);
    }
}
