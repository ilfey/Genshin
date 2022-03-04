package com.example.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.example.genshin.R;

public class RobberDialog extends DialogFragment {
    @NonNull
    public Dialog onCreateDialog(Bundle state){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        return builder
                .setView(R.layout.robber_view)
                .setPositiveButton("OK", null)
                .create();
    }
}