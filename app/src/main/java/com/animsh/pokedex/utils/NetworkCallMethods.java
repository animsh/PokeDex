package com.animsh.pokedex.utils;

import android.content.Context;
import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.animsh.pokedex.adapter.PokemonListAdapter;
import com.animsh.pokedex.model.PokemonCollection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class NetworkCallMethods {
    private static final String TAG = "NetworkCallMethods";
    static PokemonCollection pokemonCollection = new PokemonCollection();

    public static PokemonCollection getPokemonCollection(RecyclerView pokemonRecyclerview, Context context) {
        Retrofit retrofit = RetrofitClient.getClient();
        PokeApiCalls pokeApiCalls = retrofit.create(PokeApiCalls.class);
        Call<PokemonCollection> call = pokeApiCalls.getAllPokemonCollection();

        call.enqueue(new Callback<PokemonCollection>() {
            @Override
            public void onResponse(Call<PokemonCollection> call, Response<PokemonCollection> response) {

                if (response.code() != 200) {
                    Log.d(TAG, "onResponse: Check Your Network Connection");
                    return;
                }
                pokemonCollection = response.body();
                Log.d(TAG, "onResponse: " + pokemonCollection);
                for (int i = 0; i < pokemonCollection.getPokemonList().size(); i++) {
                    Log.d(TAG, "onResponse: " + pokemonCollection.getPokemonList().get(i).getName());
                }
                PokemonListAdapter pokemonListAdapter = new PokemonListAdapter(pokemonCollection.getPokemonList(), context);
                pokemonRecyclerview.setLayoutManager(new LinearLayoutManager(context));
                pokemonRecyclerview.setAdapter(pokemonListAdapter);
                pokemonListAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<PokemonCollection> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
        return pokemonCollection;
    }
}
