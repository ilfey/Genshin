package com.example;

import android.app.Application;

import com.example.data.remote.characters.Characters;
import com.example.data.remote.dictionary.Dictionary;
import com.example.data.remote.gacha.Gacha;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GenshinApp extends Application {

	public Retrofit retrofit;

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

		retrofit = new Retrofit.Builder()
				.client(http)
				.baseUrl("https://sushicat.pp.ua/api/genshin/api/")
				.addConverterFactory(GsonConverterFactory.create())
				.build();
	}
}
