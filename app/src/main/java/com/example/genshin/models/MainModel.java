package com.example.genshin.models;

import android.view.View;

public class MainModel {
	int icon;
	String title;
	View.OnClickListener onClick;

	public MainModel(int icon, String title, View.OnClickListener onClick) {
		this.icon = icon;
		this.title = title;
		this.onClick = onClick;
	}

	public int getIcon() {
		return icon;
	}

	public void setIcon(int icon) {
		this.icon = icon;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public View.OnClickListener getOnClick() {
		return onClick;
	}

	public void setOnClick(View.OnClickListener onClick) {
		this.onClick = onClick;
	}
}