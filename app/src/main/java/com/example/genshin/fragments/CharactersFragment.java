package com.example.genshin.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.GenshinApp;
import com.example.adapters.CharacterAdapter;
import com.example.data.remote.characters.CharacterEntry;
import com.example.data.remote.characters.CharactersResponse;
import com.example.genshin.MainActivity;
import com.example.genshin.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CharactersFragment extends Fragment {

    private RecyclerView characters_recycler;
    private CharacterAdapter charactersAdapter;
    private List<CharacterEntry> menuCharacters = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_characters, container, false);

        characters_recycler = view.findViewById(R.id.characters_recycler);
        charactersAdapter = new CharacterAdapter(getContext(), (MainActivity) getActivity(), menuCharacters);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        characters_recycler.setLayoutManager(layoutManager);
        characters_recycler.setAdapter(charactersAdapter);

        ((GenshinApp) getActivity().getApplication()).characters.getCharacters().enqueue(new Callback<CharactersResponse>() {
            @Override
            public void onResponse(Call<CharactersResponse> call, Response<CharactersResponse> response) {
                if(response.code() == 200 && response.body() != null) {
                    charactersAdapter.setListCharactersModels(response.body().entries);
                }
            }

            @Override
            public void onFailure(Call<CharactersResponse> call, Throwable t) {
                menuCharacters.add(new CharacterEntry("1", "Проблемы с полдлючением", "Проверьте подключение к интернету", null));
            }
        });

        return view;
    }
}