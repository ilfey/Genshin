package com.example.genshin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.GenshinApp;
import com.example.dialogs.CustomDialog;
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
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Загрузка или создание префсов
        prefs = getSharedPreferences("LocalStorage", Context.MODE_PRIVATE);
        // Установка темы
        setFinalTheme();
        // Инициализируем активити
        super.onCreate(savedInstanceState);
        // Отрисовываем активити
        setContentView(R.layout.activity_main);
        // Получаем навбар
        BottomNavigationView navbar = findViewById(R.id.nav_bar);
        // Устанавливаем слушатель событий на навбар
        navbar.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        // Загружаем последний фрагмент
        loadFragment(whichFragment(prefs.getInt("LastFragment", R.id.main)));
        // Проверяем на первое открытие апк
        if (prefs.getBoolean("Visited", true)) {
            // диалог при первом открытии
            showDialog(getString(R.string.welcome), "Какое-то описание)");
            // Создаем запись в префсах о том, что апк уже открывалось ранее
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("Visited", false);
            editor.apply();
        }
    }

    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
        // Загружаем нужный фрагмент
        loadFragment(whichFragment(item.getItemId()));
        return true;
    };

    // Метод для создания фрагмента по его id
    @SuppressLint("NonConstantResourceId")
    private Fragment whichFragment(int id) {
        switch (id) {
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

    // Метод для отрисовки фрагмента
    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void setFinalTheme() {
        switch (prefs.getString("Theme", DARK)) {
            case DARK: {
                setTheme(R.style.AppTheme_Dark, DARK);
                return;
            }
            case LIGHT: {
                setTheme(R.style.AppTheme_Light, LIGHT);
                return;
            }
        }
    }

    // Перегружаем метод setTheme для удобства
    public void setTheme(int id, String theme) {
        setTheme(id);
        CURRENT_THEME = theme;
    }

    @Override
    protected void onStop() {
        // Создаем редактор префсов
        SharedPreferences.Editor edit = prefs.edit();
        // Создаем запись о последней теме
        edit.putString("Theme", CURRENT_THEME);
        // Создаем запись о последнем фрагменте
        edit.putInt("LastFragment", CURRENT_FRAGMENT);
        // Сохраняем записи
        edit.apply();
        super.onStop();
    }

    // Показываем диалоговое окно
    public void showDialog(String title, String description) {
        CustomDialog dialog = new CustomDialog(title, description);
        dialog.show(getSupportFragmentManager(), "custom");
    }
}