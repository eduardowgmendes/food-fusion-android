package br.com.edu.jet.foodfusion.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import br.com.edu.jet.foodfusion.R;
import br.com.edu.jet.foodfusion.ui.model.restaurant.Restaurant;
import br.com.edu.jet.foodfusion.ui.model.restaurant.enums.CuisineType;
import br.com.edu.jet.foodfusion.ui.utils.CuisineTypeTranslator;
import br.com.edu.jet.foodfusion.utils.ResourceUtils;
import br.com.edu.jet.foodfusion.viewmodel.RestaurantViewModel;

public class CreateRestaurantActivity extends AppCompatActivity {

    private MaterialButton saveButton;
    private TextInputLayout nameEditText, descriptionEditText;
    private AutoCompleteTextView typeSelector;
    private RestaurantViewModel restaurantViewModel;
    private CuisineType selectedCuisineType;

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

        long restaurantId = getIntent().getLongExtra("restaurantId", 0);

        ResourceUtils.init(this);

        restaurantViewModel = new ViewModelProvider(this).get(RestaurantViewModel.class);

        MaterialToolbar editToolbar = findViewById(R.id.create_new_toolbar);
        setSupportActionBar(editToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nameEditText = findViewById(R.id.restaurant_name_text_input);
        descriptionEditText = findViewById(R.id.restaurant_description_text_input);
        typeSelector = findViewById(R.id.restaurant_type_autocomplete_text_view);

        ArrayAdapter<CuisineType> cuisineTypeArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, CuisineType.values());

        typeSelector.setAdapter(cuisineTypeArrayAdapter);
        typeSelector.setOnItemClickListener(((parent, view, position, id) -> {
            selectedCuisineType = ((CuisineType) parent.getItemAtPosition(position));
        }));

        saveButton = findViewById(R.id.save_button);
        saveButton.setOnClickListener(view -> {
            saveRestaurant(nameEditText.getEditText(), descriptionEditText.getEditText(), selectedCuisineType);
        });

        if (restaurantId != 0) {
            getSupportActionBar()
                    .setTitle(ResourceUtils.getString(R.string.edit_restaurant));
            restaurantViewModel.getById(restaurantId).observe(this, new Observer<Restaurant>() {
                @Override
                public void onChanged(Restaurant restaurant) {
                    nameEditText.getEditText().setText(restaurant.getName());
                    descriptionEditText.getEditText().setText(restaurant.getDescription());
                    typeSelector.setText(restaurant.getType().getDescription(), false);
                }
            });
        } else {
            getSupportActionBar()
                    .setTitle(ResourceUtils.getString(R.string.new_restaurant));
        }

    }

    private void saveRestaurant(EditText nameEditText, EditText descriptionEditText, CuisineType cuisineType) {
        boolean isValid = true;

        String restaurantName = null;
        if (nameEditText.getText() != null) {
            Editable restaurantNameEditText = nameEditText.getText();
            restaurantName = restaurantNameEditText.toString().trim();
        }

        if (restaurantName == null || restaurantName.isEmpty()) {
            nameEditText.setError(getString(R.string.name_is_required_message));
            isValid = false;
        } else {
            nameEditText.setError(null);
        }

        String restaurantDescription = null;
        if (descriptionEditText.getText() != null) {
            Editable restaurantDescriptionEditText = descriptionEditText.getText();
            restaurantDescription = restaurantDescriptionEditText.toString().trim();
        }

        if (cuisineType == null) {
            typeSelector.setError(getString(R.string.cuisine_type_is_required_message));
            isValid = false;
        } else {
            typeSelector.setError(null);
        }

        if (isValid) {
            LiveData<Restaurant> restaurantLiveData = restaurantViewModel.create(buildRestaurant(restaurantName, restaurantDescription, cuisineType));
            Restaurant restaurantCreated = restaurantLiveData.getValue();

            new AlertDialog.Builder(CreateRestaurantActivity.this)
                    .setTitle(R.string.restaurant_created_successfully_title)
                    .setMessage(R.string.restaurant_created_successfully_message)
                    .setPositiveButton(R.string.ok_button_hint, (dialog, which) -> {
                        Intent resultIntent = new Intent();
                        resultIntent.putExtra("created", true);
                        setResult(RESULT_OK, resultIntent);
                        finish();
                    }).show();
        }
    }

    private Restaurant buildRestaurant(String name, String description, CuisineType type) {
        Restaurant restaurant = new Restaurant();
        restaurant.setName(name);
        restaurant.setDescription(description);
        restaurant.setType(type);
        return restaurant;
    }

}
