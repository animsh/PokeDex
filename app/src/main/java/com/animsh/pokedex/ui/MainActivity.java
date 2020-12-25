package com.animsh.pokedex.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.animsh.pokedex.R;
import com.animsh.pokedex.adapter.PokemonListAdapter;
import com.animsh.pokedex.listener.PaginationScrollListener;
import com.animsh.pokedex.model.Pokemon;
import com.animsh.pokedex.model.PokemonCollection;
import com.animsh.pokedex.network.PokeApiCalls;
import com.animsh.pokedex.utils.RetrofitClient;

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
    private ArrayList<Pokemon> finalArrayList;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    private int currentPage = PAGE_START;
    private int offset = 0;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        pokemonRecyclerview = findViewById(R.id.pokemon_recyclerview);
        progressBar = findViewById(R.id.main_progress);


        pokemonListAdapter = new PokemonListAdapter(this);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        pokemonRecyclerview.setHasFixedSize(true);
        pokemonRecyclerview.setLayoutManager(linearLayoutManager);
        pokemonRecyclerview.setAdapter(pokemonListAdapter);
        pokemonRecyclerview.setItemViewCacheSize(2000);
        pokemonRecyclerview.setItemAnimator(new DefaultItemAnimator());

        pokemonRecyclerview.addOnScrollListener(new PaginationScrollListener(linearLayoutManager) {
            @Override
            protected void loadMoreItems() {

                isLoading = true;
                currentPage += 1;
                loadNextPage();
            }

            @Override
            public int getTotalPageCount() {
                return TOTAL_PAGES;
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });

        loadFirstPage();
    }

    private Call<PokemonCollection> callPokemonService() {
        Retrofit retrofit = RetrofitClient.getClient();
        PokeApiCalls pokeApiCalls = retrofit.create(PokeApiCalls.class);
        return pokeApiCalls.getAllPokemonCollection(26, offset);
    }

    private void loadFirstPage() {
        offset = 0;
        currentPage = PAGE_START;

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

                if (currentPage <= TOTAL_PAGES) pokemonListAdapter.addLoadingFooter();
                else isLastPage = true;
                Log.d(TAG, "onResponse: " + pokemonCollection);
            }

            @Override
            public void onFailure(Call<PokemonCollection> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    private void loadNextPage() {
        offset += 26;
        Log.d(TAG, "loadNextPage: " + currentPage);

        callPokemonService().enqueue(new Callback<PokemonCollection>() {
            @Override
            public void onResponse(Call<PokemonCollection> call, Response<PokemonCollection> response) {
                if (response.isSuccessful()) {
                    pokemonListAdapter.removeLoadingFooter();
                    isLoading = false;

                    PokemonCollection pokemonCollection = response.body();
                    List<Pokemon> results = pokemonCollection.getPokemonList();
                    pokemonListAdapter.addAll(results);

                    if (currentPage != TOTAL_PAGES) pokemonListAdapter.addLoadingFooter();
                    else isLastPage = true;
                } else {
                    Log.d(TAG, "onResponseFail" + response.errorBody());
                    pokemonListAdapter.removeLoadingFooter();
                }
            }

            @Override
            public void onFailure(Call<PokemonCollection> call, Throwable t) {

            }
        });
    }


}