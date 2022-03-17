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
import com.example.adapters.CharacterAdapter;
import com.example.data.remotely.characters.Characters;
import com.example.data.remotely.characters.CharactersResponses;
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
    private List<CharactersResponses.Character> menuCharacters = new ArrayList<>();
    private SwipeRefreshLayout refresh;
    private ProgressBar progress;
    private TextView error;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_characters, container, false);

        // Получаем нужные объекты
        ctx = getContext();
        activity = (MainActivity) getActivity();
        app = (GenshinApp) (activity != null ? activity.getApplication() : null);

        refresh = view.findViewById(R.id.refresh);
        refresh.setColorSchemeResources(R.color.primary);
        error = view.findViewById(R.id.error);
        progress = view.findViewById(R.id.progress);
        progress.getIndeterminateDrawable().setColorFilter(0xFF4F46E5, android.graphics.PorterDuff.Mode.MULTIPLY);
        characters_recycler = view.findViewById(R.id.characters_recycler);
        charactersAdapter = new CharacterAdapter(ctx, activity, menuCharacters);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ctx, RecyclerView.VERTICAL, false);

//        RecyclerViewPreloader<ContactsContract.CommonDataKinds.Photo> preloader = new RecyclerViewPreloader<>(Glide.with(this), modelProvider, sizeProvider, 10);

//        characters_recycler.addOnScrollListener(preloader);
        characters_recycler.setLayoutManager(layoutManager);
        characters_recycler.setAdapter(charactersAdapter);

        app.hasConnection();

        refresh.setOnRefreshListener(() -> {
            new Thread(() -> {
                app.retrofit.create(Characters.class).getCharacters().enqueue(new Callback<List<CharactersResponses.Character>>() {
                    @Override
                    public void onResponse(Call<List<CharactersResponses.Character>> call, Response<List<CharactersResponses.Character>> response) {
                        if (response.code() == 200 && response.body() != null) {
                            error.setVisibility(View.GONE);
                            progress.setVisibility(View.GONE);
                            app.characters = response.body();
                            charactersAdapter.setListCharactersModels(app.characters);
                            refresh.setRefreshing(false);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<CharactersResponses.Character>> call, Throwable t) {
                        progress.setVisibility(View.GONE);
                        error.setVisibility(View.VISIBLE);
                        refresh.setRefreshing(false);
                    }
                });
            }).start();
        });

        charactersAdapter.setListCharactersModels(app.characters);

        if (!app.hasConnection()) {
            error.setVisibility(View.VISIBLE);
        } else {
            progress.setVisibility(View.VISIBLE);
            new Thread(() -> {
                app.retrofit.create(Characters.class).getCharacters().enqueue(new Callback<List<CharactersResponses.Character>>() {
                    @Override
                    public void onResponse(Call<List<CharactersResponses.Character>> call, Response<List<CharactersResponses.Character>> response) {
                        if (response.code() == 200 && response.body() != null) {
                            error.setVisibility(View.GONE);
                            progress.setVisibility(View.GONE);
                            app.characters = response.body();
                            charactersAdapter.setListCharactersModels(app.characters);
                            refresh.setRefreshing(false);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<CharactersResponses.Character>> call, Throwable t) {
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