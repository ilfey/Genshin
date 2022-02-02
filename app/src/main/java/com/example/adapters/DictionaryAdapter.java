package com.example.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.genshin.R;
import com.example.models.DictionaryModel;

import java.util.List;

public class DictionaryAdapter extends RecyclerView.Adapter<DictionaryAdapter.DictionaryViewHolder> {

    Context ctx;
    List<DictionaryModel> models;

    public DictionaryAdapter(Context ctx, List<DictionaryModel> models) {
        this.ctx = ctx;
        this.models = models;
    }

    @NonNull
    @Override
    public DictionaryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.model_dictionary, parent, false);

        return new DictionaryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DictionaryViewHolder holder, int position) {
        holder.title.setText(models.get(position).getTitle());
        holder.content.setText(models.get(position).getContent());
        holder.description.setText(models.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public final class DictionaryViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        TextView content;
        TextView description;

        public DictionaryViewHolder(@NonNull View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.model_dictionary_title);
            content = (TextView) itemView.findViewById(R.id.model_dictionary_content);
            description = (TextView) itemView.findViewById(R.id.model_dictionary_description);
        }
    }
}
