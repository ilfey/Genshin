package com.example;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import androidx.room.Room;

import com.example.data.remotely.characters.CharacterEntry;
import com.example.data.remotely.dictionary.DictionaryEntry;
import com.example.data.remotely.gacha.GachaEntry;
import com.example.genshin.R;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
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
    }

    // Метод для установки сатуса подключения
    public boolean hasConnection() {
        ConnectivityManager cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (wifiInfo != null && wifiInfo.isConnected()) {
            this.connection = true;
            return true;
        }
        wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (wifiInfo != null && wifiInfo.isConnected()) {
            this.connection = true;
            return true;
        }
        wifiInfo = cm.getActiveNetworkInfo();
        if (wifiInfo != null && wifiInfo.isConnected()) {
            this.connection = true;
            return true;
        }
        this.connection = false;
        return false;
    }
}
