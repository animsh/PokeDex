package com.animsh.pokedex.model;

import com.animsh.pokedex.model.pokemonspecies.FlavorTextEntries;
import com.animsh.pokedex.model.pokemonspecies.Genera;

import java.util.List;

public class PokemonSpecies {
    private List<FlavorTextEntries> flavor_text_entries;
    private List<Genera> genera;

    public PokemonSpecies(List<FlavorTextEntries> flavor_text_entries, List<Genera> genera) {
        this.flavor_text_entries = flavor_text_entries;
        this.genera = genera;
    }

    public List<FlavorTextEntries> getFlavor_text_entries() {
        return flavor_text_entries;
    }

    public List<Genera> getGenera() {
        return genera;
    }
}
