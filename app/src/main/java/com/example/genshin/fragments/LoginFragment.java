package com.example.genshin.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.GenshinApp;
import com.example.data.remotely.auth.Auth;
import com.example.data.remotely.auth.LoginBody;
import com.example.dialogs.CustomDialog;
import com.example.dialogs.RobberDialog;
import com.example.genshin.MainActivity;
import com.example.genshin.R;
import com.example.genshin.databinding.ActivityMainBinding;
import com.example.genshin.databinding.FragmentLoginBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;
    private GenshinApp app;
    private MainActivity activity;
    private Context ctx;
    private EditText username;
    private EditText password;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentLoginBinding.inflate(inflater, container, false);

        View view = binding.getRoot();

        activity = (MainActivity) getActivity();
        app = (GenshinApp) activity.getApplication();
        ctx = getContext();

        app.hasConnection();

        username = binding.form.findViewById(R.id.username);
        password = binding.form.findViewById(R.id.password);

        binding.form.findViewById(R.id.login).setOnClickListener(view1 -> {

            LoginBody body = new LoginBody(username.getText().toString(), password.getText().toString());
            app.retrofit.create(Auth.class).login(body).enqueue(new Callback<Object>() {
                @Override
                public void onResponse(Call<Object> call, Response<Object> response) {
                    Toast toast = new Toast(ctx);
                    toast.setText(String.valueOf(response.code()));
                    toast.show();
                    switch (response.code()) {
                        case 200: {
                            CustomDialog dialog = new CustomDialog("Вход удался", username.getText().toString() + ":" + password.getText().toString());
                            dialog.show(activity.getSupportFragmentManager(), "custom");
                        }
                        case 401: {
                            CustomDialog dialog = new CustomDialog("Вход удался", username.getText().toString() + ":" + password.getText().toString());
                            dialog.show(activity.getSupportFragmentManager(), "custom");
                        }
                        default:{
                            CustomDialog dialog = new CustomDialog("Вход не удался", "Я сам хз почему, напишите создателю апк и бекэнда, что он еблан, в дискорд: Josty#0626");
                            dialog.show(activity.getSupportFragmentManager(), "custom");
                        }
                    }
                }

                @Override
                public void onFailure(Call<Object> call, Throwable t) {
                    CustomDialog dialog = new CustomDialog("Вход не удался", "Проверьте подключение к интернету");
                    dialog.show(activity.getSupportFragmentManager(), "custom");
                }
            });
        });

        return view;
    }
}