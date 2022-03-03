package com.example.genshin.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.GenshinApp;
import com.example.adapters.GachaAdapter;
import com.example.data.remotely.gacha.Gacha;
import com.example.data.remotely.gacha.GachaEntry;
import com.example.data.remotely.gacha.GachaResponse;
import com.example.genshin.MainActivity;
import com.example.genshin.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WishesFragment extends Fragment {

    private Context ctx;
    private MainActivity activity;
    private GenshinApp app;
    private RecyclerView gacha_recycler;
    private GachaAdapter gachaAdapter;
    private List<GachaEntry> models = new ArrayList<>();
    private SwipeRefreshLayout refresh;
    private ProgressBar progress;
    private TextView error;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_wishes, container, false);

        // Получаем нужные объекты
        ctx = getContext();
        activity = (MainActivity) getActivity();
        app = (GenshinApp) (activity != null ? activity.getApplication() : null);

        // Получаем элементы
        refresh = view.findViewById(R.id.refresh);
        refresh.setColorSchemeResources(R.color.primary);
        error = view.findViewById(R.id.error);
        progress = view.findViewById(R.id.progress);
        progress.getIndeterminateDrawable().setColorFilter(0xFF4F46E5, android.graphics.PorterDuff.Mode.MULTIPLY);
        gacha_recycler = view.findViewById(R.id.gacha_recycler);
        refresh = view.findViewById(R.id.refresh);

        gachaAdapter = new GachaAdapter(ctx, activity, models);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ctx, RecyclerView.VERTICAL, false);
        gacha_recycler.setLayoutManager(layoutManager);
        gacha_recycler.setAdapter(gachaAdapter);

        refresh.setOnRefreshListener(() -> {
            new Thread(() -> {
                app.retrofit.create(Gacha.class).getGacha().enqueue(new Callback<GachaResponse>() {
                    @Override
                    public void onResponse(Call<GachaResponse> call, Response<GachaResponse> response) {
                        if (response.code() == 200 && response.body() != null) {
                            error.setVisibility(View.GONE);
                            progress.setVisibility(View.GONE);
                            app.gacha = response.body().entries;
                            gachaAdapter.setListGachaModels(app.gacha);
                            refresh.setRefreshing(false);
                        }
                    }

                    @Override
                    public void onFailure(Call<GachaResponse> call, Throwable t) {
                        progress.setVisibility(View.GONE);
                        error.setVisibility(View.VISIBLE);
                        refresh.setRefreshing(false);
                    }
                });
            }).start();
        });

        gachaAdapter.setListGachaModels(app.gacha);

        if (!app.connection) {
            view.findViewById(R.id.error).setVisibility(View.VISIBLE);
        } else {
            view.findViewById(R.id.progress).setVisibility(View.VISIBLE);
            new Thread(() -> {
                app.retrofit.create(Gacha.class).getGacha().enqueue(new Callback<GachaResponse>() {
                    @Override
                    public void onResponse(Call<GachaResponse> call, Response<GachaResponse> response) {
                        if (response.code() == 200 && response.body() != null) {
                            error.setVisibility(View.GONE);
                            progress.setVisibility(View.GONE);
                            app.gacha = response.body().entries;
                            gachaAdapter.setListGachaModels(app.gacha);
                            refresh.setRefreshing(false);
                        }
                    }

                    @Override
                    public void onFailure(Call<GachaResponse> call, Throwable t) {
                        progress.setVisibility(View.GONE);
                        error.setVisibility(View.VISIBLE);
                        refresh.setRefreshing(false);
                    }
                });
            }).start();
        }

        return view;
    }
}