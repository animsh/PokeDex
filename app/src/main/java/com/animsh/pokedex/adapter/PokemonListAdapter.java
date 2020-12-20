package com.animsh.pokedex.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.GradientDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.palette.graphics.Palette;
import androidx.recyclerview.widget.RecyclerView;

import com.animsh.pokedex.R;
import com.animsh.pokedex.model.Pokemon;
import com.animsh.pokedex.model.PokemonDetails;
import com.animsh.pokedex.utils.PokeApiCalls;
import com.animsh.pokedex.utils.RetrofitClient;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

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

    public static Palette.Swatch getDominantSwatch(Palette palette) {
        // find most-represented swatch based on population
        return Collections.max(palette.getSwatches(), new Comparator<Palette.Swatch>() {
            @Override
            public int compare(Palette.Swatch sw1, Palette.Swatch sw2) {
                return Integer.compare(sw1.getPopulation(), sw2.getPopulation());
            }
        });
    }

    public static int getDominantColor1(Bitmap bitmap) {
        List<Palette.Swatch> swatchesTemp = Palette.from(bitmap).generate().getSwatches();
        List<Palette.Swatch> swatches = new ArrayList<Palette.Swatch>(swatchesTemp);
        Collections.sort(swatches, new Comparator<Palette.Swatch>() {
            @Override
            public int compare(Palette.Swatch swatch1, Palette.Swatch swatch2) {
                return swatch2.getPopulation() - swatch1.getPopulation();
            }
        });
        return swatches.size() > 0 ? swatches.get(0).getRgb() : 0;
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
            Retrofit retrofit = RetrofitClient.getClient();
            PokeApiCalls pokeApiCalls = retrofit.create(PokeApiCalls.class);
            Call<PokemonDetails> call = pokeApiCalls.getPokemonDetails(id);
            call.enqueue(new Callback<PokemonDetails>() {
                @Override
                public void onResponse(Call<PokemonDetails> call, Response<PokemonDetails> response) {
                    Log.d(TAG, "onResponse: " + response.body());
                    PokemonDetails pokemonDetails = response.body();
                    if (pokemonDetails.getTypes().size() > 1) {
                        type1.setText(pokemonDetails.getTypes().get(0).getType().getName().toUpperCase());
                        type2.setVisibility(View.VISIBLE);
                        type2.setText(pokemonDetails.getTypes().get(1).getType().getName().toUpperCase());
                    } else if (pokemonDetails.getTypes().size() == 1) {
                        type1.setText(pokemonDetails.getTypes().get(0).getType().getName().toUpperCase());
                    }
                }

                @Override
                public void onFailure(Call<PokemonDetails> call, Throwable t) {
                    Log.d(TAG, "onFailure: " + t.getMessage());
                }
            });

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
                    .listener(new RequestListener<Bitmap>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                            Palette p = Palette.from(resource).generate();
                            List<Palette.Swatch> pss = p.getSwatches();
                            Palette.Swatch palette = getDominantSwatch(p);
                            pokemonContainer.setBackgroundColor(palette.getRgb());
                            pokemonName.setTextColor(palette.getTitleTextColor());
                            pokemonNumber.setTextColor(palette.getTitleTextColor());
                            GradientDrawable drawable1 = (GradientDrawable) type1.getBackground();
                            GradientDrawable drawable2 = (GradientDrawable) type2.getBackground();
                            type1.setTextColor(palette.getBodyTextColor());
                            type2.setTextColor(palette.getBodyTextColor());
                            drawable1.setStroke(2, palette.getBodyTextColor()); // set stroke width and stroke color
                            drawable2.setStroke(2, palette.getBodyTextColor()); // set stroke width and stroke color
                            return false;
                        }
                    })
                    .placeholder(R.drawable.ic_pokeball)
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
