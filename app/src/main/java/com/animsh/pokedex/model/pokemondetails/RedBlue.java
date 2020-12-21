package com.animsh.pokedex.model.pokemondetails;

public class RedBlue {
    private String back_default;
    private String back_gray;
    private String front_default;
    private String front_gray;

    public RedBlue(String back_default, String back_gray, String front_default, String front_gray) {
        this.back_default = back_default;
        this.back_gray = back_gray;
        this.front_default = front_default;
        this.front_gray = front_gray;
    }

    public String getBack_default() {
        return back_default;
    }

    public String getBack_gray() {
        return back_gray;
    }

    public String getFront_default() {
        return front_default;
    }

    public String getFront_gray() {
        return front_gray;
    }
}
