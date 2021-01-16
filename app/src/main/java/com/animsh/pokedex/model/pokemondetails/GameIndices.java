package com.animsh.pokedex.model.pokemondetails;

public class GameIndices {
    private int game_index;
    private NamedAPIResource version;

    public GameIndices(int game_index, NamedAPIResource version) {
        this.game_index = game_index;
        this.version = version;
    }

    public int getGame_index() {
        return game_index;
    }

    public NamedAPIResource getVersion() {
        return version;
    }
}
