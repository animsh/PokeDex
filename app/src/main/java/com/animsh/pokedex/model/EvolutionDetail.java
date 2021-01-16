package com.animsh.pokedex.model;

/**
 * Created by animsh on 1/16/2021.
 */
public class EvolutionDetail {
    private NamedAPIResource item;
    private NamedAPIResource trigger;
    private int gender;
    private NamedAPIResource held_item;
    private NamedAPIResource known_move;
    private NamedAPIResource known_move_type;
    private NamedAPIResource location;
    private int min_level;
    private int min_happiness;
    private int min_beauty;
    private int min_affection;
    private boolean needs_overworld_rain;
    private NamedAPIResource party_species;
    private NamedAPIResource party_type;
    private int relative_physical_stats;
    private String time_of_day;
    private NamedAPIResource trade_species;
    private boolean turn_upside_down;

    public EvolutionDetail(NamedAPIResource item, NamedAPIResource trigger, int gender, NamedAPIResource held_item, NamedAPIResource known_move, NamedAPIResource known_move_type, NamedAPIResource location, int min_level, int min_happiness, int min_beauty, int min_affection, boolean needs_overworld_rain, NamedAPIResource party_species, NamedAPIResource party_type, int relative_physical_stats, String time_of_day, NamedAPIResource trade_species, boolean turn_upside_down) {
        this.item = item;
        this.trigger = trigger;
        this.gender = gender;
        this.held_item = held_item;
        this.known_move = known_move;
        this.known_move_type = known_move_type;
        this.location = location;
        this.min_level = min_level;
        this.min_happiness = min_happiness;
        this.min_beauty = min_beauty;
        this.min_affection = min_affection;
        this.needs_overworld_rain = needs_overworld_rain;
        this.party_species = party_species;
        this.party_type = party_type;
        this.relative_physical_stats = relative_physical_stats;
        this.time_of_day = time_of_day;
        this.trade_species = trade_species;
        this.turn_upside_down = turn_upside_down;
    }

    public NamedAPIResource getItem() {
        return item;
    }

    public NamedAPIResource getTrigger() {
        return trigger;
    }

    public int getGender() {
        return gender;
    }

    public NamedAPIResource getHeld_item() {
        return held_item;
    }

    public NamedAPIResource getKnown_move() {
        return known_move;
    }

    public NamedAPIResource getKnown_move_type() {
        return known_move_type;
    }

    public NamedAPIResource getLocation() {
        return location;
    }

    public int getMin_level() {
        return min_level;
    }

    public int getMin_happiness() {
        return min_happiness;
    }

    public int getMin_beauty() {
        return min_beauty;
    }

    public int getMin_affection() {
        return min_affection;
    }

    public boolean isNeeds_overworld_rain() {
        return needs_overworld_rain;
    }

    public NamedAPIResource getParty_species() {
        return party_species;
    }

    public NamedAPIResource getParty_type() {
        return party_type;
    }

    public int getRelative_physical_stats() {
        return relative_physical_stats;
    }

    public String getTime_of_day() {
        return time_of_day;
    }

    public NamedAPIResource getTrade_species() {
        return trade_species;
    }

    public boolean isTurn_upside_down() {
        return turn_upside_down;
    }
}
