package com.example.genshin.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.genshin.MainActivity;
import com.example.genshin.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainFragment extends Fragment {
    private MainActivity activity;
    private Context ctx;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        activity = (MainActivity) getActivity();
        ctx = getContext();

        // Получаем навбар
        BottomNavigationView navbar = view.findViewById(R.id.nav_bar);

        // Создаем и устанавливаем слушатель событий на навбар
        navbar.setOnNavigationItemSelectedListener(item -> {
            // Загружаем нужный фрагмент
            activity.loadFragment(activity.whichFragment(item.getItemId()));
            return true;
        });

        // Загружаем последний фрагмент
        activity.loadFragment(activity.whichFragment(activity.prefs.getInt("LastFragment", R.id.nav_main)));

        // Проверяем на первое открытие апк
        if (activity.prefs.getBoolean("Visited", true)) {
            // диалог при первом открытии
            activity.showDialog(getString(R.string.welcome), "Какое-то описание)");
            // Создаем запись в префсах о том, что апк уже открывалось ранее
            SharedPreferences.Editor editor = activity.prefs.edit();
            editor.putBoolean("Visited", false);
            editor.apply();
        }

        return view;
    }


}