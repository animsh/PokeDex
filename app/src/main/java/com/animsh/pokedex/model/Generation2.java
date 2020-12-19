package com.animsh.pokedex.model;

public class Generation2 {

    private Crystal crystal;
    private Gold gold;
    private Silver silver;

    public Generation2(Crystal crystal, Gold gold, Silver silver) {
        this.crystal = crystal;
        this.gold = gold;
        this.silver = silver;
    }

    public Crystal getCrystal() {
        return crystal;
    }

    public Gold getGold() {
        return gold;
    }

    public Silver getSilver() {
        return silver;
    }
}
