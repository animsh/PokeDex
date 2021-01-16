package com.animsh.pokedex.model.pokemonspecies;

import com.animsh.pokedex.model.pokemondetails.NamedAPIResource;

public class FlavorTextEntries {
    private String flavor_text;
    private NamedAPIResource language;
    private NamedAPIResource version;

    public FlavorTextEntries(String flavor_text, NamedAPIResource language, NamedAPIResource version) {
        this.flavor_text = flavor_text;
        this.language = language;
        this.version = version;
    }

    public String getFlavor_text() {
        return flavor_text;
    }

    public NamedAPIResource getLanguage() {
        return language;
    }

    public NamedAPIResource getVersion() {
        return version;
    }
}
