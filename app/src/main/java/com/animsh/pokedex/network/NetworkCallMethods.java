package com.animsh.pokedex.network;

import android.content.Context;
import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.animsh.pokedex.adapter.PokemonListAdapter;
import com.animsh.pokedex.model.PokemonCollection;
import com.animsh.pokedex.model.PokemonDetails;
import com.animsh.pokedex.utils.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class NetworkCallMethods {
    private static final String TAG = "NetworkCallMethods";
    static PokemonCollection pokemonCollection = new PokemonCollection();
    static PokemonDetails pokemonDetails = new PokemonDetails();
    static List<PokemonDetails> pokemonDetailsList = new ArrayList<>();

    public static PokemonCollection getPokemonCollection(RecyclerView pokemonRecyclerview, Context context) {
        Retrofit retrofit = RetrofitClient.getClient();
        PokeApiCalls pokeApiCalls = retrofit.create(PokeApiCalls.class);
        Call<PokemonCollection> call = pokeApiCalls.getAllPokemonCollection(20, 20);

        call.enqueue(new Callback<PokemonCollection>() {
            @Override
            public void onResponse(Call<PokemonCollection> call, Response<PokemonCollection> response) {

                if (response.code() != 200) {
                    Log.d(TAG, "onResponse: Check Your Network Connection");
                    return;
                }
                pokemonCollection = response.body();
                PokemonListAdapter pokemonListAdapter = new PokemonListAdapter(context);
                pokemonRecyclerview.setHasFixedSize(true);
                pokemonRecyclerview.setLayoutManager(new LinearLayoutManager(context));
                pokemonRecyclerview.setAdapter(pokemonListAdapter);
                pokemonRecyclerview.setItemViewCacheSize(2000);
                Log.d(TAG, "onResponse: " + pokemonCollection);
            }

            @Override
            public void onFailure(Call<PokemonCollection> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
        return pokemonCollection;
    }

    public static PokemonDetails getPokemonDetail(int id) {
        Retrofit retrofit = RetrofitClient.getClient();
        PokeApiCalls pokeApiCalls = retrofit.create(PokeApiCalls.class);
        Call<PokemonDetails> call = pokeApiCalls.getPokemonDetails(id);
        call.enqueue(new Callback<PokemonDetails>() {
            @Override
            public void onResponse(Call<PokemonDetails> call, Response<PokemonDetails> response) {
                Log.d(TAG, "onResponse: " + response.body());
                pokemonDetails = response.body();
            }

            @Override
            public void onFailure(Call<PokemonDetails> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
        return pokemonDetails;
    }
}
