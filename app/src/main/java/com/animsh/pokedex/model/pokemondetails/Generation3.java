package com.animsh.pokedex.model.pokemondetails;

import com.google.gson.annotations.SerializedName;

public class Generation3 {
    private Emerald emerald;
    @SerializedName("firered-leafgreen")
    private FireredLeafgreen fireredleafgreen;
    @SerializedName("ruby-sapphire")
    private RubySapphire rubySapphire;

    public Generation3(Emerald emerald, FireredLeafgreen fireredleafgreen, RubySapphire rubySapphire) {
        this.emerald = emerald;
        this.fireredleafgreen = fireredleafgreen;
        this.rubySapphire = rubySapphire;
    }

    public Emerald getEmerald() {
        return emerald;
    }

    public FireredLeafgreen getFireredleafgreen() {
        return fireredleafgreen;
    }

    public RubySapphire getRubySapphire() {
        return rubySapphire;
    }
}
