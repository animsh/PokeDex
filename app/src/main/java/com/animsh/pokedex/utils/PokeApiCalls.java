package com.animsh.pokedex.utils;

import com.animsh.pokedex.model.PokemonCollection;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PokeApiCalls {
    @GET("pokemon")
    Call<PokemonCollection> getAllPokemonCollection();
}
