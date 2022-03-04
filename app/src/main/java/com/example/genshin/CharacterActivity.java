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
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.GenshinApp;
import com.example.data.remotely.characters.CharacterEntry;
import com.example.listeners.OnSwipeTouchListener;
import com.squareup.picasso.Picasso;

import java.util.List;

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
    private EditText titleEdit;
    private EditText rarityEdit;
    private EditText weaperonEdit;
    private EditText eyeEdit;
    private EditText fullnameEdit;
    private EditText sexEdit;
    private EditText birthdayEdit;
    private EditText regionEdit;
    private EditText belongingEdit;
    private EditText destEdit;
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

        models = ((GenshinApp) getApplication()).characters;
        character_logo = findViewById(R.id.character_logo);
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
        titleEdit = findViewById(R.id.title_edit);
        rarityEdit = findViewById(R.id.rarity_edit);
        weaperonEdit = findViewById(R.id.weapon_edit);
        eyeEdit = findViewById(R.id.eye_edit);
        fullnameEdit = findViewById(R.id.fullname_edit);
        sexEdit = findViewById(R.id.sex_edit);
        birthdayEdit = findViewById(R.id.birthday_edit);
        regionEdit = findViewById(R.id.region_edit);
        belongingEdit = findViewById(R.id.belonging_edit);
        destEdit = findViewById(R.id.dest_edit);

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

            return onOptionsItemSelected(menuItem);
        });

        menu.findItem(R.id.cancel).setOnMenuItemClickListener(menuItem -> {
            menu.findItem(R.id.edit).setVisible(true);
            menu.findItem(R.id.cancel).setVisible(false);
            menu.findItem(R.id.done).setVisible(false);

            return onOptionsItemSelected(menuItem);
        });

        menu.findItem(R.id.done).setOnMenuItemClickListener(menuItem -> {
            menu.findItem(R.id.edit).setVisible(true);
            menu.findItem(R.id.cancel).setVisible(false);
            menu.findItem(R.id.done).setVisible(false);

            return onOptionsItemSelected(menuItem);
        });

        return super.onCreateOptionsMenu(menu);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.edit: {
                changeVisibility(View.GONE, View.VISIBLE);
                moveText(true);

                return super.onOptionsItemSelected(item);
            }
            case R.id.cancel: {
                changeVisibility(View.VISIBLE, View.GONE);

                return true;
            }
            case R.id.done: {
                changeVisibility(View.VISIBLE, View.GONE);
                moveText(false);

                dest.setText(destEdit.getText());

                return true;
            }
            default: {
                return true;
            }
        }
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

    private void changeVisibility(int text, int editText){
        title.setVisibility(text);
        rarity.setVisibility(text);
        weaperon.setVisibility(text);
        eye.setVisibility(text);
        fullname.setVisibility(text);
        sex.setVisibility(text);
        birthday.setVisibility(text);
        region.setVisibility(text);
        belonging.setVisibility(text);
        dest.setVisibility(text);
        titleEdit.setVisibility(editText);
        rarityEdit.setVisibility(editText);
        weaperonEdit.setVisibility(editText);
        eyeEdit.setVisibility(editText);
        fullnameEdit.setVisibility(editText);
        sexEdit.setVisibility(editText);
        birthdayEdit.setVisibility(editText);
        regionEdit.setVisibility(editText);
        belongingEdit.setVisibility(editText);
        destEdit.setVisibility(editText);
    }

    private void moveText(boolean toEditText){
        if (toEditText){
            titleEdit.setText(title.getText());
            rarityEdit.setText(rarity.getText());
            weaperonEdit.setText(weaperon.getText());
            eyeEdit.setText(eye.getText());
            fullnameEdit.setText(fullname.getText());
            sexEdit.setText(sex.getText());
            birthdayEdit.setText(birthday.getText());
            regionEdit.setText(region.getText());
            belongingEdit.setText(belonging.getText());
            destEdit.setText(dest.getText());
        } else {
            title.setText(titleEdit.getText());
            rarity.setText(rarityEdit.getText());
            weaperon.setText(weaperonEdit.getText());
            eye.setText(eyeEdit.getText());
            fullname.setText(fullnameEdit.getText());
            sex.setText(sexEdit.getText());
            birthday.setText(birthdayEdit.getText());
            region.setText(regionEdit.getText());
            belonging.setText(belongingEdit.getText());
            dest.setText(destEdit.getText());
        }
    }
}