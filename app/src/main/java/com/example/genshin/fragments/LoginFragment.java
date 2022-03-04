package com.example.genshin.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.dialogs.CustomDialog;
import com.example.dialogs.RobberDialog;
import com.example.genshin.R;

public class LoginFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        view.findViewById(R.id.login).setOnClickListener(view1 -> {
            if (((EditText) view.findViewById(R.id.username)).getText().toString().equals("Putin")){
                RobberDialog dialog = new RobberDialog();
                dialog.show(getActivity().getSupportFragmentManager(), "custom");
            }
        });

        return view;
    }
}