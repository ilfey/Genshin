package com.example.genshin.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.GenshinApp;
import com.example.adapters.GachaAdapter;
import com.example.data.remote.gacha.GachaEntry;
import com.example.data.remote.gacha.GachaResponse;
import com.example.genshin.MainActivity;
import com.example.genshin.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GachaFragment extends Fragment {

    private Context ctx;
    private MainActivity activity;
    private GenshinApp app;
    private Spinner spinner;
    private RecyclerView gacha_recycler;
    private GachaAdapter gachaAdapter;
    private List<GachaEntry> models = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gacha, container, false);

        // Получаем нужные объекты
        ctx = getContext();
        activity = (MainActivity) getActivity();
        app = (GenshinApp) (activity != null ? activity.getApplication() : null);

        // Получаем элементы
        spinner = view.findViewById(R.id.sort_gacha);
        gacha_recycler = view.findViewById(R.id.gacha_recycler);

        ArrayAdapter<?> adapter = ArrayAdapter.createFromResource(ctx, R.array.sort_by, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Создаем слушатель событий
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] items = getResources().getStringArray(R.array.sort_by);
                activity.showDialog("Вы выбрали", items[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
        // Вызываем адаптер
        spinner.setAdapter(adapter);

        gachaAdapter = new GachaAdapter(ctx, models);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ctx, RecyclerView.VERTICAL, false);
        gacha_recycler.setLayoutManager(layoutManager);
        gacha_recycler.setAdapter(gachaAdapter);

        app.gacha.getGacha().enqueue(new Callback<GachaResponse>() {
            @Override
            public void onResponse(@NotNull Call<GachaResponse> call, @NotNull Response<GachaResponse> response) {
                if (response.code() == 200 && response.body() != null) {
                    gachaAdapter.setListGachaModels(response.body().entries);
                }
            }

            @Override
            public void onFailure(@NotNull Call<GachaResponse> call, Throwable t) {
                activity.showDialog("Ошибка!", "Нет подключения к интернету.");
            }
        });

        return view;
    }
}