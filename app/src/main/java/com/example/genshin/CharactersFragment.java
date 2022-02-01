package com.example.genshin;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.adapters.CharacterAdapter;
import com.example.models.MenuCharacter;

import java.util.ArrayList;
import java.util.List;

public class CharactersFragment extends Fragment {

    RecyclerView characters_recycler;
    CharacterAdapter charactersAdapter;
    List<MenuCharacter> menuCharacters = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_characters, container, false);

        characters_recycler = view.findViewById(R.id.characters_recycler);
        charactersAdapter = new CharacterAdapter(getContext(), menuCharacters);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        characters_recycler.setLayoutManager(layoutManager);
        characters_recycler.setAdapter(charactersAdapter);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        menuCharacters.add(new MenuCharacter(1, R.mipmap.tartaglia, "Тарталия", "5*"));
        menuCharacters.add(new MenuCharacter(2, R.mipmap.tartaglia, "Ке Цин", "5*"));
        menuCharacters.add(new MenuCharacter(3, R.mipmap.tartaglia, "Чжун Ли", "5*"));
        menuCharacters.add(new MenuCharacter(4, R.mipmap.tartaglia, "Гань Юй", "5*"));
        menuCharacters.add(new MenuCharacter(5, R.mipmap.tartaglia, "Венти", "5*"));
        menuCharacters.add(new MenuCharacter(6, R.mipmap.tartaglia, "Сяо", "5*"));
        menuCharacters.add(new MenuCharacter(7, R.mipmap.tartaglia, "Аяка", "5*"));
        menuCharacters.add(new MenuCharacter(8, R.mipmap.tartaglia, "Ци Ци", "5*"));
        menuCharacters.add(new MenuCharacter(9, R.mipmap.tartaglia, "Дилюк", "5*"));
        menuCharacters.add(new MenuCharacter(10, R.mipmap.tartaglia, "Мона", "5*"));
        menuCharacters.add(new MenuCharacter(11, R.mipmap.tartaglia, "Джин", "5*"));
        menuCharacters.add(new MenuCharacter(12, R.mipmap.tartaglia, "Кли", "5*"));
        menuCharacters.add(new MenuCharacter(12, R.mipmap.tartaglia, "Фишиль", "4*"));

    }
}