package com.animsh.pokedex.model.pokemondetails;

public class VersionGroupDetails {
    private int level_learned_at;
    private NamedAPIResource move_learn_method;
    private NamedAPIResource versionGroup;

    public VersionGroupDetails(int level_learned_at, NamedAPIResource move_learn_method, NamedAPIResource versionGroup) {
        this.level_learned_at = level_learned_at;
        this.move_learn_method = move_learn_method;
        this.versionGroup = versionGroup;
    }

    public int getLevel_learned_at() {
        return level_learned_at;
    }

    public NamedAPIResource getMove_learn_method() {
        return move_learn_method;
    }

    public NamedAPIResource getVersionGroup() {
        return versionGroup;
    }
}
