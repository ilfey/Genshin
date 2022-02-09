package com.example.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.data.remote.characters.CharacterEntry;
import com.example.genshin.CharacterActivity;
import com.example.genshin.MainActivity;
import com.example.genshin.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder> {

    private Context ctx;
    private List<CharacterEntry> models;
    private MainActivity activity;

    public CharacterAdapter(Context ctx, MainActivity activity, List<CharacterEntry> models) {
        this.ctx = ctx;
        this.models = models;
        this.activity = activity;
    }

    public void setListCharactersModels(List<CharacterEntry> listCharactersModels){
        this.models = listCharactersModels;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {



        View characters = LayoutInflater.from(ctx).inflate(R.layout.model_characters, parent, false);
        characters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ctx, CharacterActivity.class);
                i.addFlags(i.FLAG_ACTIVITY_NO_ANIMATION);
                i.putExtra("Theme", activity.CURRENT_THEME);
                i.putExtra("URL", "url");
                ctx.startActivity(i);
            }
        });

        return new CharacterViewHolder(characters);
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterViewHolder holder, int position) {
        holder.name.setText(models.get(position).getName());
        holder.rarity.setText(models.get(position).getRarity());

        Picasso.with(ctx)
                .load("https://sushicat.pp.ua/api" + models.get(position).getIco())
                .into(holder.ico);
    }

    @Override
    public int getItemCount() {
        if(models != null){
            return models.size();
        }
        return 0;
    }

    public final class CharacterViewHolder extends RecyclerView.ViewHolder {

        ImageView ico;
        TextView name;
        TextView rarity;

        public CharacterViewHolder(@NonNull View itemView) {
            super(itemView);

            ico = (ImageView) itemView.findViewById(R.id.characters_model_logo);
            name = (TextView) itemView.findViewById(R.id.model_characters_title);
            rarity = (TextView) itemView.findViewById(R.id.model_characters_star);
        }
    }
}
