package com.example.genshin;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

public class GachaFragment extends Fragment {

//    List<String> ArraySort = new ArrayList<String>();

    public GachaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gacha, container, false);

        /*ArraySort.add("От новых");
        ArraySort.add("От старых");
        ArraySort.add("По алфавиту");
        ArraySort.add("С конца алфавита");*/

    }
}