package com.example.genshin.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.genshin.MainActivity;
import com.example.genshin.R;

public class AboutFragment extends Fragment {
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_about, container, false);
		Button btn = view.findViewById(R.id.toggler);
		btn.setOnClickListener(v -> {
			MainActivity mainActivity = ((MainActivity)getActivity());
			switch (mainActivity.CURRENT_THEME){
				case "Dark": {
					System.out.println("set light theme");
					mainActivity.setTheme(R.style.AppTheme_Light, mainActivity.LIGHT);
					mainActivity.recreate();
					break;
				}
				case "Light": {
					System.out.println("set dark theme");
					mainActivity.setTheme(R.style.AppTheme_Dark, mainActivity.DARK);
					mainActivity.recreate();
					break;
				}
				default:{
					System.out.println("set current theme");
					mainActivity.setTheme(R.style.AppTheme_Dark, mainActivity.DARK);
					mainActivity.recreate();
					break;
				}
			}
		});
		return view;
	}
}