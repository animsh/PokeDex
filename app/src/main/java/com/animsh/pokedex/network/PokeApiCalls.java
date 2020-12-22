package com.animsh.pokedex.network;

import com.animsh.pokedex.model.PokemonCollection;
import com.animsh.pokedex.model.PokemonDetails;
import com.animsh.pokedex.model.PokemonSpecies;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PokeApiCalls {
    @GET("pokemon")
    Call<PokemonCollection> getAllPokemonCollection();

    @GET("pokemon/{id}")
    Call<PokemonDetails> getPokemonDetails(@Path("id") int id);

    @GET("pokemon-species/{id}")
    Call<PokemonSpecies> getPokemonSpecie(@Path("id") int id);
}
