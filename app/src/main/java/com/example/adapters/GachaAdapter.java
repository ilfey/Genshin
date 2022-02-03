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
import com.example.genshin.models.GachaModel;

import java.util.List;

public class GachaAdapter extends RecyclerView.Adapter<GachaAdapter.GachaViewHolder>{

    Context ctx;
    private final List<GachaModel> models;

    public GachaAdapter(Context ctx, List<GachaModel> models) {
        this.ctx = ctx;
        this.models = models;
    }

    @NonNull
    @Override
    public GachaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.model_gacha, parent, false);
        return new GachaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GachaViewHolder holder, int position) {
        holder.icon.setImageResource(models.get(position).getIcon());
        holder.title.setText(models.get(position).getTitle());
        holder.skills.setText(models.get(position).getSkills());
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public final class GachaViewHolder extends RecyclerView.ViewHolder {
        ImageView icon;
        TextView title;
        TextView skills;

        public GachaViewHolder(@NonNull View itemView) {
            super(itemView);

            icon = (ImageView) itemView.findViewById(R.id.model_gacha_icon);
            title = (TextView) itemView.findViewById(R.id.model_gacha_title);
            skills = (TextView) itemView.findViewById(R.id.model_gacha_skills);
        }
    }
}
