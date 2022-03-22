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
import com.example.adapters.DictionaryAdapter;
import com.example.data.remotely.dictionary.Dictionary;
import com.example.data.remotely.dictionary.DictionaryResponses;
import com.example.genshin.MainActivity;
import com.example.genshin.R;
import com.example.genshin.databinding.FragmentDictionaryBinding;
import com.example.genshin.databinding.FragmentWishesBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DictionaryFragment extends Fragment {

    private FragmentDictionaryBinding binding;
    private Context ctx;
    private MainActivity activity;
    private GenshinApp app;
    private RecyclerView dictionary_recycler;
    private DictionaryAdapter dictionaryAdapter;
    private List<DictionaryResponses.Word> dictionaryModels = new ArrayList<>();
    private SwipeRefreshLayout refresh;
    private ProgressBar progress;
    private TextView error;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDictionaryBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        // Получаем нужные объекты
        ctx = getContext();
        activity = (MainActivity) getActivity();
        app = (GenshinApp) (activity != null ? activity.getApplication() : null);

        app.hasConnection();

        binding.refresh.setColorSchemeResources(R.color.primary);
        binding.progress.getIndeterminateDrawable().setColorFilter(0xFF4F46E5, android.graphics.PorterDuff.Mode.MULTIPLY);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ctx, RecyclerView.VERTICAL, false);
        binding.dictionaryRecycler.setLayoutManager(layoutManager);

        dictionaryAdapter = new DictionaryAdapter(ctx, dictionaryModels);
        binding.dictionaryRecycler.setAdapter(dictionaryAdapter);
//        dictionary_recycler.setHasFixedSize(true);
        binding.refresh.setOnRefreshListener(() -> {
            new Thread(() -> {
                app.retrofit.create(Dictionary.class).getDictionary().enqueue(new Callback<List<DictionaryResponses.Word>>() {
                    @Override
                    public void onResponse(Call<List<DictionaryResponses.Word>> call, Response<List<DictionaryResponses.Word>> response) {
                        switch (response.code()) {
                            case 200: {
                                binding.error.setVisibility(View.GONE);
                                app.dictionary = response.body();
                                dictionaryAdapter.setListDictionaryModels(app.dictionary);
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
                    public void onFailure(Call<List<DictionaryResponses.Word>> call, Throwable t) {
                        progress.setVisibility(View.GONE);
                        error.setVisibility(View.VISIBLE);
                        refresh.setRefreshing(false);
                    }
                });
            }).start();
        });

        dictionaryAdapter.setListDictionaryModels(app.dictionary);

        if (!app.hasConnection()) {
            binding.error.setVisibility(View.VISIBLE);
        } else {
            binding.progress.setVisibility(View.VISIBLE);
            new Thread(() -> {
                app.retrofit.create(Dictionary.class).getDictionary().enqueue(new Callback<List<DictionaryResponses.Word>>() {
                    @Override
                    public void onResponse(Call<List<DictionaryResponses.Word>> call, Response<List<DictionaryResponses.Word>> response) {
                        switch (response.code()) {
                            case 200: {
                                binding.error.setVisibility(View.GONE);
                                binding.progress.setVisibility(View.GONE);
                                app.dictionary = response.body();
                                dictionaryAdapter.setListDictionaryModels(app.dictionary);
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
                    public void onFailure(Call<List<DictionaryResponses.Word>> call, Throwable t) {
                        binding.progress.setVisibility(View.GONE);
                        binding.error.setVisibility(View.VISIBLE);
                    }
                });
            }).start();
        }
        return view;
    }
}