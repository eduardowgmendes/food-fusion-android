package br.com.edu.jet.foodfusion.ui.component.section.enums;

public enum SectionType {

    DEFAULT_SECTION(0),
    NO_BORDER_SECTION(1),
    SPECIAL_HEADER_SECTION(2),
    SET_SECTION(3);

    private final int code;

    SectionType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static SectionType fromCode(int code) {
        for (SectionType type : SectionType.values()) {
            if (type.getCode() == code) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid code: " + code);
    }
}
