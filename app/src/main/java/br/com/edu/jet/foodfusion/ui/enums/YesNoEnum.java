package br.com.edu.jet.foodfusion.ui.enums;

public enum YesNoEnum {
    YES("Yes"), NO("No");

    private final String description;

    YesNoEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static String valueOf(boolean value) {
        return value ?
                YES.getDescription() : NO.getDescription();
    }
}
