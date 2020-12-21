package com.animsh.pokedex.model.pokemondetails;

public class Forms {
    private String name;
    private String url;

    public Forms(String name, String url) {
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
