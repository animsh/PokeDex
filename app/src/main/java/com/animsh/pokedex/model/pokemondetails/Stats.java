package com.animsh.pokedex.model.pokemondetails;

public class Stats {
    private int base_stat;
    private int effort;
    private NamedAPIResource stat;

    public Stats(int base_stat, int effort, NamedAPIResource stat) {
        this.base_stat = base_stat;
        this.effort = effort;
        this.stat = stat;
    }

    public int getBase_stat() {
        return base_stat;
    }

    public int getEffort() {
        return effort;
    }

    public NamedAPIResource getStat() {
        return stat;
    }
}
