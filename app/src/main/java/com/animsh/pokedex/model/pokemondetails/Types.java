package com.animsh.pokedex.model.pokemondetails;

public class Types {
    private int slot;
    private NamedAPIResource type;

    public Types() {
    }

    public Types(int slot, NamedAPIResource type) {
        this.slot = slot;
        this.type = type;
    }

    public int getSlot() {
        return slot;
    }

    public NamedAPIResource getType() {
        return type;
    }
}
