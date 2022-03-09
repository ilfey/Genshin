package com.example.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.data.remotely.gacha.GachaEntry;
import com.example.genshin.MainActivity;
import com.example.genshin.R;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class GachaAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Context ctx;
    private List<GachaEntry> models;
    private MainActivity activity;
    private Picasso picasso;

    public GachaAdapter(Context ctx, MainActivity activity, List<GachaEntry> models) {
        this.ctx = ctx;
        this.models = models;
        this.activity = activity;

        HttpLoggingInterceptor logs = new HttpLoggingInterceptor();
        logs.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logs)
                .connectTimeout(10, TimeUnit.MILLISECONDS)
                .build();

        picasso = new Picasso.Builder(ctx).loggingEnabled(true).indicatorsEnabled(true).build();
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
    public void setListGachaModels(List<GachaEntry> listGachaModels) {
        this.models = listGachaModels;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        switch (viewType){
            case 0: {
                View view = LayoutInflater.from(ctx).inflate(R.layout.sort, parent, false);
                Spinner sort = view.findViewById(R.id.sort);

                ArrayAdapter<?> adapter = ArrayAdapter.createFromResource(ctx, R.array.sort_by, android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                // Создаем слушатель событий
                sort.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        String[] items = ctx.getResources().getStringArray(R.array.sort_by);
//                        activity.showDialog("Вы выбрали", items[position]);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                // Устанавливаем адаптер
                sort.setAdapter(adapter);

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
                String starText = models.get(position).getCh5star();
                String star1Text = models.get(position).getCh4star1();
                String star2Text = models.get(position).getCh4star2();
                String star3Text = models.get(position).getCh4star3();

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

                picasso.with(ctx)
                        .load("https://sushicat.pp.ua/api" + models.get(position).getImg())
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

            icon = (ImageView) itemView.findViewById(R.id.model_gacha_icon);
            title = (TextView) itemView.findViewById(R.id.model_gacha_title);
            star = (TextView) itemView.findViewById(R.id.model_gacha_star);
            star1 = (TextView) itemView.findViewById(R.id.model_gacha_star1);
            star2 = (TextView) itemView.findViewById(R.id.model_gacha_star2);
            star3 = (TextView) itemView.findViewById(R.id.model_gacha_star3);
            starImage = (ImageView) itemView.findViewById(R.id.model_gacha_star_image);
            star1Image = (ImageView) itemView.findViewById(R.id.model_gacha_star1_image);
            star2Image = (ImageView) itemView.findViewById(R.id.model_gacha_star2_image);
            star3Image = (ImageView) itemView.findViewById(R.id.model_gacha_star3_image);
        }
    }

    public final class SortViewHolder extends RecyclerView.ViewHolder{
        Spinner sort;

        public SortViewHolder(@NonNull View itemView) {
            super(itemView);

            sort = itemView.findViewById(R.id.sort);
        }
    }
}
