package com.example.genshin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class CharacterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // получаем аргументы
        Bundle args = getIntent().getExtras();
        String url = args.getString("URL");

        // устанавливаем тему
        switch (args.getString("Theme")){
            case "Dark":{
                setTheme(R.style.AppTheme_Dark);
                break;
            }
            case "Light":{
                setTheme(R.style.AppTheme_Light);
                break;
            }
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character);

        System.out.println(url);
    }
}