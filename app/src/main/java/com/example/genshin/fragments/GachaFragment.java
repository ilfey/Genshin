package com.example.genshin.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.GenshinApp;
import com.example.adapters.GachaAdapter;
import com.example.data.remote.gacha.Gacha;
import com.example.data.remote.gacha.GachaEntry;
import com.example.data.remote.gacha.GachaResponse;
import com.example.genshin.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GachaFragment extends Fragment {

    private RecyclerView gacha_recycler;
    private GachaAdapter gachaAdapter;
    private List<GachaEntry> models = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gacha, container, false);

        gacha_recycler = view.findViewById(R.id.gacha_recycler);
        gachaAdapter = new GachaAdapter(getContext(), models);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        gacha_recycler.setLayoutManager(layoutManager);
        gacha_recycler.setAdapter(gachaAdapter);

        ((GenshinApp) getActivity().getApplication()).gacha.getGacha().enqueue(new Callback<GachaResponse>() {
            @Override
            public void onResponse(Call<GachaResponse> call, Response<GachaResponse> response) {
                if(response.code() == 200 && response.body() != null) {
                    gachaAdapter.setListGachaModels(response.body().entries);
                }
            }

            @Override
            public void onFailure(Call<GachaResponse> call, Throwable t) {
                models.add(new GachaEntry("1", "", "Проблемы с подключением", "Проверьте подключение к интернету", "Розария", "Ноэль", "Беннет"));
            }
        });

        return view;
    }
}