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

import com.example.GenshinApp;
import com.example.adapters.CharacterAdapter;
import com.example.data.remote.characters.CharacterEntry;
import com.example.data.remote.characters.Characters;
import com.example.data.remote.characters.CharactersResponse;
import com.example.genshin.MainActivity;
import com.example.genshin.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CharactersFragment extends Fragment {

    private Context ctx;
    private MainActivity activity;
    private GenshinApp app;
    private RecyclerView characters_recycler;
    private CharacterAdapter charactersAdapter;
    private List<CharacterEntry> menuCharacters = new ArrayList<>();
    private SwipeRefreshLayout refresh;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_characters, container, false);

        // Получаем нужные объекты
        ctx = getContext();
        activity = (MainActivity) getActivity();
        app = (GenshinApp) (activity != null ? activity.getApplication() : null);

        characters_recycler = view.findViewById(R.id.characters_recycler);
        charactersAdapter = new CharacterAdapter(ctx, menuCharacters);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ctx, RecyclerView.VERTICAL, false);
        characters_recycler.setLayoutManager(layoutManager);
        characters_recycler.setAdapter(charactersAdapter);

        refresh = view.findViewById(R.id.refresh);

        refresh.setOnRefreshListener(() -> {
            new Thread(() -> {
                app.retrofit.create(Characters.class).getCharacters().enqueue(new Callback<CharactersResponse>() {
                    @Override
                    public void onResponse(Call<CharactersResponse> call, Response<CharactersResponse> response) {
                        if (response.code() == 200 && response.body() != null) {
                            view.findViewById(R.id.error).setVisibility(View.GONE);
                            view.findViewById(R.id.progress).setVisibility(View.GONE);
                            charactersAdapter.setListCharactersModels(response.body().entries);
                            refresh.setRefreshing(false);
                        }
                    }

                    @Override
                    public void onFailure(Call<CharactersResponse> call, Throwable t) {
                        view.findViewById(R.id.progress).setVisibility(View.GONE);
                        view.findViewById(R.id.error).setVisibility(View.VISIBLE);
                        refresh.setRefreshing(false);
                    }
                });
            }).start();
        });

        new Thread(() -> {
            app.retrofit.create(Characters.class).getCharacters().enqueue(new Callback<CharactersResponse>() {
                @Override
                public void onResponse(Call<CharactersResponse> call, Response<CharactersResponse> response) {
                    if (response.code() == 200 && response.body() != null) {
                        view.findViewById(R.id.progress).setVisibility(View.GONE);
                        charactersAdapter.setListCharactersModels(response.body().entries);
                    }
                }

                @Override
                public void onFailure(Call<CharactersResponse> call, Throwable t) {
                    view.findViewById(R.id.progress).setVisibility(View.GONE);
                    view.findViewById(R.id.error).setVisibility(View.VISIBLE);
                }
            });
        }).start();

        return view;
    }
}
