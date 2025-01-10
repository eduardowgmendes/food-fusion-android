package br.com.edu.jet.foodfusion.ui.fragment.adapter.transformer;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.viewpager2.widget.ViewPager2;

public class AccordionPageTransformer implements ViewPager2.PageTransformer {

    @Override
    public void transformPage(@NonNull View page, float position) {
        page.setPivotX(position < 0.0F ? 0.0F : (float) page.getWidth());
        page.setScaleX(position < 0.0F ? 1.0F + position : 1.0F - position);
    }
}
