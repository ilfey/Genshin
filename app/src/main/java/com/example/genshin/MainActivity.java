package com.example.genshin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.genshin.fragments.AboutFragment;
import com.example.genshin.fragments.CharactersFragment;
import com.example.genshin.fragments.DictionaryFragment;
import com.example.genshin.fragments.GachaFragment;
import com.example.genshin.fragments.MainFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {
    public static String CURRENT_THEME;
    public static int CURRENT_FRAGMENT;
    public final String DARK = "Dark";
    public final String LIGHT = "Light";
    public SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // загрузка или создание преференсов
        prefs = getSharedPreferences("Preferences", Context.MODE_PRIVATE);

        // установка темы
        setFinalTheme();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // инициализация навбара
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.nav_bar);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        // загрузка фрагмента
        int lastFragment = prefs.getInt("LastFragment", R.id.main);
        loadFragment(whitchFragment(lastFragment));

        // проверка на певое открытие
        if (!prefs.getBoolean("Visited", false)) {
            // выводим нужную активность
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("Visited", true);
            editor.apply();
        }
    }

    private Fragment whitchFragment(int id) {
        switch (id) {
            case R.id.main:
                CURRENT_FRAGMENT = R.id.main;
                return new MainFragment();
            case R.id.characters:
                CURRENT_FRAGMENT = R.id.characters;
                return new CharactersFragment();
            case R.id.dictionary:
                CURRENT_FRAGMENT = R.id.dictionary;
                return new DictionaryFragment();
            case R.id.gacha:
                CURRENT_FRAGMENT = R.id.gacha;
                return new GachaFragment();
            case R.id.about:
                CURRENT_FRAGMENT = R.id.about;
                return new AboutFragment();
            default:
                CURRENT_FRAGMENT = R.id.main;
                return new MainFragment();
        }
    }

    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
        loadFragment(whitchFragment(item.getItemId()));
        return true;
    };

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void setFinalTheme() {
        switch (prefs.getString("Theme", LIGHT)) {
            case DARK: {
                setCustomTheme(R.style.Dark, DARK);
                return;
            }
            case LIGHT: {
                setCustomTheme(R.style.Light, LIGHT);
                return;
            }
            default:
                setCustomTheme(R.style.Light, LIGHT);
        }
    }

    public void setCustomTheme(int id, String theme) {
        setTheme(id);
        CURRENT_THEME = theme;
    }

    @Override
    protected void onStop() {
        SharedPreferences.Editor edit = prefs.edit();

        // запись последней темы
        edit.putString("Theme", CURRENT_THEME);

        // запись последнего фрагмента
        edit.putInt("LastFragment", CURRENT_FRAGMENT);

        // сохраниение записей
        edit.apply();

        super.onStop();
    }
}