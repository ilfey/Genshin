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
import com.example.genshin.databinding.FragmentCharactersBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CharactersFragment extends Fragment {

    private FragmentCharactersBinding binding;
    private Context ctx;
    private MainActivity activity;
    private GenshinApp app;
    private CharacterAdapter charactersAdapter;
    private List<CharactersResponses.Character> menuCharacters = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCharactersBinding.inflate(inflater, container, false);

        View view = binding.getRoot();

        // Получаем нужные объекты
        ctx = getContext();
        activity = (MainActivity) getActivity();
        app = (GenshinApp) (activity != null ? activity.getApplication() : null);

        binding.refresh.setColorSchemeResources(R.color.primary);
        binding.progress.getIndeterminateDrawable().setColorFilter(0xFF4F46E5, android.graphics.PorterDuff.Mode.MULTIPLY);
        charactersAdapter = new CharacterAdapter(ctx, activity, menuCharacters);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ctx, RecyclerView.VERTICAL, false);

//        RecyclerViewPreloader<ContactsContract.CommonDataKinds.Photo> preloader = new RecyclerViewPreloader<>(Glide.with(this), modelProvider, sizeProvider, 10);

//        characters_recycler.addOnScrollListener(preloader);
        binding.charactersRecycler.setLayoutManager(layoutManager);
        binding.charactersRecycler.setAdapter(charactersAdapter);

        app.hasConnection();

        binding.refresh.setOnRefreshListener(() -> {
            app.retrofit.create(Characters.class).getCharacters().enqueue(new Callback<List<CharactersResponses.Character>>() {
                @Override
                public void onResponse(Call<List<CharactersResponses.Character>> call, Response<List<CharactersResponses.Character>> response) {
                    switch (response.code()) {
                        case 200: {
                            binding.error.setVisibility(View.GONE);
                            app.characters = response.body();
                            charactersAdapter.setListCharactersModels(app.characters);
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
                public void onFailure(Call<List<CharactersResponses.Character>> call, Throwable t) {
                    binding.progress.setVisibility(View.GONE);
                    binding.error.setVisibility(View.VISIBLE);
                    binding.refresh.setRefreshing(false);
                }
            });
        });

        charactersAdapter.setListCharactersModels(app.characters);

        if (!app.hasConnection()) {
            binding.error.setVisibility(View.VISIBLE);
        } else {
            binding.progress.setVisibility(View.VISIBLE);
            app.retrofit.create(Characters.class).getCharacters().enqueue(new Callback<List<CharactersResponses.Character>>() {
                @Override
                public void onResponse(Call<List<CharactersResponses.Character>> call, Response<List<CharactersResponses.Character>> response) {
                    switch (response.code()) {
                        case 200: {
                            binding.error.setVisibility(View.GONE);
                            binding.progress.setVisibility(View.GONE);
                            app.characters = response.body();
                            charactersAdapter.setListCharactersModels(app.characters);
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
                public void onFailure(Call<List<CharactersResponses.Character>> call, Throwable t) {
                    binding.progress.setVisibility(View.GONE);
                    binding.error.setVisibility(View.VISIBLE);
                    binding.refresh.setRefreshing(false);
                }
            });
        }

        return view;
    }
}