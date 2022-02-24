package com.example.genshin.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.GenshinApp;
import com.example.adapters.DictionaryAdapter;
import com.example.data.remote.dictionary.Dictionary;
import com.example.data.remote.dictionary.DictionaryEntry;
import com.example.data.remote.dictionary.DictionaryResponse;
import com.example.genshin.MainActivity;
import com.example.genshin.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DictionaryFragment extends Fragment {

    private Context ctx;
    private MainActivity activity;
    private GenshinApp app;
    private RecyclerView dictionary_recycler;
    private DictionaryAdapter dictionaryAdapter;
    private List<DictionaryEntry> dictionaryModels = new ArrayList<>();
    private SwipeRefreshLayout refresh;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dictionary, container, false);

        // Получаем нужные объекты
        ctx = getContext();
        activity = (MainActivity) getActivity();
        app = (GenshinApp) (activity != null ? activity.getApplication() : null);

        dictionary_recycler = view.findViewById(R.id.dictionary_recycler);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ctx, RecyclerView.VERTICAL, false);
        dictionary_recycler.setLayoutManager(layoutManager);

        dictionaryAdapter = new DictionaryAdapter(ctx, dictionaryModels);
        dictionary_recycler.setAdapter(dictionaryAdapter);

        refresh = view.findViewById(R.id.refresh);

        refresh.setOnRefreshListener(() -> {
            new Thread(() -> {
                app.retrofit.create(Dictionary.class).getDictionary().enqueue(new Callback<DictionaryResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<DictionaryResponse> call, @NonNull Response<DictionaryResponse> response) {
                        if(response.code() == 200 && response.body() != null) {
                            view.findViewById(R.id.progress).setVisibility(View.GONE);
                            dictionaryAdapter.setListDictionaryModels(response.body().entries);
                            refresh.setRefreshing(false);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<DictionaryResponse> call, @NonNull Throwable t) {
                        view.findViewById(R.id.progress).setVisibility(View.GONE);
                        view.findViewById(R.id.error).setVisibility(View.VISIBLE);
                        refresh.setRefreshing(false);
                    }
                });
            }).start();
        });

        new Thread(() -> {
            app.retrofit.create(Dictionary.class).getDictionary().enqueue(new Callback<DictionaryResponse>() {
                @Override
                public void onResponse(@NonNull Call<DictionaryResponse> call, @NonNull Response<DictionaryResponse> response) {
                    if(response.code() == 200 && response.body() != null) {
                        view.findViewById(R.id.progress).setVisibility(View.GONE);
                        dictionaryAdapter.setListDictionaryModels(response.body().entries);
                    }
                }

                @Override
                public void onFailure(@NonNull Call<DictionaryResponse> call, @NonNull Throwable t) {
                    view.findViewById(R.id.progress).setVisibility(View.GONE);
                    view.findViewById(R.id.error).setVisibility(View.VISIBLE);
                }
            });
        }).start();

        return view;
    }
}