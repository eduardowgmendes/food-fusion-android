package br.com.edu.jet.foodfusion.ui.activity;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.appbar.MaterialToolbar;

import br.com.edu.jet.foodfusion.R;
import br.com.edu.jet.foodfusion.utils.ResourceUtils;

public class CreateRestaurantActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create_restaurant);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ResourceUtils.init(this);

        MaterialToolbar editToolbar = findViewById(R.id.create_new_toolbar);
        setSupportActionBar(editToolbar);
        getSupportActionBar()
                .setTitle(ResourceUtils.getString(R.string.new_restaurant));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}