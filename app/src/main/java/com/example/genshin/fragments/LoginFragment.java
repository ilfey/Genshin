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
import com.example.dialogs.CustomDialog;
import com.example.dialogs.RobberDialog;
import com.example.genshin.MainActivity;
import com.example.genshin.R;

public class LoginFragment extends Fragment {

    private GenshinApp app;
    private MainActivity activity;
    private Context ctx;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        activity = (MainActivity) getActivity();
        app = (GenshinApp) activity.getApplication();
        ctx = getContext();

        app.hasConnection();

        view.findViewById(R.id.login).setOnClickListener(view1 -> {
            if (((EditText) view.findViewById(R.id.username)).getText().toString().equals("Putin")){
                RobberDialog dialog = new RobberDialog();
                dialog.show(getActivity().getSupportFragmentManager(), "custom");
            }
        });

        return view;
    }
}