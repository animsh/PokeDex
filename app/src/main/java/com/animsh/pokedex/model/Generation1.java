package com.animsh.pokedex.model;

import com.google.gson.annotations.SerializedName;

public class Generation1 {
    @SerializedName("red-blue")
    private RedBlue redBlue;
    private Yellow yellow;

    public Generation1(RedBlue redBlue, Yellow yellow) {
        this.redBlue = redBlue;
        this.yellow = yellow;
    }

    public RedBlue getRedBlue() {
        return redBlue;
    }

    public Yellow getYellow() {
        return yellow;
    }
}
