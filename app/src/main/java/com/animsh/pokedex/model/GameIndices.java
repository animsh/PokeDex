package com.animsh.pokedex.model;

public class GameIndices {
    private int game_index;
    private Version version;

    public GameIndices(int game_index, Version version) {
        this.game_index = game_index;
        this.version = version;
    }

    public int getGame_index() {
        return game_index;
    }

    public Version getVersion() {
        return version;
    }
}
