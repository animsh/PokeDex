package com.animsh.pokedex.model.pokemondetails;

public class Abilities {
    private NamedAPIResource ability;
    private boolean is_hidden;
    private int slot;

    public Abilities(NamedAPIResource ability, boolean is_hidden, int slot) {
        this.ability = ability;
        this.is_hidden = is_hidden;
        this.slot = slot;
    }

    public NamedAPIResource getAbility() {
        return ability;
    }

    public boolean isIs_hidden() {
        return is_hidden;
    }

    public int getSlot() {
        return slot;
    }
}
