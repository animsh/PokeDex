package com.animsh.pokedex.model;

import com.animsh.pokedex.model.pokemonspecies.FlavorTextEntries;
import com.animsh.pokedex.model.pokemonspecies.Genera;

import java.util.List;

public class PokemonSpecies {
    private List<FlavorTextEntries> flavor_text_entries;
    private List<Genera> genera;
    private EvolutionChain evolution_chain;

    public PokemonSpecies() {
    }

    public PokemonSpecies(List<FlavorTextEntries> flavor_text_entries, List<Genera> genera, EvolutionChain evolution_chain) {
        this.flavor_text_entries = flavor_text_entries;
        this.genera = genera;
        this.evolution_chain = evolution_chain;
    }

    public List<FlavorTextEntries> getFlavor_text_entries() {
        return flavor_text_entries;
    }

    public List<Genera> getGenera() {
        return genera;
    }

    public EvolutionChain getEvolution_chain() {
        return evolution_chain;
    }
}
