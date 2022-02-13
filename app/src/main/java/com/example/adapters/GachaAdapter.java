package com.example.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.data.remote.gacha.GachaEntry;
import com.example.genshin.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class GachaAdapter extends RecyclerView.Adapter<GachaAdapter.GachaViewHolder> {

	private Context ctx;
	private List<GachaEntry> models;

	public GachaAdapter(Context ctx, List<GachaEntry> models) {
		this.ctx = ctx;
		this.models = models;
	}

	public void setListGachaModels(List<GachaEntry> listGachaModels) {
		this.models = listGachaModels;
		notifyDataSetChanged();
	}

	@NonNull
	@Override
	public GachaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(ctx).inflate(R.layout.model_gacha, parent, false);
		return new GachaViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull GachaViewHolder holder, int position) {

		Picasso.get()
				.load("https://sushicat.pp.ua/api" + models.get(position).getImg())
				.into(holder.icon);
		holder.title.setText(models.get(position).getName());
		holder.star.setText(models.get(position).getCh5star());
		holder.star1.setText(models.get(position).getCh4star1());
		holder.star2.setText(models.get(position).getCh4star2());
		holder.star3.setText(models.get(position).getCh4star3());
	}

	@Override
	public int getItemCount() {
		if (models != null) {
			return models.size();
		}
		return 0;
	}

	public final class GachaViewHolder extends RecyclerView.ViewHolder {

		ImageView icon;
		TextView title;
		TextView star;
		TextView star1;
		TextView star2;
		TextView star3;

		public GachaViewHolder(@NonNull View itemView) {
			super(itemView);

			icon = (ImageView) itemView.findViewById(R.id.model_gacha_icon);
			title = (TextView) itemView.findViewById(R.id.model_gacha_title);
			star = (TextView) itemView.findViewById(R.id.model_gacha_star);
			star1 = (TextView) itemView.findViewById(R.id.model_gacha_star1);
			star2 = (TextView) itemView.findViewById(R.id.model_gacha_star2);
			star3 = (TextView) itemView.findViewById(R.id.model_gacha_star3);
		}
	}
}
