package br.com.edu.jet.foodfusion.utils;

import android.content.Context;

import br.com.edu.jet.foodfusion.ui.calculation.RestaurantMetricsCalculator;

public class RestaurantDashboardComposer {

    private Context context;
    private RestaurantMetricsCalculator restaurantMetricsCalculator;

    public RestaurantDashboardComposer(Context context, RestaurantMetricsCalculator restaurantMetricsCalculator) {
        this.context = context;
        this.restaurantMetricsCalculator = restaurantMetricsCalculator;
    }


}
