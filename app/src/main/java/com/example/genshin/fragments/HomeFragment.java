package com.example.genshin.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.adapters.MainAdapter;
import com.example.genshin.MainActivity;
import com.example.genshin.R;
import com.example.genshin.models.MainModel;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private RecyclerView mainRecycler;
    private MainAdapter mainAdapter;
    private List<MainModel> models = new ArrayList<>();
    private MainActivity activity;
    private Context ctx;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        activity = (MainActivity) getActivity();
        ctx = getContext();
        mainRecycler = view.findViewById(R.id.main_recycler);
        mainAdapter = new MainAdapter(ctx, models);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);

        mainRecycler.setLayoutManager(layoutManager);
        mainRecycler.setAdapter(mainAdapter);

        models.add(new MainModel(R.mipmap.character, "Персонажи", view1 -> {
            activity.loadFragment(activity.whichFragment(R.id.nav_characters));
        }));
        models.add(new MainModel(R.mipmap.book, "Хиличурлский", view1 -> {
            activity.loadFragment(activity.whichFragment(R.id.nav_dictionary));
        }));
        models.add(new MainModel(R.mipmap.star, "Молитвы", view1 -> {
            activity.loadFragment(activity.whichFragment(R.id.nav_wishes));
        }));
        models.add(new MainModel(R.mipmap.bag, "Предметы", view1 -> {}));
        models.add(new MainModel(R.mipmap.question, "Полезное", view1 -> {}));
        models.add(new MainModel(R.mipmap.map, "Карта", view1 -> {}));
        models.add(new MainModel(R.mipmap.friends, "О проекте", view1 -> {
            activity.loadFragment(activity.whichFragment(R.id.fragment_about));
        }));

        return view;
    }
}