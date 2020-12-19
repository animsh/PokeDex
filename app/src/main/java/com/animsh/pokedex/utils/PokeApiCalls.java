package com.animsh.pokedex.utils;

import com.animsh.pokedex.model.PokemonCollection;
import com.animsh.pokedex.model.PokemonDetails;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PokeApiCalls {
    @GET("pokemon")
    Call<PokemonCollection> getAllPokemonCollection();

    @GET("pokemon/{id}")
    Call<PokemonDetails> getPokemonDetails(@Path("id") int id);
}
