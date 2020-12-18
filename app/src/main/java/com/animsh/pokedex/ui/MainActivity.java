package com.animsh.pokedex.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.animsh.pokedex.R;
import com.animsh.pokedex.utils.NetworkCallMethods;

public class MainActivity extends AppCompatActivity {
    public String TAG = "MainActivity.class";
    public RecyclerView pokemonRecyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pokemonRecyclerview = findViewById(R.id.pokemon_recyclerview);
        NetworkCallMethods.getPokemonCollection(pokemonRecyclerview, this);
    }
}