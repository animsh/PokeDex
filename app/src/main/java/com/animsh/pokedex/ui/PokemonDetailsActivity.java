package com.animsh.pokedex.ui;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.animsh.pokedex.R;
import com.animsh.pokedex.adapter.AbilitiesAdapter;
import com.animsh.pokedex.model.pokemondetails.Abilities;
import com.animsh.pokedex.model.pokemondetails.Ability;
import com.skydoves.progressview.ProgressView;

import java.util.ArrayList;
import java.util.List;

public class PokemonDetailsActivity extends AppCompatActivity {

    LinearLayout pokemonDetailLayout;
    ConstraintLayout pokemonContainer;
    TextView pokemonName, pokemonNumber, type1, type2, pokemonType;
    ImageView pokemonImage;

    TextView pokemonDetail, pokemonHeight, pokemonWeight, txtSpecies, txtAbilities, txtBaseStats;
    RecyclerView pokemonAbilityRecyclerView;

    ProgressView progressViewHP, progressViewAttack, progressViewDefence, progressViewSPAttack, progressViewSPDefence, progressViewSpeed;
    TextView labelHP, labelAttack, labelDefence, labelSPAttack, labelSPDefence, labelSpeed;
    View supporterViewHP, supporterViewAttack, supporterViewDefence, supporterViewSPAttack, supporterViewSPDefence, supporterViewSpeed;

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
        pokemonDetail = findViewById(R.id.pokemon_detail);
        pokemonHeight = findViewById(R.id.pokemon_height);
        pokemonWeight = findViewById(R.id.pokemon_weight);
        type1 = findViewById(R.id.type_1);
        type2 = findViewById(R.id.type_2);
        txtSpecies = findViewById(R.id.txt_species);
        txtAbilities = findViewById(R.id.txt_abilities);
        txtBaseStats = findViewById(R.id.txt_base_stat);

        pokemonDetailLayout = findViewById(R.id.pokemon_details_container);
        pokemonContainer = findViewById(R.id.pokemon_container);
        pokemonAbilityRecyclerView = findViewById(R.id.pokemon_ability_recyclerview);

        progressViewHP = findViewById(R.id.progressView1);
        progressViewAttack = findViewById(R.id.progressViewAttack);
        progressViewDefence = findViewById(R.id.progressViewDefence);
        progressViewSPAttack = findViewById(R.id.progressViewSPAttack);
        progressViewSPDefence = findViewById(R.id.progressViewSPDefence);
        progressViewSpeed = findViewById(R.id.progressViewSpeed);

        labelHP = findViewById(R.id.txt_hp);
        labelAttack = findViewById(R.id.txt_attack);
        labelDefence = findViewById(R.id.txt_defence);
        labelSPAttack = findViewById(R.id.txt_sp_attack);
        labelSPDefence = findViewById(R.id.txt_sp_defence);
        labelSpeed = findViewById(R.id.txt_speed);

        supporterViewHP = findViewById(R.id.supporter_view_1);
        supporterViewAttack = findViewById(R.id.supporter_view_attack);
        supporterViewDefence = findViewById(R.id.supporter_view_defence);
        supporterViewSPAttack = findViewById(R.id.supporter_view_sp_attack);
        supporterViewSPDefence = findViewById(R.id.supporter_view_sp_defence);
        supporterViewSpeed = findViewById(R.id.supporter_view_speed);


        pokemonDetailLayout.setBackgroundColor(backgroundColor);
        pokemonContainer.setBackgroundColor(backgroundColor);

        pokemonName.setTextColor(bodyTextColor);
        pokemonNumber.setTextColor(titleColor);
        pokemonType.setTextColor(titleColor);
        setStrokeWithCorner(type1, titleColor, true, "");
        setStrokeWithCorner(type2, titleColor, true, "");
        setStrokeWithCorner(pokemonDetail, 0, false, "#1F000000");
        setStrokeWithCorner(pokemonHeight, 0, false, "#1F000000");
        setStrokeWithCorner(pokemonWeight, 0, false, "#1F000000");

        type1.setTextColor(titleColor);
        type2.setTextColor(titleColor);
        txtSpecies.setTextColor(titleColor);
        txtAbilities.setTextColor(titleColor);
        txtBaseStats.setTextColor(titleColor);

        List<Abilities> abilities = new ArrayList<>();
        Ability ability1 = new Ability("OverGrow", "");
        Ability ability2 = new Ability("Chlorophyll", "");
        abilities.add(new Abilities(ability1, false, 1));
        abilities.add(new Abilities(ability2, true, 2));
        pokemonAbilityRecyclerView.setAdapter(new AbilitiesAdapter(abilities, this, backgroundColor, titleColor, bodyTextColor));
        pokemonAbilityRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        pokemonAbilityRecyclerView.setHasFixedSize(true);

        setUpProgressView(progressViewHP, supporterViewHP, labelHP, backgroundColor, titleColor, bodyTextColor);
        setUpProgressView(progressViewAttack, supporterViewAttack, labelAttack, backgroundColor, titleColor, bodyTextColor);
        setUpProgressView(progressViewDefence, supporterViewDefence, labelDefence, backgroundColor, titleColor, bodyTextColor);
        setUpProgressView(progressViewSPAttack, supporterViewSPAttack, labelSPAttack, backgroundColor, titleColor, bodyTextColor);
        setUpProgressView(progressViewSPDefence, supporterViewSPDefence, labelSPDefence, backgroundColor, titleColor, bodyTextColor);
        setUpProgressView(progressViewSpeed, supporterViewSpeed, labelSpeed, backgroundColor, titleColor, bodyTextColor);

        setProgressViewProgress(progressViewHP, 45f, 65f);
        setProgressViewProgress(progressViewAttack, 49f, 65f);
        setProgressViewProgress(progressViewDefence, 49f, 65f);
        setProgressViewProgress(progressViewSPAttack, 65f, 65f);
        setProgressViewProgress(progressViewSPDefence, 65f, 65f);
        setProgressViewProgress(progressViewSpeed, 45f, 65f);


    }

    public void setProgressViewProgress(ProgressView progressView, float progress, float maxProgress) {
        progressView.setMax(maxProgress);
        progressView.setProgress(progress);
        int intProgress = (int) progress;
        progressView.setLabelText(String.valueOf(intProgress));
    }

    public void setUpProgressView(ProgressView progressView, View supporterView, TextView label, int backgroundColor, int titleColor, int bodyTextColor) {
        progressView.getHighlightView().setColor(backgroundColor);
        progressView.setLabelColorOuter(backgroundColor);
        progressView.setLabelColorInner(bodyTextColor);
        progressView.setLabelText(String.valueOf(progressView.getProgress()));
        GradientDrawable shape = new GradientDrawable();
        shape.setShape(GradientDrawable.RECTANGLE);
        shape.setColor(backgroundColor);
        shape.setCornerRadii(new float[]{20, 20, 0, 0, 0, 0, 20, 20});
        supporterView.setBackgroundColor(backgroundColor);
        label.setBackgroundDrawable(shape);
        label.setTextColor(titleColor);
    }

    public void setStrokeWithCorner(View view, int intColor, boolean isIntColor, String stringColor) {
        GradientDrawable drawable = (GradientDrawable) view.getBackground();
        if (isIntColor) {
            drawable.setStroke(2, intColor);
        } else {
            drawable.setStroke(2, Color.parseColor(stringColor));
        }
        drawable.setCornerRadius(20f);
    }
}