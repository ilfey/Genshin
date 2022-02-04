package com.example.genshin.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.adapters.GachaAdapter;
import com.example.genshin.R;
import com.example.genshin.models.GachaModel;

import java.util.ArrayList;
import java.util.List;

public class GachaFragment extends Fragment {

    private RecyclerView gacha_recycler;
    private GachaAdapter gachaAdapter;
    private List<GachaModel> gachaModels = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gacha, container, false);

        gacha_recycler = view.findViewById(R.id.gacha_recycler);
        gachaAdapter = new GachaAdapter(getContext(), gachaModels);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        gacha_recycler.setLayoutManager(layoutManager);
        gacha_recycler.setAdapter(gachaAdapter);

        gachaModels.add(new GachaModel(1, R.mipmap.eola, "Эола", "Эола 5*\nРозария 4*\nНоэль 4*\nБеннет 4*"));
        gachaModels.add(new GachaModel(2, R.mipmap.eola, "Эола", "Эола 5*\nРозария 4*\nНоэль 4*\nБеннет 4*"));
        gachaModels.add(new GachaModel(3, R.mipmap.eola, "Эола", "Эола 5*\nРозария 4*\nНоэль 4*\nБеннет 4*"));

        return view;
    }
}