package com.example.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.example.genshin.CharacterActivity;
import com.example.genshin.R;

public class AddDialog extends DialogFragment {

    private CharSequence[] items;
    private CharacterActivity activity;

    public AddDialog(CharacterActivity activity, CharSequence[] items) {
        this.activity = activity;
        this.items = items;
    }

    @NonNull
    public Dialog onCreateDialog(Bundle state){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setItems(items, (dialogInterface, i) -> {
            activity.showToast(items[i].toString());
            dialogInterface.dismiss();
        });

        Dialog dialog  = builder.create();

        dialog.getWindow().setBackgroundDrawableResource(R.drawable.cr15dp);

        return dialog;
    }
}