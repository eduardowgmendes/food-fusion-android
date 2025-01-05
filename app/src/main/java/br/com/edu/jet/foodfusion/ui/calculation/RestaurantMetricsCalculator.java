package br.com.edu.jet.foodfusion.ui.calculation;

import br.com.edu.jet.foodfusion.ui.model.restaurant.Restaurant;

public class RestaurantMetricsCalculator {

    private double averageCheck;
    private double profitMargin;
    private double costOfGoodsSold;
    private double revenuePerCustomer;
    private double netProfit;
    private double roi;
    private double netPromoterScore;
    private double customerRetentionRate;
    private double customerAcquisitionRate;
    private double churnRate;
    private double customerLifetimeValue;
    private double occupancyRate;
    private double serviceTime;
    private double kitchenEfficiency;
    private double inventoryTurnover;
    private double foodWaste;
    private double energyEfficiency;
    private double digitalMarketing;
    private long onlineOrders;

    private final Restaurant restaurant;

    public RestaurantMetricsCalculator(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public double getAverageCheck() {
        return averageCheck;
    }

    public double getProfitMargin() {
        return profitMargin;
    }

    public double getCostOfGoodsSold() {
        return costOfGoodsSold;
    }

    public double getRevenuePerCustomer() {
        return revenuePerCustomer;
    }

    public double getNetProfit() {
        return netProfit;
    }

    public double getRoi() {
        return roi;
    }

    public double getNetPromoterScore() {
        return netPromoterScore;
    }

    public double getCustomerRetentionRate() {
        return customerRetentionRate;
    }

    public double getCustomerAcquisitionRate() {
        return customerAcquisitionRate;
    }

    public double getChurnRate() {
        return churnRate;
    }

    public double getCustomerLifetimeValue() {
        return customerLifetimeValue;
    }

    public double getOccupancyRate() {
        return occupancyRate;
    }

    public double getServiceTime() {
        return serviceTime;
    }

    public double getKitchenEfficiency() {
        return kitchenEfficiency;
    }

    public double getInventoryTurnover() {
        return inventoryTurnover;
    }

    public double getFoodWaste() {
        return foodWaste;
    }

    public double getEnergyEfficiency() {
        return energyEfficiency;
    }

    public double getDigitalMarketing() {
        return digitalMarketing;
    }

    public long getOnlineOrders() {
        return onlineOrders;
    }
}
