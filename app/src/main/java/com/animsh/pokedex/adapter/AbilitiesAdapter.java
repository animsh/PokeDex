package com.animsh.pokedex.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.animsh.pokedex.R;
import com.animsh.pokedex.model.pokemondetails.Abilities;

import java.util.List;

public class AbilitiesAdapter extends RecyclerView.Adapter<AbilitiesAdapter.AbilitiesVH> {
    List<Abilities> abilitiesList;
    Context mContext;
    int backgroundColor, titleColor, bodyTextColor;

    public AbilitiesAdapter() {
    }

    public AbilitiesAdapter(List<Abilities> abilitiesList, Context mContext, int backgroundColor, int titleColor, int bodyTextColor) {
        this.abilitiesList = abilitiesList;
        this.mContext = mContext;
        this.backgroundColor = backgroundColor;
        this.titleColor = titleColor;
        this.bodyTextColor = bodyTextColor;
    }

    @NonNull
    @Override
    public AbilitiesVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AbilitiesVH(LayoutInflater.from(mContext)
                .inflate(R.layout.container_ability, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AbilitiesVH holder, int position) {
        holder.setData(abilitiesList.get(position));
    }

    @Override
    public int getItemCount() {
        return abilitiesList.size();
    }

    public void setStrokeWithCorner(View view, int intColor, boolean isIntColor, String stringColor) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);
        if (isIntColor) {
            drawable.setStroke(2, intColor);
        } else {
            drawable.setStroke(2, Color.parseColor(stringColor));
        }
        drawable.setCornerRadius(20f);
        view.setBackgroundDrawable(drawable);
    }

    public void setRoundedCorner(View view, int bColor) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setColor(bColor);
        drawable.setCornerRadius(20f);
        view.setBackgroundDrawable(drawable);
    }

    public class AbilitiesVH extends RecyclerView.ViewHolder {

        TextView pokemonAbility, labelHidden;
        ImageView infoBtn;

        public AbilitiesVH(@NonNull View itemView) {
            super(itemView);
            pokemonAbility = itemView.findViewById(R.id.pokemon_ability);
            labelHidden = itemView.findViewById(R.id.label_hidden);
            infoBtn = itemView.findViewById(R.id.info_btn);
        }

        public void setData(Abilities abilities) {
            pokemonAbility.setText(abilities.getAbility().getName());
            labelHidden.setTextColor(titleColor);
            if (abilities.isIs_hidden()) {
                labelHidden.setVisibility(View.VISIBLE);
                infoBtn.setColorFilter(backgroundColor, android.graphics.PorterDuff.Mode.SRC_IN);
                setStrokeWithCorner(pokemonAbility, backgroundColor, true, "");
                GradientDrawable shape = new GradientDrawable();
                shape.setShape(GradientDrawable.RECTANGLE);
                shape.setColor(backgroundColor);
                shape.setCornerRadii(new float[]{20, 20, 0, 0, 0, 0, 20, 20});
                labelHidden.setBackgroundDrawable(shape);
                pokemonAbility.setTextColor(backgroundColor);
            } else {
                labelHidden.setVisibility(View.GONE);
                setRoundedCorner(pokemonAbility, backgroundColor);
                pokemonAbility.setTextColor(titleColor);
                infoBtn.setColorFilter(titleColor, android.graphics.PorterDuff.Mode.SRC_IN);
            }
        }
    }
}
