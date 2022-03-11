package com.example.genshin.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.GenshinApp;
import com.example.adapters.DictionaryAdapter;
import com.example.data.remotely.dictionary.Dictionary;
import com.example.data.remotely.dictionary.DictionaryEntry;
import com.example.data.remotely.dictionary.DictionaryResponse;
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
    private ProgressBar progress;
    private TextView error;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_dictionary, container, false);

        // Получаем нужные объекты
        ctx = getContext();
        activity = (MainActivity) getActivity();
        app = (GenshinApp) (activity != null ? activity.getApplication() : null);

        app.hasConnection();

        refresh = view.findViewById(R.id.refresh);
        refresh.setColorSchemeResources(R.color.primary);
        error = view.findViewById(R.id.error);
        progress = view.findViewById(R.id.progress);
        progress.getIndeterminateDrawable().setColorFilter(0xFF4F46E5, android.graphics.PorterDuff.Mode.MULTIPLY);
        dictionary_recycler = view.findViewById(R.id.dictionary_recycler);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ctx, RecyclerView.VERTICAL, false);
        dictionary_recycler.setLayoutManager(layoutManager);

        dictionaryAdapter = new DictionaryAdapter(ctx, dictionaryModels);
        dictionary_recycler.setAdapter(dictionaryAdapter);
//        dictionary_recycler.setHasFixedSize(true);
        refresh.setOnRefreshListener(() -> {
            new Thread(() -> {
                load();
            }).start();
        });

//        dictionaryAdapter.setListDictionaryModels(app.dictionary);

        if (!app.hasConnection()) {
            view.findViewById(R.id.error).setVisibility(View.VISIBLE);
        } else {
            view.findViewById(R.id.progress).setVisibility(View.VISIBLE);
            new Thread(() -> {
                load();
            }).start();
        }
        return view;
    }

    void load(){
        app.retrofit.create(Dictionary.class).getDictionary().enqueue(new Callback<DictionaryResponse>() {
            @Override
            public void onResponse(Call<DictionaryResponse> call, Response<DictionaryResponse> response) {
                if (response.code() == 200 && response.body() != null) {
                    error.setVisibility(View.GONE);
                    progress.setVisibility(View.GONE);
                    app.dictionary = response.body().entries;
                    dictionaryAdapter.setListDictionaryModels(app.dictionary);
                    dictionaryAdapter.notifyDataSetChanged();
                    refresh.setRefreshing(false);
                }
            }

            @Override
            public void onFailure(Call<DictionaryResponse> call, Throwable t) {
                progress.setVisibility(View.GONE);
                error.setVisibility(View.VISIBLE);
                refresh.setRefreshing(false);
            }
        });
    }
}