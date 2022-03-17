package com.example.genshin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Group;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.GenshinApp;
import com.example.data.remotely.characters.CharactersResponses;
import com.example.dialogs.AddDialog;
import com.example.listeners.OnSwipeTouchListener;
import com.example.listeners.TextChanged;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CharacterActivity extends AppCompatActivity {

    public Context ctx;
    private ImageView character_logo;
    private EditText title;
    private EditText rarity;
    private EditText weaperon;
    private EditText eye;
    private EditText fullname;
    private EditText sex;
    private EditText birthday;
    private EditText region;
    private EditText affiliation;
    private EditText dest;
    private Group editGroup;
    private ConstraintLayout destLayout;
    private List<CharactersResponses.Character> models;
    private int position = 0;
    private SwipeRefreshLayout refresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // получаем аргументы
        Bundle args = getIntent().getExtras();
        position = args.getInt("Position");

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
        actionBar.setHomeButtonEnabled(true);

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character);

        findViewById(R.id.layout).setOnTouchListener(new OnSwipeTouchListener(this) {
            @Override
            public void onSwipeLeft() {
                position++;
                if (position > models.size() - 1){
                    position = models.size() - 1;
                }
                loadContent();
            }

            @Override
            public void onSwipeRight() {
                position--;
                if (position < 0){
                    position = 0;
                }
                loadContent();
            }
        });

        ctx = this;
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
        affiliation = findViewById(R.id.affiliation);
        dest = findViewById(R.id.dest);

        editGroup = findViewById(R.id.edit_group);

        int[] editIds = editGroup.getReferencedIds();

        for (int id : editIds) {
            EditText view = findViewById(id);

            view.addTextChangedListener(new TextChanged(view));
        }

        refresh = findViewById(R.id.refresh);
        refresh.setColorSchemeResources(R.color.primary);

        refresh.setOnRefreshListener(() -> {
            loadContent();
            refresh.setRefreshing(false);
        });

        if (!((GenshinApp) getApplication()).hasConnection()) {
            Toast.makeText(ctx, "Нет подключения.", Toast.LENGTH_SHORT);
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
            menu.findItem(R.id.edit).setVisible(false);
            menu.findItem(R.id.cancel).setVisible(true);
            menu.findItem(R.id.undo).setVisible(true);
            menu.findItem(R.id.add).setVisible(true);
            menu.findItem(R.id.redo).setVisible(true);
            menu.findItem(R.id.done).setVisible(true);

            return onOptionsItemSelected(menuItem);
        });

        menu.findItem(R.id.cancel).setOnMenuItemClickListener(menuItem -> {
            menu.findItem(R.id.edit).setVisible(true);
            menu.findItem(R.id.cancel).setVisible(false);
            menu.findItem(R.id.undo).setVisible(false);
            menu.findItem(R.id.add).setVisible(false);
            menu.findItem(R.id.redo).setVisible(false);
            menu.findItem(R.id.done).setVisible(false);

            return onOptionsItemSelected(menuItem);
        });

        menu.findItem(R.id.done).setOnMenuItemClickListener(menuItem -> {
            menu.findItem(R.id.edit).setVisible(true);
            menu.findItem(R.id.cancel).setVisible(false);
            menu.findItem(R.id.undo).setVisible(false);
            menu.findItem(R.id.add).setVisible(false);
            menu.findItem(R.id.redo).setVisible(false);
            menu.findItem(R.id.done).setVisible(false);

            return onOptionsItemSelected(menuItem);
        });

        return super.onCreateOptionsMenu(menu);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home: {
                onBackPressed();
            }
            case R.id.edit: {
                changeEditable(true);

                return super.onOptionsItemSelected(item);
            }
            case R.id.cancel: {
                changeEditable(false);
                loadContent();

                return true;
            }
            case R.id.undo: {
                return true;
            }
            case R.id.add: {
                CharSequence[] fields = {
                        "Редкость",
                        "Оружие",
                        "Глаз Бога",
                        "Полное имя",
                        "Пол",
                        "День Рождения",
                        "Регион",
                        "Принадлежность"
                };

                AddDialog dialog = new AddDialog(CharacterActivity.this, fields);
                dialog.show(getSupportFragmentManager(), "custom");

                return true;
            }
            case R.id.redo: {
                return true;
            }
            case R.id.done: {
                changeEditable(false);

                return true;
            }
            default: {
                return true;
            }
        }
    }

    private void loadContent() {
        Picasso.with(this).load(String.format("https://sushicat.pp.ua/api%s", models.get(position).getCard())).into(character_logo);

        String titleString = models.get(position).getName();
        String rarityString = String.valueOf(models.get(position).getRarity());
        String weaperonString = models.get(position).getWeapon();
        String eyeString = models.get(position).getEye();
        String fullnameString = models.get(position).getFull_name();
        String sexString = models.get(position).getSex();
        String birthdayString = models.get(position).getBirthday();
        String regionString = models.get(position).getRegion();
        String affiliationString = models.get(position).getAffiliation();
        String destString = models.get(position).getDescription();

        CharactersResponses.Character entry = models.get(position);

        /*for (int id : editGroup.getReferencedIds()){
            EditText view = findViewById(id);
            view.setText();
        }*/

        title.setText(titleString);

        rarity.setText(rarityString);
        weaperon.setText(weaperonString);
        eye.setText(eyeString);
        fullname.setText(fullnameString);
        sex.setText(sexString);
        birthday.setText(birthdayString);
        region.setText(regionString);
        affiliation.setText(affiliationString);
        dest.setText(destString);
    }

    private void changeEditable(boolean editText){
        for (int id : editGroup.getReferencedIds()){
            EditText view = findViewById(id);
            view.setEnabled(editText);
        }
    }

    public void showToast(String msg){
        Toast warring = Toast.makeText(ctx, msg, Toast.LENGTH_SHORT);
        warring.show();
    }

    public void showToast(int res){
        Toast warring = Toast.makeText(ctx, res, Toast.LENGTH_SHORT);
        warring.show();
    }
}