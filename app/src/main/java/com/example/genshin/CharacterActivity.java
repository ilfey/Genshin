package com.example.genshin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.example.listeners.OnSwipeTouchListener;

public class CharacterActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		// получаем аргументы
		Bundle args = getIntent().getExtras();
		String url = args.getString("URL");

		// устанавливаем тему
		switch (args.getString("Theme")) {
			case "Dark": {
				setTheme(R.style.AppTheme_Dark);
				break;
			}
			case "Light": {
				setTheme(R.style.AppTheme_Light);
				break;
			}
		}

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_character);

		findViewById(R.id.layout).setOnTouchListener(new OnSwipeTouchListener(this){
			@Override
			public void onSwipeLeft() {
				System.out.println("Swipe Left");
			}

			@Override
			public void onSwipeRight() {
				System.out.println("Swipe Right");
			}
		});
		System.out.println(args.get("Characters"));

		System.out.println(url);
	}
}