package com.animsh.pokedex.model.pokemonspecies;

public class Language {
    private String name;
    private String url;

    public Language(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}
