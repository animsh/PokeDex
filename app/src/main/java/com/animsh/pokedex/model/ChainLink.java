package com.animsh.pokedex.model;

import java.util.List;

/**
 * Created by animsh on 1/16/2021.
 */
public class ChainLink {
    private boolean is_baby;
    private NamedAPIResource species;
    private List<EvolutionDetail> evolution_details;
    private List<ChainLink> evolves_to;

    public ChainLink(boolean is_baby, NamedAPIResource species, List<EvolutionDetail> evolution_details, List<ChainLink> evolves_to) {
        this.is_baby = is_baby;
        this.species = species;
        this.evolution_details = evolution_details;
        this.evolves_to = evolves_to;
    }

    public boolean isIs_baby() {
        return is_baby;
    }

    public NamedAPIResource getSpecies() {
        return species;
    }

    public List<EvolutionDetail> getEvolution_details() {
        return evolution_details;
    }

    public List<ChainLink> getEvolves_to() {
        return evolves_to;
    }
}
