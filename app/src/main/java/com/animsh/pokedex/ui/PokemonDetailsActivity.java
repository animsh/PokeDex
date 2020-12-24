package com.animsh.pokedex.ui;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.animsh.pokedex.R;
import com.animsh.pokedex.adapter.AbilitiesAdapter;
import com.animsh.pokedex.model.PokemonDetails;
import com.animsh.pokedex.model.PokemonSpecies;
import com.animsh.pokedex.model.pokemondetails.Abilities;
import com.animsh.pokedex.model.pokemondetails.Stats;
import com.animsh.pokedex.model.pokemonspecies.FlavorTextEntries;
import com.animsh.pokedex.model.pokemonspecies.Genera;
import com.animsh.pokedex.network.PokeApiCalls;
import com.animsh.pokedex.utils.RetrofitClient;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.skydoves.progressview.ProgressView;

import java.text.DecimalFormat;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PokemonDetailsActivity extends AppCompatActivity {

    private static final String TAG = "PokemonDetails";
    LinearLayout pokemonDetailLayout;
    ConstraintLayout pokemonContainer;
    TextView pokemonName, pokemonNumber, type1, type2, pokemonType;
    ImageView pokemonImage;

    TextView pokemonDetail, pokemonHeight, pokemonWeight, txtSpecies, txtAbilities, txtBaseStats;
    RecyclerView pokemonAbilityRecyclerView;

    ProgressView progressViewHP, progressViewAttack, progressViewDefence, progressViewSPAttack, progressViewSPDefence, progressViewSpeed;
    TextView labelHP, labelAttack, labelDefence, labelSPAttack, labelSPDefence, labelSpeed;
    View supporterViewHP, supporterViewAttack, supporterViewDefence, supporterViewSPAttack, supporterViewSPDefence, supporterViewSpeed;

    PokemonDetails pokemonDetails;

    ProgressBar progressBarSpecies, progressBarAbilities, progressBarBaseStat;
    ConstraintLayout layoutSpecies;
    LinearLayout layoutStat;

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
        int intentId = getIntent().getIntExtra("id", 0);

        initializeViews();

        pokemonDetails = new PokemonDetails();

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

        progressBarBaseStat.getIndeterminateDrawable().setColorFilter(backgroundColor, PorterDuff.Mode.SRC_IN);
        progressBarAbilities.getIndeterminateDrawable().setColorFilter(backgroundColor, PorterDuff.Mode.SRC_IN);
        progressBarSpecies.getIndeterminateDrawable().setColorFilter(backgroundColor, PorterDuff.Mode.SRC_IN);


        setUpProgressView(progressViewHP, supporterViewHP, labelHP, backgroundColor, titleColor, bodyTextColor);
        setUpProgressView(progressViewAttack, supporterViewAttack, labelAttack, backgroundColor, titleColor, bodyTextColor);
        setUpProgressView(progressViewDefence, supporterViewDefence, labelDefence, backgroundColor, titleColor, bodyTextColor);
        setUpProgressView(progressViewSPAttack, supporterViewSPAttack, labelSPAttack, backgroundColor, titleColor, bodyTextColor);
        setUpProgressView(progressViewSPDefence, supporterViewSPDefence, labelSPDefence, backgroundColor, titleColor, bodyTextColor);
        setUpProgressView(progressViewSpeed, supporterViewSpeed, labelSpeed, backgroundColor, titleColor, bodyTextColor);


        Retrofit retrofit = RetrofitClient.getClient();
        PokeApiCalls pokeApiCalls = retrofit.create(PokeApiCalls.class);

        Call<PokemonDetails> call = pokeApiCalls.getPokemonDetails(intentId);
        call.enqueue(new Callback<PokemonDetails>() {
            @Override
            public void onResponse(Call<PokemonDetails> call, Response<PokemonDetails> response) {
                Log.d(TAG, "onResponse: " + response.body());
                pokemonDetails = response.body();
                pokemonName.setText(getProperName(pokemonDetails.getName()));

                double weightKg = (double) pokemonDetails.getWeight() / 10;
                String txtWeightKg = new DecimalFormat("####.#").format(weightKg) + " kg";
                double weightLBS = ((double) pokemonDetails.getWeight() / 4.536);
                String txtWeightLBS = new DecimalFormat("####.##").format(weightLBS) + " lbs";
                String weight = txtWeightLBS + " (" + txtWeightKg + ")";
                pokemonWeight.setText(weight);

                double heightMeter = (double) pokemonDetails.getHeight() / 10;
                String txtHeightMeter = new DecimalFormat("####.##").format(heightMeter) + " m";
                double heightFeet = ((double) pokemonDetails.getHeight() / 3.048);
                String txtHeightFeet = new DecimalFormat("0.0").format(heightFeet);
                String[] listString = txtHeightFeet.split("\\.");
                String notationFeet = "";
                if (listString.length > 1) {
                    notationFeet = listString[0] + "'" + listString[1] + "\"";
                } else if (listString.length == 1) {
                    notationFeet = listString[0] + "'";
                }
                String height = notationFeet + " (" + txtHeightMeter + ")";
                pokemonHeight.setText(height);

                List<Abilities> abilitiesList = pokemonDetails.getAbilities();
                Log.d(TAG, "onResponse: " + abilitiesList.get(0).getAbility().getName());

                if (abilitiesList != null) {
                    pokemonAbilityRecyclerView.setAdapter(new AbilitiesAdapter(abilitiesList, PokemonDetailsActivity.this, backgroundColor, titleColor, bodyTextColor));
                    pokemonAbilityRecyclerView.setLayoutManager(new LinearLayoutManager(PokemonDetailsActivity.this));
                    pokemonAbilityRecyclerView.setHasFixedSize(true);

                }

                List<Stats> statsList = pokemonDetails.getStats();
                int maxProgress = 0;
                for (int i = 0; i < statsList.size(); i++) {
                    if (statsList.get(i).getBase_stat() > maxProgress) {
                        maxProgress = statsList.get(i).getBase_stat();
                    }
                }
                setProgressViewProgress(progressViewHP, statsList.get(0).getBase_stat(), maxProgress);
                setProgressViewProgress(progressViewAttack, statsList.get(1).getBase_stat(), maxProgress);
                setProgressViewProgress(progressViewDefence, statsList.get(2).getBase_stat(), maxProgress);
                setProgressViewProgress(progressViewSPAttack, statsList.get(3).getBase_stat(), maxProgress);
                setProgressViewProgress(progressViewSPDefence, statsList.get(4).getBase_stat(), maxProgress);
                setProgressViewProgress(progressViewSpeed, statsList.get(5).getBase_stat(), maxProgress);

                int id = pokemonDetails.getId();
                String newId = "";
                String baseId = "";
                if (String.valueOf(id).length() == 1) {
                    newId = "#00" + id;
                    baseId = "00" + id;
                } else if (String.valueOf(id).length() == 2) {
                    newId = "#0" + id;
                    baseId = "0" + id;
                } else if (String.valueOf(id).length() == 3) {
                    newId = "#" + id;
                    baseId = String.valueOf(id);
                } else {
                    newId = "#" + id;
                    baseId = String.valueOf(id);
                }
                pokemonNumber.setText(newId);

                String url;
                if (id < 893 | id >= 10001) {
                    url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/" + id + ".png";
                } else {
                    url = "https://assets.pokemon.com/assets/cms2/img/pokedex/detail/" + baseId + ".png";
                }
                if (pokemonDetails.getTypes().size() > 1) {
                    type1.setText(pokemonDetails.getTypes().get(0).getType().getName().toUpperCase());
                    type2.setVisibility(View.VISIBLE);
                    type2.setText(pokemonDetails.getTypes().get(1).getType().getName().toUpperCase());
                } else if (pokemonDetails.getTypes().size() == 1) {
                    type1.setText(pokemonDetails.getTypes().get(0).getType().getName().toUpperCase());
                }

                Glide.with(getApplicationContext()).asBitmap()
                        .load(url)
                        .listener(new RequestListener<Bitmap>() {
                            @Override
                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                                return false;
                            }
                        })
                        .placeholder(R.drawable.ic_pokeball)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(pokemonImage);

                Handler handler = new Handler();

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progressBarAbilities.setVisibility(View.GONE);
                        pokemonAbilityRecyclerView.setVisibility(View.VISIBLE);
                        progressBarSpecies.setVisibility(View.GONE);
                        layoutSpecies.setVisibility(View.VISIBLE);
                    }
                }, 500);

                String[] speciesUrl = pokemonDetails.getSpecies().getUrl().split("\\/");
                int speciesId = Integer.parseInt(speciesUrl[speciesUrl.length - 1]);
                Call<PokemonSpecies> pokemonSpeciesCall = pokeApiCalls.getPokemonSpecie(speciesId);
                pokemonSpeciesCall.enqueue(new Callback<PokemonSpecies>() {
                    @Override
                    public void onResponse(Call<PokemonSpecies> call, Response<PokemonSpecies> response) {
                        PokemonSpecies pokemonSpecies = response.body();
                        List<Genera> genera = pokemonSpecies.getGenera();
                        for (int i = 0; i < genera.size(); i++) {
                            if (genera.get(i).getLanguage().getName().equals("en")) {
                                pokemonType.setText(genera.get(i).getGenus());
                            }
                        }

                        List<FlavorTextEntries> flavorTextEntriesList = pokemonSpecies.getFlavor_text_entries();
                        for (int i = 0; i < flavorTextEntriesList.size(); i++) {
                            if (flavorTextEntriesList.get(i).getLanguage().getName().equals("en")) {
                                pokemonDetail.setText(flavorTextEntriesList.get(i).getFlavor_text().replace("\n", ""));
                                break;
                            }
                        }

                    }

                    @Override
                    public void onFailure(Call<PokemonSpecies> call, Throwable t) {

                    }
                });

                progressBarBaseStat.setVisibility(View.GONE);
                layoutStat.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(Call<PokemonDetails> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });


    }

    public String getProperName(String oldName) {
        // stores each characters to a char array
        char[] charArray = oldName.toCharArray();
        boolean foundSpace = true;

        for (int i = 0; i < charArray.length; i++) {

            // if the array element is a letter
            if (Character.isLetter(charArray[i])) {

                // check space is present before the letter
                if (foundSpace) {

                    // change the letter into uppercase
                    charArray[i] = Character.toUpperCase(charArray[i]);
                    foundSpace = false;
                }
            } else {
                // if the new character is not character
                foundSpace = true;
            }
        }

        // convert the char array to the string
        return String.valueOf(charArray);
    }

    private void initializeViews() {
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

        progressBarSpecies = findViewById(R.id.progressBarSContainer);
        progressBarAbilities = findViewById(R.id.progressBarAContainer);
        progressBarBaseStat = findViewById(R.id.progressBarBSContainer);

        layoutSpecies = findViewById(R.id.layout_species);
        layoutStat = findViewById(R.id.layout_stats);

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

        GradientDrawable shape2 = new GradientDrawable();
        shape2.setShape(GradientDrawable.RECTANGLE);
        shape2.setColor(backgroundColor);
        shape2.setCornerRadii(new float[]{0, 0, 20, 20, 20, 20, 0, 0});
        supporterView.setBackgroundDrawable(shape2);
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