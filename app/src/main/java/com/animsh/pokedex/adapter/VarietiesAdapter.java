package com.animsh.pokedex.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.palette.graphics.Palette;
import androidx.recyclerview.widget.RecyclerView;

import com.animsh.pokedex.R;
import com.animsh.pokedex.model.Pokemon;
import com.animsh.pokedex.ui.PokemonDetailsActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.skydoves.transformationlayout.TransformationLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by animsh on 1/17/2021.
 */
public class VarietiesAdapter extends RecyclerView.Adapter<VarietiesAdapter.VarietyViewHolder> {

    private static final String TAG = "V_ADAPTER";
    private List<Pokemon> pokemonList;
    private Context context;

    public VarietiesAdapter() {
    }

    public VarietiesAdapter(List<Pokemon> pokemonList, Context context) {
        this.pokemonList = pokemonList;
        this.context = context;
    }

    @NonNull
    @Override
    public VarietyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VarietyViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.container_pokemon_evolution, parent, false
        ));
    }

    @Override
    public void onBindViewHolder(@NonNull VarietyViewHolder holder, int position) {
        holder.setData(pokemonList.get(position));
    }

    @Override
    public int getItemCount() {
        return pokemonList.size();
    }

    public class VarietyViewHolder extends RecyclerView.ViewHolder {
        TextView pokemonName, pokemonNumber, type1, type2;
        ImageView pokemonImage, baseImage;
        LinearLayout pokemonImageContainer;
        LinearLayout pokemonContainer;
        Palette.Swatch palette;
        TransformationLayout transformationLayout;

        public VarietyViewHolder(@NonNull View itemView) {
            super(itemView);
            pokemonName = itemView.findViewById(R.id.pokemon_name);
            pokemonNumber = itemView.findViewById(R.id.pokemon_number);
            pokemonImage = itemView.findViewById(R.id.pokemon_image);
            baseImage = itemView.findViewById(R.id.base_image);
            pokemonContainer = itemView.findViewById(R.id.pokemon_container);
            pokemonImageContainer = itemView.findViewById(R.id.pokemon_image_container);
            type1 = itemView.findViewById(R.id.type_1);
            type2 = itemView.findViewById(R.id.type_2);
            transformationLayout = itemView.findViewById(R.id.transformationLayout);
        }

        public void setData(Pokemon pokemon) {
            String[] pokemonUrl = pokemon.getUrl().split("\\/");
            int id = Integer.parseInt(pokemonUrl[pokemonUrl.length - 1]);

            try {
                JSONObject object = new JSONObject(readJSON());
                JSONArray types = object.getJSONArray(pokemonUrl[pokemonUrl.length - 1]);
                if (types != null) {
                    if (types.length() == 1) {
                        type1.setText(types.getJSONObject(0).getJSONObject("type").getString("name").toUpperCase());
                        type2.setVisibility(View.GONE);
                        Log.d(TAG, types.getJSONObject(0).getJSONObject("type").getString("name"));
                    } else if (types.length() > 1) {
                        type1.setText(types.getJSONObject(0).getJSONObject("type").getString("name").toUpperCase());
                        type2.setVisibility(View.VISIBLE);
                        type2.setText(types.getJSONObject(1).getJSONObject("type").getString("name").toUpperCase());
                        Log.d(TAG, types.getJSONObject(0).getJSONObject("type").getString("name") + " "
                                + types.getJSONObject(1).getJSONObject("type").getString("name"));
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            pokemonName.setText(getProperName(pokemon.getName().replace("-", " ")));
            String newId;
            String baseId;
            if (String.valueOf(id).length() == 1) {
                newId = "#00" + id;
                baseId = "00" + id;
            } else if (String.valueOf(id).length() == 2) {
                newId = "#0" + id;
                baseId = "0" + id;
            } else if (String.valueOf(id).length() == 3) {
                newId = "#" + id;
                baseId = String.valueOf(id);
            } else {
                newId = "#" + id;
                baseId = String.valueOf(id);
            }

            pokemonNumber.setText(newId);

            String base;
            String url;

            ArrayList<Integer> idArrayList = new ArrayList<>();
            for (int i = 10027; i <= 10032; i++) {
                idArrayList.add(i);
            }
            idArrayList.add(10061);
            for (int i = 10080; i <= 10085; i++) {
                idArrayList.add(i);
            }
            for (int i = 10091; i <= 10220; i++) {
                idArrayList.add(i);
            }
            if (id < 893 | id >= 10001) {
                if (idArrayList.contains(id)) {
                    base = "https://raw.githubusercontent.com/animsh/PokemonSprites/main/imagesHQ/" + id + ".png";
                    url = "https://raw.githubusercontent.com/animsh/PokemonSprites/main/imagesHQ/" + id + ".png";
                } else {
                    base = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + id + ".png";
                    url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/" + id + ".png";
                }
            } else {
                base = "https://assets.pokemon.com/assets/cms2/img/pokedex/detail/" + baseId + ".png";
                url = "https://assets.pokemon.com/assets/cms2/img/pokedex/detail/" + baseId + ".png";
            }
            Glide.with(context).asBitmap()
                    .load(base)
                    .listener(new RequestListener<Bitmap>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                            Palette p = Palette.from(resource).generate();
                            List<Palette.Swatch> pss = p.getSwatches();
                            palette = getDominantSwatch(p);
                            pokemonContainer.setBackgroundColor(palette.getRgb());
                            pokemonName.setTextColor(palette.getTitleTextColor());
                            pokemonNumber.setTextColor(palette.getTitleTextColor());
                            GradientDrawable drawable1 = (GradientDrawable) type1.getBackground();
                            GradientDrawable drawable2 = (GradientDrawable) type2.getBackground();
                            type1.setTextColor(palette.getBodyTextColor());
                            type2.setTextColor(palette.getBodyTextColor());
                            drawable1.setStroke(2, palette.getBodyTextColor());
                            drawable1.setCornerRadius(20f);// set stroke width and stroke color
                            drawable2.setStroke(2, palette.getBodyTextColor());
                            drawable2.setCornerRadius(20f);// set stroke width and stroke color
                            Glide.with(context).asBitmap()
                                    .load(url)
                                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                                    .into(pokemonImage);
                            return true;
                        }
                    })
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(baseImage);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (palette != null) {
                        Bundle bundle = transformationLayout.withContext(context, "myTransitionName");
                        Intent pokemonDetails = new Intent(context, PokemonDetailsActivity.class);
                        pokemonDetails.putExtra("bColor", palette.getRgb());
                        pokemonDetails.putExtra("tColor", palette.getTitleTextColor());
                        pokemonDetails.putExtra("btColor", palette.getBodyTextColor());
                        pokemonDetails.putExtra("id", id);
                        pokemonDetails.putExtra("TransformationParams", transformationLayout.getParcelableParams());
                        context.startActivity(pokemonDetails, bundle);
                    }
                }
            });
        }

        public String readJSON() {
            String json = null;
            try {
                // Opening data.json file
                InputStream inputStream = context.getAssets().open("types.json");
                int size = inputStream.available();
                byte[] buffer = new byte[size];
                // read values in the byte array
                inputStream.read(buffer);
                inputStream.close();
                // convert byte to string
                json = new String(buffer, StandardCharsets.UTF_8);
            } catch (IOException e) {
                e.printStackTrace();
                return json;
            }
            return json;
        }
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
