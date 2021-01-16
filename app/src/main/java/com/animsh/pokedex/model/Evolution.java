package com.animsh.pokedex.model;

/**
 * Created by animsh on 1/16/2021.
 */
public class Evolution {
    private int id;
    private NamedAPIResource baby_trigger_item;
    private ChainLink chain;

    public Evolution() {
    }

    public Evolution(int id, NamedAPIResource baby_trigger_item, ChainLink chain) {
        this.id = id;
        this.baby_trigger_item = baby_trigger_item;
        this.chain = chain;
    }

    public int getId() {
        return id;
    }

    public NamedAPIResource getBaby_trigger_item() {
        return baby_trigger_item;
    }

    public ChainLink getChain() {
        return chain;
    }
}
