package com.animsh.pokedex.model;

public class Species {
    private String name;
    private String url;

    public Species(String name, String url) {
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
