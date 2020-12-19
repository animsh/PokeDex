package com.animsh.pokedex.model;

import com.google.gson.annotations.SerializedName;

public class Other {

    private DreamWorld dream_world;
    @SerializedName("official-artwork")
    private OfficialArtwork officialartwork;

    public Other(DreamWorld dream_world, OfficialArtwork officialartwork) {
        this.dream_world = dream_world;
        this.officialartwork = officialartwork;
    }

    public DreamWorld getDream_world() {
        return dream_world;
    }

    public OfficialArtwork getOfficialartwork() {
        return officialartwork;
    }
}
