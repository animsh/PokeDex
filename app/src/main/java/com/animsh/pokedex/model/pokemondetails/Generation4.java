package com.animsh.pokedex.model.pokemondetails;

import com.google.gson.annotations.SerializedName;

public class Generation4 {
    @SerializedName("diamond-pearl")
    private DiamondPearl diamondPearl;

    @SerializedName("heartgold-soulsilver")
    private HeartgoldSoulsilver heartgoldSoulsilver;

    private Platinum platinum;

    public Generation4(DiamondPearl diamondPearl, HeartgoldSoulsilver heartgoldSoulsilver, Platinum platinum) {
        this.diamondPearl = diamondPearl;
        this.heartgoldSoulsilver = heartgoldSoulsilver;
        this.platinum = platinum;
    }

    public DiamondPearl getDiamondPearl() {
        return diamondPearl;
    }

    public HeartgoldSoulsilver getHeartgoldSoulsilver() {
        return heartgoldSoulsilver;
    }

    public Platinum getPlatinum() {
        return platinum;
    }
}
