package com.animsh.pokedex.model.pokemondetails;

public class VersionGroup {
    private String name;
    private String url;

    public VersionGroup(String name, String url) {
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
