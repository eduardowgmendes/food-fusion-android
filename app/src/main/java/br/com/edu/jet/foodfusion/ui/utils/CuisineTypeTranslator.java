package br.com.edu.jet.foodfusion.ui.utils;

import android.content.Context;

import androidx.annotation.NonNull;

import br.com.edu.jet.foodfusion.R;
import br.com.edu.jet.foodfusion.ui.model.restaurant.enums.CuisineType;

public class CuisineTypeTranslator {

    public static String translateType(CuisineType cuisineType, Context context) {
        switch (cuisineType) {
            case ITALIAN:
                return getString(context, R.string.cuisine_italian);
            case JAPANESE:
                return getString(context, R.string.cuisine_japanese);
            case THAI:
                return getString(context, R.string.cuisine_thai);
            case CHINESE:
                return getString(context, R.string.cuisine_chinese);
            case MEXICAN:
                return getString(context, R.string.cuisine_mexican);
            case INDIAN:
                return getString(context, R.string.cuisine_indian);
            case AMERICAN:
                return getString(context, R.string.cuisine_american);
            case FRENCH:
                return getString(context, R.string.cuisine_french);
            case BRAZILIAN:
                return getString(context, R.string.cuisine_brazilian);
            case MEDITERRANEAN:
                return getString(context, R.string.cuisine_mediterranean);
            case SPANISH:
                return getString(context, R.string.cuisine_spanish);
            case GREEK:
                return getString(context, R.string.cuisine_greek);
            case KOREAN:
                return getString(context, R.string.cuisine_korean);
            case VIETNAMESE:
                return getString(context, R.string.cuisine_vietnamese);
            case TURKISH:
                return getString(context, R.string.cuisine_turkish);
            case ARABIC:
                return getString(context, R.string.cuisine_arabic);
            default:
                return cuisineType.getDescription();
        }
    }

    private static @NonNull String getString(Context context, int stringId) {
        return context
                .getResources()
                .getString(stringId);
    }

}
