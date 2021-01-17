package com.animsh.pokedex.model;

import com.animsh.pokedex.model.pokemondetails.NamedAPIResource;

/**
 * Created by animsh on 1/17/2021.
 */
public class Variety {
    private boolean is_default;
    private NamedAPIResource pokemon;

    public Variety() {
    }

    public Variety(boolean is_default, NamedAPIResource pokemon) {
        this.is_default = is_default;
        this.pokemon = pokemon;
    }

    public boolean isIs_default() {
        return is_default;
    }

    public NamedAPIResource getPokemon() {
        return pokemon;
    }
}
