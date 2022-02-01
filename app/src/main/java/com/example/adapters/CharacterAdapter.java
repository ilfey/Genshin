package com.example.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.genshin.R;
import com.example.models.MenuCharacter;

import java.util.List;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder> {

    Context ctx;
    private final List<MenuCharacter> models;

    public CharacterAdapter(Context ctx, List<MenuCharacter> models) {
        this.ctx = ctx;
        this.models = models;
    }

    @NonNull
    @Override
    public CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View characters = LayoutInflater.from(ctx).inflate(R.layout.model_characters, parent, false);

        return new CharacterViewHolder(characters);
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterViewHolder holder, int position) {
        holder.name.setText(models.get(position).getName());
        holder.star.setText(models.get(position).getStar());
        holder.icon.setImageResource(models.get(position).getIcon());
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public final class CharacterViewHolder extends RecyclerView.ViewHolder {

        ImageView icon;
        TextView name;
        TextView star;

        public CharacterViewHolder(@NonNull View itemView) {
            super(itemView);

            icon = (ImageView) itemView.findViewById(R.id.characters_model_logo);
            name = (TextView) itemView.findViewById(R.id.model_characters_title);
            star = (TextView) itemView.findViewById(R.id.model_characters_star);
        }
    }
}
