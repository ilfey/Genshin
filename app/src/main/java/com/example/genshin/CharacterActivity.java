package com.example.genshin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.GenshinApp;
import com.example.data.remotely.characters.CharacterEntry;
import com.example.listeners.OnSwipeTouchListener;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CharacterActivity extends AppCompatActivity {

    private ImageView character_logo;
    private EditText title;
    private EditText rarity;
    private EditText weaperon;
    private EditText eye;
    private EditText fullname;
    private EditText sex;
    private EditText birthday;
    private EditText region;
    private EditText belonging;
    private EditText dest;
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

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);

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

        character_logo = findViewById(R.id.character_logo);
        models = ((GenshinApp) getApplication()).characters;
        title = findViewById(R.id.title);
        rarity = findViewById(R.id.rarity);
        weaperon = findViewById(R.id.weapon);
        eye = findViewById(R.id.eye);
        fullname = findViewById(R.id.fullname);
        sex = findViewById(R.id.sex);
        birthday = findViewById(R.id.birthday);
        region = findViewById(R.id.region);
        belonging = findViewById(R.id.belonging);
        dest = findViewById(R.id.dest);

        refresh = findViewById(R.id.refresh);
        refresh.setColorSchemeResources(R.color.primary);

        refresh.setOnRefreshListener(() -> {
            loadContent();
            refresh.setRefreshing(false);
        });

        if (!((GenshinApp) getApplication()).hasConnection()) {
            System.out.println("No internet");
        } else {
            refresh.setRefreshing(false);
        }
        loadContent();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar, menu);

        menu.findItem(R.id.edit).setOnMenuItemClickListener(menuItem -> {
            menu.findItem(R.id.done).setVisible(true);
            menu.findItem(R.id.cancel).setVisible(true);
            menu.findItem(R.id.edit).setVisible(false);
            menu.findItem(R.id.undo).setVisible(true);
            menu.findItem(R.id.redo).setVisible(true);

            return onOptionsItemSelected(menuItem);
        });

        menu.findItem(R.id.cancel).setOnMenuItemClickListener(menuItem -> {
            menu.findItem(R.id.edit).setVisible(true);
            menu.findItem(R.id.cancel).setVisible(false);
            menu.findItem(R.id.done).setVisible(false);
            menu.findItem(R.id.undo).setVisible(false);
            menu.findItem(R.id.redo).setVisible(false);

            return onOptionsItemSelected(menuItem);
        });

        menu.findItem(R.id.done).setOnMenuItemClickListener(menuItem -> {
            menu.findItem(R.id.edit).setVisible(true);
            menu.findItem(R.id.cancel).setVisible(false);
            menu.findItem(R.id.done).setVisible(false);
            menu.findItem(R.id.undo).setVisible(false);
            menu.findItem(R.id.redo).setVisible(false);

            return onOptionsItemSelected(menuItem);
        });

        return super.onCreateOptionsMenu(menu);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.edit: {
                changeVisibility(true);

                return super.onOptionsItemSelected(item);
            }
            case R.id.cancel: {
                changeVisibility(false);

                return true;
            }
            case R.id.done: {
                changeVisibility(false);

                return true;
            }
            default: {
                return true;
            }
        }
    }

    private void loadContent() {
        Picasso.with(this).load(String.format("https://sushicat.pp.ua/api%s", models.get(position).getIco())).into(character_logo);

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

    private void changeVisibility(boolean editText){
        title.setEnabled(editText);
        rarity.setEnabled(editText);
        weaperon.setEnabled(editText);
        eye.setEnabled(editText);
        fullname.setEnabled(editText);
        sex.setEnabled(editText);
        birthday.setEnabled(editText);
        region.setEnabled(editText);
        belonging.setEnabled(editText);
        dest.setEnabled(editText);
    }
}