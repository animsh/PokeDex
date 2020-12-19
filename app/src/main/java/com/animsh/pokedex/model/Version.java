package com.animsh.pokedex.model;

public class Version {
    private String name;
    private String url;

    public Version(String name, String url) {
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
