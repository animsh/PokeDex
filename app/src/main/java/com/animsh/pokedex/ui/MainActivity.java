package com.animsh.pokedex.ui;

import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.animsh.pokedex.R;
import com.animsh.pokedex.adapter.CustomSuggestionsAdapter;
import com.animsh.pokedex.adapter.PokemonListAdapter;
import com.animsh.pokedex.model.Pokemon;
import com.animsh.pokedex.model.PokemonCollection;
import com.animsh.pokedex.network.PokeApiCalls;
import com.animsh.pokedex.utils.RetrofitClient;
import com.mancj.materialsearchbar.MaterialSearchBar;
import com.skydoves.transformationlayout.TransformationCompat;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    private static final int TOTAL_PAGES = 43;
    private static final int PAGE_START = 1;
    public String TAG = "MainActivity.class";
    public RecyclerView pokemonRecyclerview;
    PokemonListAdapter pokemonListAdapter;
    private final boolean isLoading = false;
    private boolean isLastPage = false;
    private final int currentPage = PAGE_START;
    private final int offset = 0;
    private ProgressBar progressBar;
    private MaterialSearchBar materialSearchBar;
    private final List<Pokemon> suggestions = new ArrayList<>();
    private CustomSuggestionsAdapter customSuggestionsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TransformationCompat.onTransformationStartContainer(this);
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        pokemonRecyclerview = findViewById(R.id.pokemon_recyclerview);
        progressBar = findViewById(R.id.main_progress);
        materialSearchBar = findViewById(R.id.searchBar);
        materialSearchBar.getPlaceHolderView().setTextAppearance(this, R.style.MyCustomFontTextAppearance);
        materialSearchBar.addTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.d("LOG_TAG", getClass().getSimpleName() + " text changed " + materialSearchBar.getText());
                // send the entered text to our filter and let it manage everything
                pokemonListAdapter.getFilter().filter(materialSearchBar.getText());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }

        });

        pokemonListAdapter = new PokemonListAdapter(this);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        pokemonRecyclerview.setHasFixedSize(true);
        pokemonRecyclerview.setLayoutManager(linearLayoutManager);
        pokemonRecyclerview.setAdapter(pokemonListAdapter);
        pokemonRecyclerview.setItemViewCacheSize(2000);
        pokemonRecyclerview.setItemAnimator(new DefaultItemAnimator());


        loadFirstPage();
    }

    private Call<PokemonCollection> callPokemonService() {
        Retrofit retrofit = RetrofitClient.getClient();
        PokeApiCalls pokeApiCalls = retrofit.create(PokeApiCalls.class);
        return pokeApiCalls.getAllPokemonCollection(1118, offset);
    }

    private void loadFirstPage() {

        callPokemonService().enqueue(new Callback<PokemonCollection>() {
            @Override
            public void onResponse(Call<PokemonCollection> call, Response<PokemonCollection> response) {

                if (response.code() != 200) {
                    Log.d(TAG, "onResponse: Check Your Network Connection");
                    return;
                }
                PokemonCollection pokemonCollection = response.body();
                progressBar.setVisibility(View.GONE);
                Log.d(TAG, "onResponse: " + pokemonCollection.getPokemonList().get(pokemonCollection.getPokemonList().size() - 1).getName());
                pokemonListAdapter.addAll(pokemonCollection.getPokemonList());

                isLastPage = true;
                Log.d(TAG, "onResponse: " + pokemonCollection);
            }

            @Override
            public void onFailure(Call<PokemonCollection> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

}