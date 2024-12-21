package br.com.edu.jet.foodfusion.ui.component.content.section.property.enums;

public enum PropertyType {

    KEY_VALUE(0),
    KEY_LIST(1),
    CONFIGURABLE(2),
    MESSAGE(4);

    private final int code;

    PropertyType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public boolean isKeyValue() {
        return this == KEY_VALUE;
    }

    public boolean isKeyList() {
        return this == KEY_LIST;
    }

    public boolean isConfigurable() {
        return this == CONFIGURABLE;
    }

    public static PropertyType fromCode(int code) {
        for (PropertyType type : PropertyType.values()) {
            if (type.getCode() == code) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid code: " + code);
    }
}