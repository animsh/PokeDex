package com.animsh.pokedex.model.pokemonspecies;

public class Genera {
    private String genus;
    private Language language;

    public Genera(String genus, Language language) {
        this.genus = genus;
        this.language = language;
    }

    public String getGenus() {
        return genus;
    }

    public Language getLanguage() {
        return language;
    }
}
