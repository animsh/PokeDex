package com.animsh.pokedex.utils;

import com.animsh.pokedex.model.PokemonCollection;
import com.animsh.pokedex.model.PokemonDetails;
import com.animsh.pokedex.model.PokemonSpecies;
import com.animsh.pokedex.model.pokemondetails.Evolution;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PokeApiCalls {
    @GET("pokemon")
    Call<PokemonCollection> getAllPokemonCollection(@Query("limit") int limit, @Query("offset") int offset);

    @GET("pokemon/{id}")
    Call<PokemonDetails> getPokemonDetails(@Path("id") int id);

    @GET("pokemon-species/{id}")
    Call<PokemonSpecies> getPokemonSpecie(@Path("id") int id);

    @GET("evolution-chain/{id}/")
    Call<Evolution> getPokemonEvolution(@Path("id") int id);

}
