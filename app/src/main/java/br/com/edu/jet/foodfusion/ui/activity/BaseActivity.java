package br.com.edu.jet.foodfusion.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import br.com.edu.jet.foodfusion.R;

public class BaseActivity extends AppCompatActivity {

    public void setLayoutNoLimits() {
        getWindow()
                .setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    }

    public void openActivity(Context source, Class<? extends AppCompatActivity> destination, String extraName, Parcelable parcelable) {
        Intent intent = new Intent(source, destination);
        intent.putExtra(extraName, parcelable);
        startActivity(intent);
    }

    public void openActivity(Context source, Class<? extends AppCompatActivity> destination) {
        openActivity(source, destination, null, null);
    }

    public void replace(int rootLayout, Fragment fragment) {
        getSupportFragmentManager().popBackStack();
        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                .replace(rootLayout, fragment)
                .commit();
    }

    public void add(int rootLayout, Fragment fragment, String backStackName) {
        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out, R.anim.fade_in, R.anim.fade_out)
                .add(rootLayout, fragment)
                .addToBackStack(backStackName)
                .commit();
    }

    public Drawable getDrawableIcon(int drawableRes) {
        return ResourcesCompat.getDrawable(getResources(), drawableRes, getTheme());
    }

}

