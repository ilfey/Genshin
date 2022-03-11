package com.example.adapters;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.data.remotely.dictionary.DictionaryEntry;
import com.example.genshin.R;
import com.example.listeners.TextChanged;

import org.w3c.dom.Text;

import java.util.List;

public class DictionaryAdapter extends RecyclerView.Adapter<DictionaryAdapter.DictionaryViewHolder> {

    private Context ctx;
    private List<DictionaryEntry> models;
    private View view;

    public DictionaryAdapter(Context ctx, List<DictionaryEntry> models) {
        this.ctx = ctx;
        this.models = models;
//        setHasStableIds(true);
    }

    public void setListDictionaryModels(List<DictionaryEntry> listDictionaryModels) {
        this.models = listDictionaryModels;
//        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DictionaryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(ctx).inflate(R.layout.model_dictionary, parent, false);
        TextView title = view.findViewById(R.id.model_dictionary_title);
        TextView content = view.findViewById(R.id.model_dictionary_content);
        TextView description = view.findViewById(R.id.model_dictionary_description);
        title.addTextChangedListener(new TextChanged(title));
        content.addTextChangedListener(new TextChanged(content));
        description.addTextChangedListener(new TextChanged(description));

        return new DictionaryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DictionaryViewHolder holder, int position) {
        final String worldText = models.get(position).getWord();
        final String contentText = models.get(position).getTranslate();
        final String subinfText = models.get(position).getSubinf();

        holder.word.setText(worldText);
        holder.content.setText(contentText);
        holder.subinf.setText(subinfText);
    }

    @Override
    public int getItemCount() {
        if (models != null) {
            return models.size();
        }
        return 0;
    }

    public final class DictionaryViewHolder extends RecyclerView.ViewHolder {

        private final TextView word;
        private final TextView content;
        private final TextView subinf;

        public DictionaryViewHolder(@NonNull View itemView) {
            super(itemView);

            word = itemView.findViewById(R.id.model_dictionary_title);
            content = itemView.findViewById(R.id.model_dictionary_content);
            subinf = itemView.findViewById(R.id.model_dictionary_description);
        }
    }
}
