package com.example.genshin.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.genshin.MainActivity;
import com.example.genshin.R;

public class SettingsFragment extends Fragment {
    private MainActivity activity;
    private Context ctx;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_settings, container, false);

        activity = ((MainActivity)getActivity());

        view.findViewById(R.id.toggle).setOnClickListener(v -> {
			switch (activity.CURRENT_THEME){
				case "Dark": {
					System.out.println("set light theme");
					activity.setTheme(R.style.AppTheme_Light, activity.LIGHT);
					activity.recreate();
					break;
				}
				case "Light": {
					System.out.println("set dark theme");
					activity.setTheme(R.style.AppTheme_Dark, activity.DARK);
					activity.recreate();
					break;
				}
				default: {
					System.out.println("set current theme");
					activity.setTheme(R.style.AppTheme_Dark, activity.DARK);
					activity.recreate();
					break;
				}
			}
		});

        view.findViewById(R.id.login).setOnClickListener(view1 -> {
            activity.loadFullFragment(activity.whichFullFragment(R.id.fragment_login));
        });

        return view;
    }
}