package com.example.genshin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.GenshinApp;
import com.example.data.remote.characters.CharacterEntry;
import com.example.data.remote.characters.Characters;
import com.example.data.remote.characters.CharactersResponse;
import com.example.listeners.OnSwipeTouchListener;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CharacterActivity extends AppCompatActivity {

    private ImageView character_logo;
    private TextView title;
    private TextView rarity;
    private TextView weaperon;
    private TextView eye;
    private TextView fullname;
    private TextView sex;
    private TextView birthday;
    private TextView region;
    private TextView belonging;
    private TextView dest;
    private List<CharacterEntry> models;
    private int position = 0;
    private SwipeRefreshLayout refresh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // получаем аргументы
        Bundle args = getIntent().getExtras();
        position = args.getInt("Position");
        System.out.println(position);

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

        findViewById(R.id.layout).setOnTouchListener(new OnSwipeTouchListener(this) {
            @Override
            public void onSwipeLeft() {
                position++;
                loadContent();
            }

            @Override
            public void onSwipeRight() {
                position--;
                loadContent();
            }
        });

        models = ((GenshinApp)getApplication()).characters;
        character_logo = findViewById(R.id.character_logo);
        title = findViewById(R.id.title);
        rarity = findViewById(R.id.rarity);
        weaperon = findViewById(R.id.weaperon);
        eye = findViewById(R.id.eye);
        fullname = findViewById(R.id.fullname);
        sex = findViewById(R.id.sex);
        birthday = findViewById(R.id.birthday);
        region = findViewById(R.id.region);
        belonging = findViewById(R.id.belonging);
        dest = findViewById(R.id.dest);

        refresh = findViewById(R.id.refresh);
        refresh.setColorSchemeResources(
                R.color.primary
        );

        refresh.setOnRefreshListener(() -> {
            refresh.setRefreshing(false);
        });

        if (!((GenshinApp) getApplication()).hasConnection()) {
            System.out.println("No internet");
        } else {
            refresh.setRefreshing(false);
        }

        loadContent();
    }

    private void loadContent() {
        Picasso.get().load(String.format("https://sushicat.pp.ua/api%s", models.get(position).getIco())).into(character_logo);

        title.setText(models.get(position).getName());
        rarity.setText(models.get(position).getRarity());
        weaperon.setText(models.get(position).getWeapon());
        eye.setText(models.get(position).getEye());
        fullname.setText(models.get(position).getFullname());
        sex.setText(models.get(position).getGender());
        birthday.setText(models.get(position).getBirthday());
        region.setText(models.get(position).getRegion());
        dest.setText(models.get(position).getDesk());
    }
}