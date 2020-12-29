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
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.palette.graphics.Palette;
import androidx.recyclerview.widget.RecyclerView;

import com.animsh.pokedex.R;
import com.animsh.pokedex.model.Pokemon;
import com.animsh.pokedex.model.PokemonDetails;
import com.animsh.pokedex.network.PokeApiCalls;
import com.animsh.pokedex.ui.PokemonDetailsActivity;
import com.animsh.pokedex.utils.RetrofitClient;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.skydoves.transformationlayout.TransformationLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PokemonListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable {

    private static final String TAG = "ADAPTER";
    private final int ITEM = 0;
    private final int LOADING = 1;
    private List<Pokemon> pokemonList;
    private final boolean retryPageLoad = false;
    private List<Pokemon> pokemonListClone;
    private Context context;

    private boolean isLoadingAdded = false;
    private List<Pokemon> pokemonListSearchResult;

    public PokemonListAdapter() {
    }

    public PokemonListAdapter(Context context) {
        this.context = context;
        pokemonList = new ArrayList<>();
        pokemonListClone = new ArrayList<>();
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

    public List<Pokemon> getPokemonList() {
        return pokemonList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        switch (viewType) {
            case ITEM:
                View viewItem = inflater.inflate(R.layout.container_pokemon, viewGroup, false);
                viewHolder = new PokemonVH(viewItem);
                break;
            case LOADING:
                View viewLoading = inflater.inflate(R.layout.item_progress, viewGroup, false);
                viewHolder = new LoadingVH(viewLoading);
                break;
        }

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        switch (getItemViewType(i)) {
            case ITEM:
                PokemonVH viewHolderPokemon = (PokemonVH) viewHolder;
                viewHolderPokemon.setData(pokemonList.get(i), i);
                viewHolderPokemon.setIsRecyclable(false);
                break;
            case LOADING:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return pokemonList == null ? 0 : pokemonList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
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

    @Override
    public int getItemViewType(int position) {
        return (position == pokemonList.size() - 1 && isLoadingAdded) ? LOADING : ITEM;
    }

    public void agregarPokemones(List<Pokemon> pokemonArrayList) {
        this.pokemonList.addAll(pokemonArrayList);
        notifyDataSetChanged();
    }

    public void addLoadingFooter() {
        isLoadingAdded = true;
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;
    }

    private Pokemon getItem(int position) {
        return pokemonList.get(position);
    }

    public void addAll(List<Pokemon> pokemonResults) {
        for (Pokemon result : pokemonResults) {
            add(result);
        }
    }

    private void add(Pokemon result) {
        pokemonList.add(result);
        pokemonListClone = pokemonList;
        notifyItemInserted(pokemonList.size() - 1);
    }

    public void clear() {
        isLoadingAdded = false;
        while (getItemCount() > 0) {
            remove(getItem(0));
        }
    }

    private void remove(Pokemon r) {
        int position = pokemonList.indexOf(r);
        if (position > -1) {
            pokemonList.remove(position);
            pokemonListClone = pokemonList;
            notifyItemRemoved(position);
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                String term = constraint.toString();
                if (term.isEmpty())
                    pokemonListSearchResult = pokemonListClone;
                else {
                    pokemonListSearchResult = new ArrayList<>();
                    for (Pokemon item : pokemonListClone)
                        if (item.getName().toLowerCase().contains(term.toLowerCase()) | item.getUrl().toLowerCase().contains(term.toLowerCase()))
                            pokemonListSearchResult.add(item);
                }
                results.values = pokemonListSearchResult;
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                pokemonList = (List<Pokemon>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    class LoadingVH extends RecyclerView.ViewHolder {
        private final ProgressBar mProgressBar;

        LoadingVH(View itemView) {
            super(itemView);
            mProgressBar = itemView.findViewById(R.id.loadmore_progress);
        }

    }

    public class PokemonVH extends RecyclerView.ViewHolder {
        TextView pokemonName, pokemonNumber, type1, type2;
        ImageView pokemonImage, baseImage;
        LinearLayout pokemonImageContainer;
        ConstraintLayout pokemonContainer;
        Palette.Swatch palette;
        TransformationLayout transformationLayout;

        public PokemonVH(@NonNull View itemView) {
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

        public void setData(Pokemon pokemon, int position) {

            String[] pokemonUrl = pokemon.getUrl().split("\\/");
            Log.d(TAG, "setData: " + pokemonUrl[pokemonUrl.length - 1]);
            int id = Integer.parseInt(pokemonUrl[pokemonUrl.length - 1]);
            Retrofit retrofit = RetrofitClient.getClient();
            PokeApiCalls pokeApiCalls = retrofit.create(PokeApiCalls.class);
            Call<PokemonDetails> call = pokeApiCalls.getPokemonDetails(id);
            call.enqueue(new Callback<PokemonDetails>() {
                @Override
                public void onResponse(Call<PokemonDetails> call, Response<PokemonDetails> response) {
                    Log.d(TAG, "onResponse: " + response.body());
                    PokemonDetails pokemonDetails = response.body();
                    if (pokemonDetails.getTypes() != null) {
                        if (pokemonDetails.getTypes().size() > 1) {
                            type1.setText(pokemonDetails.getTypes().get(0).getType().getName().toUpperCase());
                            type2.setVisibility(View.VISIBLE);
                            type2.setText(pokemonDetails.getTypes().get(1).getType().getName().toUpperCase());
                        } else if (pokemonDetails.getTypes().size() == 1) {
                            type1.setText(pokemonDetails.getTypes().get(0).getType().getName().toUpperCase());
                        }
                    }
                }

                @Override
                public void onFailure(Call<PokemonDetails> call, Throwable t) {
                    Log.d(TAG, "onFailure: " + t.getMessage());
                }
            });

            pokemonName.setText(getProperName(pokemon.getName().replace("-", " ")));
            String newId = "";
            String baseId = "";
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

            Log.d(TAG, "setData: " + idArrayList.size() + idArrayList.contains(id));

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
                                    .placeholder(R.drawable.ic_pokeball)
                                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                                    .into(pokemonImage);
                            return true;
                        }
                    })
                    .placeholder(R.drawable.ic_pokeball)
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

    }

}

