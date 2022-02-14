package com.example.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public class CustomDialog extends DialogFragment {
    private String title;
    private String description;

    public CustomDialog(String title, String description) {
        this.title = title;
        this.description = description;
    }

    @NonNull
    public Dialog onCreateDialog(Bundle state){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        return builder
                .setTitle(title)
                .setMessage(description)
                .setPositiveButton("OK", null)
                .create();
    }
}