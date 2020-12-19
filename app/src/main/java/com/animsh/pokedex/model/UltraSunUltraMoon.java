package com.animsh.pokedex.model;

public class UltraSunUltraMoon {
    private String front_default;
    private String front_female;
    private String front_shiny;
    private String front_shiny_female;

    public UltraSunUltraMoon(String front_default, String front_female, String front_shiny, String front_shiny_female) {
        this.front_default = front_default;
        this.front_female = front_female;
        this.front_shiny = front_shiny;
        this.front_shiny_female = front_shiny_female;
    }

    public String getFront_default() {
        return front_default;
    }

    public String getFront_female() {
        return front_female;
    }

    public String getFront_shiny() {
        return front_shiny;
    }

    public String getFront_shiny_female() {
        return front_shiny_female;
    }
}
