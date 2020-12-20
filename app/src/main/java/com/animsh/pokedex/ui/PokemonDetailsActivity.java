package com.animsh.pokedex.ui;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.animsh.pokedex.R;

public class PokemonDetailsActivity extends AppCompatActivity {

    LinearLayout pokemonDetailLayout;
    ConstraintLayout pokemonContainer;
    TextView pokemonName, pokemonNumber, type1, type2, pokemonType;
    ImageView pokemonImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_pokemon_details);
        int backgroundColor = getIntent().getIntExtra("bColor", 0);
        int titleColor = getIntent().getIntExtra("tColor", 0);
        int bodyTextColor = getIntent().getIntExtra("btColor", 0);

        pokemonName = findViewById(R.id.pokemon_name);
        pokemonNumber = findViewById(R.id.pokemon_number);
        pokemonImage = findViewById(R.id.pokemon_image);
        pokemonType = findViewById(R.id.pokemon_type);
        type1 = findViewById(R.id.type_1);
        type2 = findViewById(R.id.type_2);

        pokemonDetailLayout = findViewById(R.id.pokemon_details_container);
        pokemonContainer = findViewById(R.id.pokemon_container);

        pokemonDetailLayout.setBackgroundColor(backgroundColor);
        pokemonContainer.setBackgroundColor(backgroundColor);
        pokemonName.setTextColor(bodyTextColor);
        pokemonNumber.setTextColor(titleColor);
        pokemonType.setTextColor(titleColor);
        GradientDrawable drawable1 = (GradientDrawable) type1.getBackground();
        GradientDrawable drawable2 = (GradientDrawable) type2.getBackground();
        drawable1.setStroke(2, titleColor); // set stroke width and stroke color
        drawable2.setStroke(2, titleColor); // set stroke width and stroke color
        drawable1.setCornerRadius(20f);
        drawable2.setCornerRadius(20f);
        type1.setTextColor(titleColor);
        type2.setTextColor(titleColor);
    }
}