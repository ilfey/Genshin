package com.example;

import android.app.Application;

import com.example.data.remote.Dictionary;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GenshinApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        configRetrofit();
    }

    private void configRetrofit(){
        HttpLoggingInterceptor log = new HttpLoggingInterceptor();
        log.level(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient http = new OkHttpClient.Builder()
            .addInterceptor(log)
            .build();

        Retrofit retrofit = new Retrofit.Builder()
            .client(http)
            .baseUrl("https://sushicat.pp.ua/api/genshin/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    }
}
