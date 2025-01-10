package br.com.edu.jet.foodfusion.ui.fragment.adapter.transformer;

import android.view.View;
import androidx.viewpager2.widget.ViewPager2;

public class TinderPageTransformer implements ViewPager2.PageTransformer {
    private static final float SCALE_FACTOR = 0.9f;
    private static final float TRANSLATION_Y_FACTOR = 30f;

    @Override
    public void transformPage(View page, float position) {
        if (position < 0) {
            page.setScaleX(1f);
            page.setScaleY(1f);
            page.setTranslationY(0f);
            page.setAlpha(1f);
        } else if (position <= 1) {
            float scale = SCALE_FACTOR + (1 - SCALE_FACTOR) * (1 - position);
            page.setScaleX(scale);
            page.setScaleY(scale);

            float translationY = TRANSLATION_Y_FACTOR * position;
            page.setTranslationY(translationY);

            page.setAlpha(1 - position);
        } else {
            page.setAlpha(0f);
        }
    }
}

