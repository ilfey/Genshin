package com.example.genshin.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.adapters.MainAdapter;
import com.example.genshin.R;
import com.example.genshin.models.MainModel;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment {

    private RecyclerView mainRecycler;
    private MainAdapter mainAdapter;
    private List<MainModel> models = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);

        mainRecycler = view.findViewById(R.id.main_recycler);
        mainAdapter = new MainAdapter(getContext(), models);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);

        mainRecycler.setLayoutManager(layoutManager);
        mainRecycler.setAdapter(mainAdapter);

        models.add(new MainModel(R.mipmap.character, "Персонажи"));
        models.add(new MainModel(R.mipmap.book, "Хиличурлский"));
        models.add(new MainModel(R.mipmap.star, "Молитвы"));
        models.add(new MainModel(R.mipmap.bag, "Предметы"));
        models.add(new MainModel(R.mipmap.question, "Полезное"));
        models.add(new MainModel(R.mipmap.map, "Карта"));
        models.add(new MainModel(R.mipmap.friends, "О проекте"));

        return view;
    }
}