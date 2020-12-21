package com.animsh.pokedex.model.pokemondetails;

import com.google.gson.annotations.SerializedName;

public class Generation6 {
    @SerializedName("omegaruby-alphasapphire")
    private OmegarubyAlphasapphire omegarubyAlphasapphire;

    @SerializedName("x-y")
    private XY xy;

    public Generation6(OmegarubyAlphasapphire omegarubyAlphasapphire, XY xy) {
        this.omegarubyAlphasapphire = omegarubyAlphasapphire;
        this.xy = xy;
    }

    public OmegarubyAlphasapphire getOmegarubyAlphasapphire() {
        return omegarubyAlphasapphire;
    }

    public XY getXy() {
        return xy;
    }
}

