package com.example.genshin;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.adapters.MainAdapter;
import com.example.models.MainModel;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment {

    RecyclerView mainRecycler;
    MainAdapter mainAdapter;
    List<MainModel> models = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        mainRecycler = view.findViewById(R.id.main_recycler);
        mainAdapter = new MainAdapter(getContext(), models);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);

        mainRecycler.setLayoutManager(layoutManager);
        mainRecycler.setAdapter(mainAdapter);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        models.add(new MainModel(1, R.mipmap.icon_ch, "Персонажи"));
        models.add(new MainModel(1, R.mipmap.icon_ch, "Хиличурлский"));
        models.add(new MainModel(1, R.mipmap.icon_ch, "Молитвы"));
        models.add(new MainModel(1, R.mipmap.icon_ch, "Предметы"));
        models.add(new MainModel(1, R.mipmap.icon_ch, "Полезное"));
        models.add(new MainModel(1, R.mipmap.icon_ch, "Карта"));
        models.add(new MainModel(1, R.mipmap.icon_ch, "О проекте"));
    }
}