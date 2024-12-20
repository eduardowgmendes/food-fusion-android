package br.com.edu.jet.foodfusion.client.shared.enums;

public enum GoodsCategoryEnum {
    ELECTRONICS("Electronics"),
    HOME_GOODS("Home Goods"),
    APPAREL("Apparel"),
    TOYS_AND_GAMES("Toys and Games"),
    SPORTS_AND_OUTDOORS("Sports and Outdoors"),
    AUTOMOTIVE("Automotive"),
    BEAUTY_AND_HEALTH("Beauty and Health"),
    OFFICE_SUPPLIES("Office Supplies"),
    PET_SUPPLIES("Pet Supplies"),
    TOYS("Toys"),
    BOARD_GAMES("Board Games"),
    VIDEO_GAMES("Video Games"),
    SPORTS_EQUIPMENT("Sports Equipment"),
    MEN_CLOTHING("Men Clothing"),
    WOMEN_CLOTHING("Women Clothing"),
    CHILDREN_CLOTHING("Children Clothing"),
    SHOES("Shoes"),
    ACCESSORIES("Accessories"),
    FURNITURE("Furniture"),
    KITCHENWARE("Kitchenware"),
    BATHROOM("Bathroom"),
    DECOR("Decor"),
    COMPUTERS("Computers"),
    CELL_PHONES("Cell Phone"),
    HOME_AUDIO("Home Audio"),
    APPLIANCES("Appliance"),
    PRODUCE("Produce"),
    MEAT_SEAFOOD("Meat & Seafood"),
    DAIRY("Dairy"),
    BAKERY("Bakery"),
    GROCERY("Grocery"),
    BEVERAGES("Beverages"),
    CONDIMENTS("Condiments"),
    SNACKS("Snacks"),
    FROZEN_FOOD("Frozen Food"),
    PANTRY_STAPLES("Pantry Staples"),
    CONFECTIONERY("Confectionery"),
    BAKERY_GOODS("Bakery Goods"),
    CANNED_GOODS("Canned Goods"),
    DAIRY_PRODUCTS("Dairy Products"),
    OILS_VINEGARS("Oils & Vinegars"),
    INTERNATIONAL_FOOD("International Food"),
    HEALTHY_FOOD("Healthy Food"),
    BEER_WINE_SPIRITS("Beer, Wine & Spirits");

    private final String displayName;

    GoodsCategoryEnum(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
