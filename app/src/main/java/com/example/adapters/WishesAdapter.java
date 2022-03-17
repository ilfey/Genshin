package com.example.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.data.remotely.wishes.WishesResponses;
import com.example.genshin.MainActivity;
import com.example.genshin.R;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;


public class WishesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Context ctx;
    private List<WishesResponses.Wish> models;
    private MainActivity activity;

    public WishesAdapter(Context ctx, MainActivity activity, List<WishesResponses.Wish> models) {
        this.ctx = ctx;
        this.models = models;
        this.activity = activity;
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0){
            return 0;
        } else {
            return 1;
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setListGachaModels(List<WishesResponses.Wish> listGachaModels) {
        this.models = listGachaModels;
        notifyDataSetChanged();
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

                return new SortViewHolder(view);
            }
            default:{
                View view = LayoutInflater.from(ctx).inflate(R.layout.model_gacha, parent, false);
                return new GachaViewHolder(view);
            }
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)){
            case 0: {
                return;
            }
            default: {
                position--;

                String titleText = models.get(position).getName();
                String starText = String.valueOf(models.get(position).getRate_5());
                String star1Text = String.valueOf(models.get(position).getRate_5());
//                String star2Text = models.get(position).getCh4star2();
//                String star3Text = models.get(position).getCh4star3();
                String star2Text = null;
                String star3Text = null;

                if (starText == null) {
                    ((GachaViewHolder)holder).star.setVisibility(View.GONE);
                    ((GachaViewHolder)holder).starImage.setVisibility(View.GONE);
                }
                if (star1Text == null) {
                    ((GachaViewHolder)holder).star1.setVisibility(View.GONE);
                    ((GachaViewHolder)holder).star1Image.setVisibility(View.GONE);
                }
                if (star2Text == null) {
                    ((GachaViewHolder)holder).star2.setVisibility(View.GONE);
                    ((GachaViewHolder)holder).star2Image.setVisibility(View.GONE);
                }
                if (star3Text == null) {
                    ((GachaViewHolder)holder).star3.setVisibility(View.GONE);
                    ((GachaViewHolder)holder).star3Image.setVisibility(View.GONE);
                }

                Picasso.with(ctx)
                        .load("https://sushicat.pp.ua/api" + models.get(position).getPoster())
                        .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                        .networkPolicy(NetworkPolicy.NO_CACHE, NetworkPolicy.NO_STORE)
                        .into(((GachaViewHolder)holder).icon);

                ((GachaViewHolder)holder).title.setText(titleText);
                ((GachaViewHolder)holder).star.setText(starText);
                ((GachaViewHolder)holder).star1.setText(star1Text);
                ((GachaViewHolder)holder).star2.setText(star2Text);
                ((GachaViewHolder)holder).star3.setText(star3Text);
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

    public final class GachaViewHolder extends RecyclerView.ViewHolder {

        ImageView icon;
        TextView title;
        TextView star;
        TextView star1;
        TextView star2;
        TextView star3;
        ImageView starImage;
        ImageView star1Image;
        ImageView star2Image;
        ImageView star3Image;

        public GachaViewHolder(@NonNull View itemView) {
            super(itemView);

            icon = itemView.findViewById(R.id.model_gacha_icon);
            title = itemView.findViewById(R.id.model_gacha_title);
            star = itemView.findViewById(R.id.model_gacha_star);
            star1 = itemView.findViewById(R.id.model_gacha_star1);
            star2 = itemView.findViewById(R.id.model_gacha_star2);
            star3 = itemView.findViewById(R.id.model_gacha_star3);
            starImage = itemView.findViewById(R.id.model_gacha_star_image);
            star1Image = itemView.findViewById(R.id.model_gacha_star1_image);
            star2Image = itemView.findViewById(R.id.model_gacha_star2_image);
            star3Image = itemView.findViewById(R.id.model_gacha_star3_image);
        }
    }

    public final class SortViewHolder extends RecyclerView.ViewHolder{
        Button sort;

        public SortViewHolder(@NonNull View itemView) {
            super(itemView);

            sort = itemView.findViewById(R.id.sort);
        }
    }
}
