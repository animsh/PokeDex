package com.animsh.pokedex.model;

import com.animsh.pokedex.model.pokemondetails.Abilities;
import com.animsh.pokedex.model.pokemondetails.GameIndices;
import com.animsh.pokedex.model.pokemondetails.Moves;
import com.animsh.pokedex.model.pokemondetails.NamedAPIResource;
import com.animsh.pokedex.model.pokemondetails.Sprites;
import com.animsh.pokedex.model.pokemondetails.Stats;
import com.animsh.pokedex.model.pokemondetails.Types;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class PokemonDetails {

    private List<Abilities> abilities;
    private int base_experience;
    private List<NamedAPIResource> forms;
    private List<GameIndices> game_indices;
    private int height;
    private Array[] held_items;
    private int id;
    private boolean is_default;
    private String location_area_encounters;
    private List<Moves> moves;
    private String name;
    private String order;
    private NamedAPIResource species;
    private Sprites sprites;
    private List<Stats> stats;
    private List<Types> types;
    private int weight;

    public PokemonDetails() {
    }

    public PokemonDetails(List<Abilities> abilities, int base_experience, List<NamedAPIResource> forms, List<GameIndices> game_indices, int height, Array[] held_items, int id, boolean is_default, String location_area_encounters, List<Moves> moves, String name, String order, NamedAPIResource species, Sprites sprites, List<Stats> stats, List<Types> types, int weight) {
        this.abilities = abilities;
        this.base_experience = base_experience;
        this.forms = forms;
        this.game_indices = game_indices;
        this.height = height;
        this.held_items = held_items;
        this.id = id;
        this.is_default = is_default;
        this.location_area_encounters = location_area_encounters;
        this.moves = moves;
        this.name = name;
        this.order = order;
        this.species = species;
        this.sprites = sprites;
        this.stats = stats;
        this.types = types;
        this.weight = weight;
    }

    public List<Abilities> getAbilities() {
        return abilities;
    }

    public int getBase_experience() {
        return base_experience;
    }

    public List<NamedAPIResource> getForms() {
        return forms;
    }

    public List<GameIndices> getGame_indices() {
        return game_indices;
    }

    public int getHeight() {
        return height;
    }

    public Array[] getHeld_items() {
        return held_items;
    }

    public int getId() {
        return id;
    }

    public boolean isIs_default() {
        return is_default;
    }

    public String getLocation_area_encounters() {
        return location_area_encounters;
    }

    public List<Moves> getMoves() {
        return moves;
    }

    public String getName() {
        return name;
    }

    public String getOrder() {
        return order;
    }

    public NamedAPIResource getSpecies() {
        return species;
    }

    public Sprites getSprites() {
        return sprites;
    }

    public List<Stats> getStats() {
        return stats;
    }

    public List<Types> getTypes() {
        return types;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "PokemonDetails{" +
                "abilities=" + abilities +
                ", base_experience=" + base_experience +
                ", forms=" + forms +
                ", game_indices=" + game_indices +
                ", height=" + height +
                ", held_items=" + Arrays.toString(held_items) +
                ", id=" + id +
                ", is_default=" + is_default +
                ", location_area_encounters='" + location_area_encounters + '\'' +
                ", moves=" + moves +
                ", name='" + name + '\'' +
                ", order='" + order + '\'' +
                ", species=" + species +
                ", sprites=" + sprites +
                ", stats=" + stats +
                ", types=" + types +
                ", weight=" + weight +
                '}';
    }
}
