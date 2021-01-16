package com.animsh.pokedex.model.pokemondetails;

/**
 * Created by animsh on 1/16/2021.
 */
public class NamedAPIResource {
    private String name;
    private String url;

    public NamedAPIResource(String name, String url) {
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
