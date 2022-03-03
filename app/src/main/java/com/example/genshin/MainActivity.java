package com.example.genshin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.dialogs.CustomDialog;
import com.example.genshin.fragments.AboutFragment;
import com.example.genshin.fragments.CharactersFragment;
import com.example.genshin.fragments.DictionaryFragment;
import com.example.genshin.fragments.LoginFragment;
import com.example.genshin.fragments.MainFragment;
import com.example.genshin.fragments.SettingsFragment;
import com.example.genshin.fragments.WishesFragment;
import com.example.genshin.fragments.HomeFragment;

public class MainActivity extends AppCompatActivity {

    public static String CURRENT_THEME;
    public static int CURRENT_FRAGMENT;
    public final String DARK = "Dark";
    public final String LIGHT = "Light";
    public SharedPreferences prefs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Загрузка или создание префсов
        prefs = getSharedPreferences("LocalStorage", Context.MODE_PRIVATE);

        // Установка темы
        setFinalTheme();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Загружаем главный фрагмент
        loadFullFragment(whichFullFragment(R.id.fragment_main));
    }

    // Метод для создания фрагмента по его id
    @SuppressLint("NonConstantResourceId")
    public Fragment whichFragment(int id) {
        switch (id) {
            case R.id.nav_characters:
                CURRENT_FRAGMENT = R.id.nav_characters;
                return new CharactersFragment();
            case R.id.nav_dictionary:
                CURRENT_FRAGMENT = R.id.nav_dictionary;
                return new DictionaryFragment();
            case R.id.nav_wishes:
                CURRENT_FRAGMENT = R.id.nav_wishes;
                return new WishesFragment();
            case R.id.nav_settings:
                CURRENT_FRAGMENT = R.id.nav_settings;
                return new SettingsFragment();
            case R.id.fragment_about:
                CURRENT_FRAGMENT = R.id.fragment_about;
                return new AboutFragment();
            default:
                CURRENT_FRAGMENT = R.id.nav_main;
                return new HomeFragment();
        }
    }

    // Метод для создания полного фрагмента по его id
    @SuppressLint("NonConstantResourceId")
    public Fragment whichFullFragment(int id){
        switch (id) {
            case R.id.fragment_login:
                return new LoginFragment();
            default:
                return new MainFragment();
        }
    }

    // Метод для отрисовки фрагмента
    public void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame, fragment);
//        transaction.addToBackStack(null);
        transaction.commit();
    }

    // Метод для отрисовки полного фрагмента
    public void loadFullFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.full_frame, fragment);
        if (!(fragment instanceof MainFragment)) transaction.addToBackStack(null);
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