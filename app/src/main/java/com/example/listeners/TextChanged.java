package com.example.listeners;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

public class TextChanged implements TextWatcher {
    private View view;

    public TextChanged(View view) {
        this.view = view;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if (charSequence == null || charSequence == "" || charSequence.toString().isEmpty()){
            view.setVisibility(View.GONE);
        } else {
            view.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
