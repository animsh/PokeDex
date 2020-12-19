package com.animsh.pokedex.ui;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.animsh.pokedex.R;
import com.animsh.pokedex.model.PokemonCollection;
import com.animsh.pokedex.utils.NetworkCallMethods;

public class MainActivity extends AppCompatActivity {
    public String TAG = "MainActivity.class";
    public RecyclerView pokemonRecyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pokemonRecyclerview = findViewById(R.id.pokemon_recyclerview);
        new TaskMater().execute();
        //NetworkCallMethods.getPokemonDetail(1);
    }

    class TaskMater extends AsyncTask<Void, Void, PokemonCollection> {
        PokemonCollection pokemonCollection;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pokemonCollection = new PokemonCollection();
        }

        @Override
        protected PokemonCollection doInBackground(Void... voids) {
            pokemonCollection = NetworkCallMethods.getPokemonCollection(pokemonRecyclerview, MainActivity.this);
            return pokemonCollection;
        }

        @Override
        protected void onPostExecute(PokemonCollection pokemonCollection) {
            super.onPostExecute(pokemonCollection);

        }
    }
}