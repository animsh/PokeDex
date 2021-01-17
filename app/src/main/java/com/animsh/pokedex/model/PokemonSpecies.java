package com.animsh.pokedex.model;

import com.animsh.pokedex.model.pokemondetails.EvolutionChain;
import com.animsh.pokedex.model.pokemonspecies.FlavorTextEntries;
import com.animsh.pokedex.model.pokemonspecies.Genera;
import com.animsh.pokedex.model.pokemonspecies.Variety;

import java.util.List;

public class PokemonSpecies {
    private List<FlavorTextEntries> flavor_text_entries;
    private List<Genera> genera;
    private EvolutionChain evolution_chain;
    private List<Variety> varieties;

    public PokemonSpecies(List<FlavorTextEntries> flavor_text_entries, List<Genera> genera, EvolutionChain evolution_chain, List<Variety> varieties) {
        this.flavor_text_entries = flavor_text_entries;
        this.genera = genera;
        this.evolution_chain = evolution_chain;
        this.varieties = varieties;
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

    public List<Variety> getVarieties() {
        return varieties;
    }
}
