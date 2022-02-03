package com.example.genshin.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.adapters.DictionaryAdapter;
import com.example.genshin.R;
import com.example.genshin.models.DictionaryModel;

import java.util.ArrayList;
import java.util.List;

public class DictionaryFragment extends Fragment {

    RecyclerView dictionary_recycler;
    DictionaryAdapter dictionaryAdapter;
    List<DictionaryModel> dictionaryModels = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dictionary, container, false);

        dictionary_recycler = view.findViewById(R.id.dictionary_recycler);
        dictionaryAdapter = new DictionaryAdapter(getContext(), dictionaryModels);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        dictionary_recycler.setLayoutManager(layoutManager);
        dictionary_recycler.setAdapter(dictionaryAdapter);

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dictionaryModels.add(new DictionaryModel(1, "aba", "[prep] before", ""));
        dictionaryModels.add(new DictionaryModel(2, "beru", "[v] talk; speak", "Japanese: しゃべる(shyaberu) speak"));
        dictionaryModels.add(new DictionaryModel(3, "biat", "[int] Damn it [v] fuck", "Russian: блядь(blyad') (Damn it)"));
        dictionaryModels.add(new DictionaryModel(4, "boya *", "[n] colour\n· celi boya red · unu boya yellow · gusha boya green · lata boya blue · nini boya white · nunu/sama boya black", "Turkish: boya (paint; dye)"));
        dictionaryModels.add(new DictionaryModel(5, "buka", "[n] belly", ""));
    }
}