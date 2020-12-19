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
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

public class PokemonListAdapter extends RecyclerView.Adapter<PokemonListAdapter.PokemonVH> {

    private static final String TAG = "ADAPTER";
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
        holder.setData(pokemonList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return pokemonList.size();
    }

    public class PokemonVH extends RecyclerView.ViewHolder {
        TextView pokemonName, pokemonNumber, type1, type2;
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
            type1 = itemView.findViewById(R.id.type_1);
            type2 = itemView.findViewById(R.id.type_2);
        }

        public void setData(Pokemon pokemon, int position) {
            int id = position + 1;
            pokemonName.setText(getProperName(pokemon.getName()));
            String newId = "";
            if (String.valueOf(id).length() == 1) {
                newId = "#000" + id;
            } else if (String.valueOf(id).length() == 2) {
                newId = "#00" + id;
            } else if (String.valueOf(id).length() == 3) {
                newId = "#0" + id;
            } else {
                newId = "#" + id;
            }
            pokemonNumber.setText(newId);
            String url = "https://pokeres.bastionbot.org/images/pokemon/" + id + ".png";
            Glide.with(context).asBitmap()
                    .load(url)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(pokemonImage);
        }

        public String getProperName(String oldName) {
            // stores each characters to a char array
            char[] charArray = oldName.toCharArray();
            boolean foundSpace = true;

            for (int i = 0; i < charArray.length; i++) {

                // if the array element is a letter
                if (Character.isLetter(charArray[i])) {

                    // check space is present before the letter
                    if (foundSpace) {

                        // change the letter into uppercase
                        charArray[i] = Character.toUpperCase(charArray[i]);
                        foundSpace = false;
                    }
                } else {
                    // if the new character is not character
                    foundSpace = true;
                }
            }

            // convert the char array to the string
            return String.valueOf(charArray);
        }

    }
}
