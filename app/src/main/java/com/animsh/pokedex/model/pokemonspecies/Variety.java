package com.animsh.pokedex.model.pokemonspecies;

import com.animsh.pokedex.model.Pokemon;

/**
 * Created by animsh on 1/17/2021.
 */
public class Variety {
    private boolean is_default;
    private Pokemon pokemon;

    public Variety() {
    }

    public Variety(boolean is_default, Pokemon pokemon) {
        this.is_default = is_default;
        this.pokemon = pokemon;
    }

    public boolean isIs_default() {
        return is_default;
    }

    public Pokemon getPokemon() {
        return pokemon;
    }
}
