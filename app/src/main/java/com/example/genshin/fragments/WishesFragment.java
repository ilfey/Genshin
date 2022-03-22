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
import com.example.adapters.WishesAdapter;
import com.example.data.remotely.wishes.Wishes;
import com.example.data.remotely.wishes.WishesResponses;
import com.example.genshin.MainActivity;
import com.example.genshin.R;
import com.example.genshin.databinding.FragmentCharactersBinding;
import com.example.genshin.databinding.FragmentWishesBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WishesFragment extends Fragment {


    private FragmentWishesBinding binding;
    private Context ctx;
    private MainActivity activity;
    private GenshinApp app;
    private RecyclerView gacha_recycler;
    private WishesAdapter wishesAdapter;
    private List<WishesResponses.Wish> models = new ArrayList<>();
    private SwipeRefreshLayout refresh;
    private ProgressBar progress;
    private TextView error;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentWishesBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        // Получаем нужные объекты
        ctx = getContext();
        activity = (MainActivity) getActivity();
        app = (GenshinApp) (activity != null ? activity.getApplication() : null);

        binding.refresh.setColorSchemeResources(R.color.primary);
        binding.progress.getIndeterminateDrawable().setColorFilter(0xFF4F46E5, android.graphics.PorterDuff.Mode.MULTIPLY);

        wishesAdapter = new WishesAdapter(ctx, activity, models);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ctx, RecyclerView.VERTICAL, false);
        binding.gachaRecycler.setLayoutManager(layoutManager);
        binding.gachaRecycler.setAdapter(wishesAdapter);

        binding.refresh.setOnRefreshListener(() -> {
            app.retrofit.create(Wishes.class).getWishes().enqueue(new Callback<List<WishesResponses.Wish>>() {
                @Override
                public void onResponse(Call<List<WishesResponses.Wish>> call, Response<List<WishesResponses.Wish>> response) {
                    switch (response.code()) {
                        case 200: {
                            binding.error.setVisibility(View.GONE);
                            app.gacha = response.body();
                            wishesAdapter.setListGachaModels(app.gacha);
                            binding.refresh.setRefreshing(false);
                            return;
                        }
                        case 404: {
                            binding.error.setVisibility(View.VISIBLE);
                            binding.refresh.setRefreshing(false);
                            return;
                        }
                        default: {
                            binding.error.setVisibility(View.VISIBLE);
                            binding.refresh.setRefreshing(false);
                        }
                    }
                }

                @Override
                public void onFailure(Call<List<WishesResponses.Wish>> call, Throwable t) {
                    binding.progress.setVisibility(View.GONE);
                    binding.error.setVisibility(View.VISIBLE);
                    binding.refresh.setRefreshing(false);
                }
            });
        });

        wishesAdapter.setListGachaModels(app.gacha);

        if (!app.connection) {
            view.findViewById(R.id.error).setVisibility(View.VISIBLE);
        } else {
            view.findViewById(R.id.progress).setVisibility(View.VISIBLE);
            app.retrofit.create(Wishes.class).getWishes().enqueue(new Callback<List<WishesResponses.Wish>>() {
                @Override
                public void onResponse(Call<List<WishesResponses.Wish>> call, Response<List<WishesResponses.Wish>> response) {
                    switch (response.code()) {
                        case 200: {
                            binding.error.setVisibility(View.GONE);
                            binding.progress.setVisibility(View.GONE);
                            app.gacha = response.body();
                            wishesAdapter.setListGachaModels(app.gacha);
                            return;
                        }
                        case 404: {
                            binding.error.setVisibility(View.VISIBLE);
                            binding.progress.setVisibility(View.GONE);
                            return;
                        }
                        default: {
                            binding.error.setVisibility(View.VISIBLE);
                            binding.progress.setVisibility(View.GONE);
                        }
                    }
                }

                @Override
                public void onFailure(Call<List<WishesResponses.Wish>> call, Throwable t) {
                    binding.progress.setVisibility(View.GONE);
                    binding.error.setVisibility(View.VISIBLE);
                    binding.refresh.setRefreshing(false);
                }
            });
        }

        return view;
    }
}