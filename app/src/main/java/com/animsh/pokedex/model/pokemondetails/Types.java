package com.animsh.pokedex.model.pokemondetails;

public class Types {
    private int slot;
    private Type type;

    public Types() {
    }

    public Types(int slot, Type type) {
        this.slot = slot;
        this.type = type;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
