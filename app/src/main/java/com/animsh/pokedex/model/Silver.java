package com.animsh.pokedex.model;

public class Silver {
    private String back_default;
    private String back_shiny;
    private String front_default;
    private String front_shiny;

    public Silver(String back_default, String back_shiny, String front_default, String front_shiny) {
        this.back_default = back_default;
        this.back_shiny = back_shiny;
        this.front_default = front_default;
        this.front_shiny = front_shiny;
    }

    public String getBack_default() {
        return back_default;
    }

    public String getBack_shiny() {
        return back_shiny;
    }

    public String getFront_default() {
        return front_default;
    }

    public String getFront_shiny() {
        return front_shiny;
    }
}
