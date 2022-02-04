package com.example.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.data.remote.characters.CharacterEntry;
import com.example.genshin.R;

import java.util.List;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder> {

    private Context ctx;
    private List<CharacterEntry> models;

    public CharacterAdapter(Context ctx, List<CharacterEntry> models) {
        this.ctx = ctx;
        this.models = models;
    }

    public void setListCharactersModels(List<CharacterEntry> listCharactersModels){
        this.models = listCharactersModels;
        notifyDataSetChanged();
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
        holder.rarity.setText(models.get(position).getRarity());

//        holder.ico.setImageResource(models.get(position).getIco());
        holder.ico.setImageResource(R.mipmap.tartaglia);  // FIX THIS!!!
//        holder.ico.setImageResource(models.get(position).getIco());
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
