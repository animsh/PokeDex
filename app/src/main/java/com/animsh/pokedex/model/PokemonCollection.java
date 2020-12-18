package com.animsh.pokedex.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PokemonCollection {
    private int count;
    private String next;
    private String previous;

    @SerializedName("results")
    private List<Pokemon> pokemonList;

    public PokemonCollection() {
    }

    public PokemonCollection(int count, String next, String previous, List<Pokemon> pokemonList) {
        this.count = count;
        this.next = next;
        this.previous = previous;
        this.pokemonList = pokemonList;
    }

    public int getCount() {
        return count;
    }

    public String getNext() {
        return next;
    }

    public String getPrevious() {
        return previous;
    }

    public List<Pokemon> getPokemonList() {
        return pokemonList;
    }

    @Override
    public String toString() {
        return "PokemonCollection{" +
                "count=" + count +
                ", next='" + next + '\'' +
                ", previous='" + previous +
                '}';
    }
}
