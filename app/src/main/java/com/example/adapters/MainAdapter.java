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
import com.example.genshin.models.MainModel;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {

	private Context ctx;
	private List<MainModel> models;

	public MainAdapter(Context ctx, List<MainModel> models) {
		this.ctx = ctx;
		this.models = models;
	}

	public void setListMainModels(List<MainModel> listMainModels) {
		this.models = listMainModels;
		notifyDataSetChanged();
	}

	@NonNull
	@Override
	public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View mainItems = LayoutInflater.from(ctx).inflate(R.layout.model_main, parent, false);

		return new MainViewHolder(mainItems);
	}

	@Override
	public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
		holder.icon.setImageResource(models.get(position).getIcon());
		holder.title.setText(models.get(position).getTitle());
		holder.itemView.setOnClickListener(models.get(position).getOnClick());
	}

	@Override
	public int getItemCount() {
		if (models != null) {
			return models.size();
		}
		return 0;
	}

	public final class MainViewHolder extends RecyclerView.ViewHolder {
		ImageView icon;
		TextView title;

		public MainViewHolder(@NonNull View itemView) {
			super(itemView);

			icon = (ImageView) itemView.findViewById(R.id.model_main_logo);
			title = (TextView) itemView.findViewById(R.id.model_main_title);
		}
	}
}
