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
	public Dictionary dictionary;
	public Characters characters;
	public Gacha gacha;

	@Override
	public void onCreate() {
		super.onCreate();
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
		dictionary = retrofit.create(Dictionary.class);
		characters = retrofit.create(Characters.class);
		gacha = retrofit.create(Gacha.class);
	}
}
