package com.animsh.pokedex.model.pokemonspecies;

import com.animsh.pokedex.model.pokemondetails.NamedAPIResource;

public class Genera {
    private String genus;
    private NamedAPIResource language;

    public Genera(String genus, NamedAPIResource language) {
        this.genus = genus;
        this.language = language;
    }

    public String getGenus() {
        return genus;
    }

    public NamedAPIResource getLanguage() {
        return language;
    }
}
