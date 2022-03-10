package com.example.adapters;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.data.remotely.characters.CharacterEntry;
import com.example.dialogs.SortDialog;
import com.example.genshin.CharacterActivity;
import com.example.genshin.MainActivity;
import com.example.genshin.R;

import java.util.List;

import okhttp3.logging.HttpLoggingInterceptor;


public class CharacterAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Context ctx;
    private List<CharacterEntry> models;
    private MainActivity activity;
    private List<CharacterEntry> data;

    public CharacterAdapter(Context ctx, MainActivity activity, List<CharacterEntry> models) {
        this.ctx = ctx;
        this.activity = activity;
        this.models = models;

        HttpLoggingInterceptor log = new HttpLoggingInterceptor();
        log.level(HttpLoggingInterceptor.Level.BODY);
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
                View view = LayoutInflater.from(ctx).inflate(R.layout.sort, parent, false);
                Button sort = view.findViewById(R.id.sort);

                sort.setOnClickListener(view1 -> {
                    activity.showSortDialog();
                });

                return new CharacterViewHolder(view);
            }
            default: {
                View characters = LayoutInflater.from(ctx).inflate(R.layout.model_characters, parent, false);

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

                Glide.with(ctx)
                        .load(String.format("https://sushicat.pp.ua/api%s", models.get(position).getIco()))
                        .error(R.drawable.star)
                        .into(((CharacterViewHolder) holder).ico);

                String nameText = models.get(position).getName();
                String rarityText = models.get(position).getRarity();

                ((CharacterViewHolder) holder).name.setText(nameText);
                ((CharacterViewHolder) holder).rarity.setText(rarityText);

                int finalPosition = position;
                holder.itemView.setOnClickListener(view -> {
                    Intent intent = new Intent(ctx, CharacterActivity.class);
                    intent.addFlags(intent.FLAG_ACTIVITY_NO_ANIMATION);
                    intent.putExtra("Theme", activity.CURRENT_THEME);
                    intent.putExtra("Position", finalPosition);

                    ctx.startActivity(intent);
                });

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
