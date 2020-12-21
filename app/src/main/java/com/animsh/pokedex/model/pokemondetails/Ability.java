package com.animsh.pokedex.model.pokemondetails;

public class Ability {
    private String name;
    private String url;

    public Ability(String name, String url) {
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
