package com.example.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.data.remote.dictionary.DictionaryEntry;
import com.example.genshin.R;

import java.util.List;

public class DictionaryAdapter extends RecyclerView.Adapter<DictionaryAdapter.DictionaryViewHolder> {

	private Context ctx;
	private List<DictionaryEntry> models;

	public DictionaryAdapter(Context ctx, List<DictionaryEntry> models) {
		this.ctx = ctx;
		this.models = models;
	}

	public void setListDictionaryModels(List<DictionaryEntry> listDictionaryModels) {
		this.models = listDictionaryModels;
		notifyDataSetChanged();
	}

	@NonNull
	@Override
	public DictionaryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(ctx).inflate(R.layout.model_dictionary, parent, false);

		return new DictionaryViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull DictionaryViewHolder holder, int position) {
		holder.word.setText(models.get(position).getWord());
		holder.content.setText(models.get(position).getTranslate());
		holder.subinf.setText(models.get(position).getSubinf());
	}

	@Override
	public int getItemCount() {
		if (models != null) {
			return models.size();
		}
		return 0;
	}

	public final class DictionaryViewHolder extends RecyclerView.ViewHolder {

		TextView word;
		TextView content;
		TextView subinf;

		public DictionaryViewHolder(@NonNull View itemView) {
			super(itemView);

			word = (TextView) itemView.findViewById(R.id.model_dictionary_title);
			content = (TextView) itemView.findViewById(R.id.model_dictionary_content);
			subinf = (TextView) itemView.findViewById(R.id.model_dictionary_description);
		}
	}
}
