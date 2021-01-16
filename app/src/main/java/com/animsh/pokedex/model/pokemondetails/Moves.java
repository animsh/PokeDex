package com.animsh.pokedex.model.pokemondetails;

import java.util.List;

public class Moves {
    private NamedAPIResource move;
    private List<VersionGroupDetails> version_group_details;

    public Moves(NamedAPIResource move, List<VersionGroupDetails> version_group_details) {
        this.move = move;
        this.version_group_details = version_group_details;
    }

    public NamedAPIResource getMove() {
        return move;
    }

    public List<VersionGroupDetails> getVersion_group_details() {
        return version_group_details;
    }
}
