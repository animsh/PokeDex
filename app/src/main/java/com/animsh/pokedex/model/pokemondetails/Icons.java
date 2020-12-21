package com.animsh.pokedex.model.pokemondetails;

public class Icons {
    private String front_default;
    private String front_female;

    public Icons(String front_default, String front_female) {
        this.front_default = front_default;
        this.front_female = front_female;
    }

    public String getFront_default() {
        return front_default;
    }

    public String getFront_female() {
        return front_female;
    }
}
