package com.animsh.pokedex.model.pokemonspecies;

import com.animsh.pokedex.model.pokemondetails.Version;

public class FlavorTextEntries {
    private String flavor_text;
    private Language language;
    private Version version;

    public FlavorTextEntries(String flavor_text, Language language, Version version) {
        this.flavor_text = flavor_text;
        this.language = language;
        this.version = version;
    }

    public String getFlavor_text() {
        return flavor_text;
    }

    public Language getLanguage() {
        return language;
    }

    public Version getVersion() {
        return version;
    }
}
