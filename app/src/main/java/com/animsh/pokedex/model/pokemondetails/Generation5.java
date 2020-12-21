package com.animsh.pokedex.model.pokemondetails;

import com.google.gson.annotations.SerializedName;

public class Generation5 {
    @SerializedName("black-white")
    private BlackWhite blackWhite;

    public Generation5(BlackWhite blackWhite) {
        this.blackWhite = blackWhite;
    }

    public BlackWhite getBlackWhite() {
        return blackWhite;
    }
}
