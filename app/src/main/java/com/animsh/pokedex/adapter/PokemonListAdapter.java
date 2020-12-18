package com.animsh.pokedex.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.animsh.pokedex.R;
import com.animsh.pokedex.model.Pokemon;

import java.util.List;

public class PokemonListAdapter extends RecyclerView.Adapter<PokemonListAdapter.PokemonVH> {

    List<Pokemon> pokemonList;
    Context context;

    public PokemonListAdapter() {
    }

    public PokemonListAdapter(List<Pokemon> pokemonList, Context context) {
        this.pokemonList = pokemonList;
        this.context = context;
    }

    @NonNull
    @Override
    public PokemonVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PokemonVH(LayoutInflater.from(context)
                .inflate(R.layout.container_pokemon, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonVH holder, int position) {
        holder.setData(pokemonList.get(position));
    }

    @Override
    public int getItemCount() {
        return pokemonList.size();
    }

    public class PokemonVH extends RecyclerView.ViewHolder {
        TextView pokemonName, pokemonNumber;
        ImageView pokemonImage;
        LinearLayout pokemonImageContainer;
        ConstraintLayout pokemonContainer;

        public PokemonVH(@NonNull View itemView) {
            super(itemView);
            pokemonName = itemView.findViewById(R.id.pokemon_name);
            pokemonNumber = itemView.findViewById(R.id.pokemon_number);
            pokemonImage = itemView.findViewById(R.id.pokemon_image);
            pokemonContainer = itemView.findViewById(R.id.pokemon_container);
            pokemonImageContainer = itemView.findViewById(R.id.pokemon_image_container);
        }

        public void setData(Pokemon pokemon) {
            pokemonName.setText(pokemon.getName());
        }

    }
}
