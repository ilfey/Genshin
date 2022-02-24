package com.example.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.data.remote.characters.CharacterEntry;
import com.example.data.remote.characters.CharactersResponse;
import com.example.genshin.CharacterActivity;
import com.example.genshin.MainActivity;
import com.example.genshin.R;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.List;

public class CharacterAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Context ctx;
    private List<CharacterEntry> models;
    private MainActivity activity;
    private List<CharacterEntry> data;

    public CharacterAdapter(Context ctx, List<CharacterEntry> models) {
        this.ctx = ctx;
        this.models = models;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setListCharactersModels(List<CharacterEntry> entries) {
        this.models = entries;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0){
            return 0;
        } else {
            return 1;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        switch (viewType){
            case 0: {
                View sort = LayoutInflater.from(ctx).inflate(R.layout.sort, parent, false);
                return new CharacterViewHolder(sort);
            }
            default: {
                View characters = LayoutInflater.from(ctx).inflate(R.layout.model_characters, parent, false);

                characters.setOnClickListener(view -> {
                    Intent intent = new Intent(ctx, CharacterActivity.class);
                    intent.addFlags(intent.FLAG_ACTIVITY_NO_ANIMATION);
                    intent.putExtra("Theme", activity.CURRENT_THEME);
//                    intent.putExtra("Position", );
//                    intent.putExtra("Characters", (Serializable) models);
                    ctx.startActivity(intent);
                });

                return new CharacterViewHolder(characters);
            }
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        switch (getItemViewType(position)){
            case 0: {
                return; // стилизация
            }
            default:{
                position--;
                String nameText = models.get(position).getName();
                String rarityText = models.get(position).getRarity();

                ((CharacterViewHolder) holder).name.setText(nameText);
                ((CharacterViewHolder) holder).rarity.setText(rarityText);

                System.out.println(models.get(position).getIco());

                Picasso.get()
                        .load(String.format("https://sushicat.pp.ua/api%s", models.get(position).getIco()))
                        .into(((CharacterViewHolder) holder).ico);
            }
        }
    }

    @Override
    public int getItemCount() {
        if (models != null) {
            return models.size();
        }
        return 0;
    }

    public final class CharacterViewHolder extends RecyclerView.ViewHolder {

        private final ImageView ico;
        private final TextView name;
        private final TextView rarity;

        public CharacterViewHolder(@NonNull View itemView) {
            super(itemView);

            ico = itemView.findViewById(R.id.characters_model_logo);
            name = itemView.findViewById(R.id.model_characters_title);
            rarity = itemView.findViewById(R.id.model_characters_star);
        }
    }



}
