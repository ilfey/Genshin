package com.example.genshin.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.GenshinApp;
import com.example.adapters.DictionaryAdapter;
import com.example.data.remote.dictionary.DictionaryEntry;
import com.example.data.remote.dictionary.DictionaryResponse;
import com.example.genshin.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DictionaryFragment extends Fragment {

    private RecyclerView dictionary_recycler;
    private DictionaryAdapter dictionaryAdapter;
    private List<DictionaryEntry> dictionaryModels = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dictionary, container, false);

        dictionary_recycler = view.findViewById(R.id.dictionary_recycler);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        dictionary_recycler.setLayoutManager(layoutManager);

        dictionaryAdapter = new DictionaryAdapter(getContext(), dictionaryModels);
        dictionary_recycler.setAdapter(dictionaryAdapter);

        ((GenshinApp) getActivity().getApplication()).dictionary.getDictionary().enqueue(new Callback<DictionaryResponse>() {
            @Override
            public void onResponse(@NonNull Call<DictionaryResponse> call, @NonNull Response<DictionaryResponse> response) {
                if(response.code() == 200 && response.body() != null) {
                    dictionaryAdapter.setListDictionaryModels(response.body().entries);
                }
            }

            @Override
            public void onFailure(@NonNull Call<DictionaryResponse> call, @NonNull Throwable t) {
                dictionaryModels.add(new DictionaryEntry("1", "Проблемы с полдлючением", "Проверьте подключение к интернету", ""));
            }
        });
        return view;
    }
}