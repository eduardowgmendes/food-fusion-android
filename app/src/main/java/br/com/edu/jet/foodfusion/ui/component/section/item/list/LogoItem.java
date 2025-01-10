package br.com.edu.jet.foodfusion.ui.component.section.item.list;

import br.com.edu.jet.foodfusion.ui.component.section.item.enums.PropertyType;

public class LogoItem extends Item {

    private String logoUrl;

    public LogoItem(String label, String logoUrl) {
        super(label);
        this.logoUrl = logoUrl;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    @Override
    public int getType() {
        return PropertyType.LOGO.getCode();
    }
}
