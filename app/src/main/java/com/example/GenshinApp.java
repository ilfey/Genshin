package com.example;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;

import com.example.data.remote.characters.CharacterEntry;
import com.example.data.remote.characters.Characters;
import com.example.data.remote.characters.CharactersResponse;
import com.example.data.remote.dictionary.Dictionary;
import com.example.data.remote.dictionary.DictionaryEntry;
import com.example.data.remote.gacha.Gacha;
import com.example.data.remote.gacha.GachaEntry;
import com.example.genshin.R;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GenshinApp extends Application {

    public Retrofit retrofit;
    public List<CharacterEntry> characters;
    public List<DictionaryEntry> dictionary;
    public List<GachaEntry> gacha;
    public boolean connection;

    @Override
    public void onCreate() {
        super.onCreate();

        HttpLoggingInterceptor log = new HttpLoggingInterceptor();
        log.level(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient http = new OkHttpClient.Builder()
                .addInterceptor(log)
                .build();

        retrofit = new Retrofit.Builder()
                .client(http)
                .baseUrl("https://sushicat.pp.ua/api/genshin/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        new Thread(() -> {
            try {
                characters = retrofit.create(Characters.class).getCharacters().execute().body().entries;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                dictionary = retrofit.create(Dictionary.class).getDictionary().execute().body().entries;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                gacha = retrofit.create(Gacha.class).getGacha().execute().body().entries;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    // Метод для установки сатуса подключения
    public void hasConnection() {
        ConnectivityManager cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (wifiInfo != null && wifiInfo.isConnected()) {
            this.connection = true;
        }
        wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (wifiInfo != null && wifiInfo.isConnected()) {
            this.connection = true;
        }
        wifiInfo = cm.getActiveNetworkInfo();
        if (wifiInfo != null && wifiInfo.isConnected()) {
            this.connection = true;
        }
        this.connection = false;
    }
}
