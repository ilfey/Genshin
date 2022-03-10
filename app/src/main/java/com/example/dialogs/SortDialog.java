package com.example.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.ArrayRes;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.example.genshin.CharacterActivity;
import com.example.genshin.R;

public class SortDialog extends DialogFragment {

    @NonNull
    public Dialog onCreateDialog(Bundle state){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setItems(R.array.sort_by, (dialogInterface, i) -> {

        });
//        builder.setTitle("zxc");

        Dialog dialog  = builder.create();

        dialog.getWindow().setBackgroundDrawableResource(R.drawable.cr15dp);

        return dialog;
    }
}
