package com.animsh.pokedex.model.pokemondetails;

import com.google.gson.annotations.SerializedName;

public class Generation7 {
    private Icons icons;
    @SerializedName("ultra-sun-ultra-moon")
    private UltraSunUltraMoon ultraSunUltraMoon;

    public Generation7(Icons icons, UltraSunUltraMoon ultraSunUltraMoon) {
        this.icons = icons;
        this.ultraSunUltraMoon = ultraSunUltraMoon;
    }

    public Icons getIcons() {
        return icons;
    }

    public UltraSunUltraMoon getUltraSunUltraMoon() {
        return ultraSunUltraMoon;
    }
}
