package com.example.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.example.genshin.CharacterActivity;
import com.example.genshin.R;

public class AddDialog extends DialogFragment {

    private CharSequence[] items;
    private CharacterActivity activity;
    private EditText rarity;
    private EditText weapon;
    private EditText eye;
    private EditText fullname;
    private EditText sex;
    private EditText birthday;
    private EditText region;
    private EditText affiliation;

    public AddDialog(CharacterActivity activity, CharSequence[] items) {
        this.activity = activity;
        this.items = items;

        this.rarity = activity.findViewById(R.id.rarity);
        this.weapon = activity.findViewById(R.id.weapon);
        this.eye = activity.findViewById(R.id.eye);
        this.fullname = activity.findViewById(R.id.fullname);
        this.sex = activity.findViewById(R.id.sex);
        this.birthday = activity.findViewById(R.id.birthday);
        this.region = activity.findViewById(R.id.region);
        this.affiliation = activity.findViewById(R.id.affiliation);
    }

    @NonNull
    public Dialog onCreateDialog(Bundle state){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setItems(items, (dialogInterface, i) -> {
            switch (i) {
                case 0: {
                    rarity.setText(R.string.rarity);
                    dismiss();
                    break;
                }
                case 1: {
                    weapon.setText(R.string.weapon);
                    dismiss();
                    break;
                }
                case 2: {
                    eye.setText(R.string.eye);
                    dismiss();
                    break;
                }
                case 3: {
                    fullname.setText(R.string.full_name);
                    dismiss();
                    break;
                }
                case 4: {
                    sex.setText(R.string.sex);
                    dismiss();
                    break;
                }
                case 5: {
                    birthday.setText(R.string.birthday);
                    dismiss();
                    break;
                }
                case 6: {
                    region.setText(R.string.region);
                    dismiss();
                    break;
                }
                case 7: {
                    affiliation.setText(R.string.affiliation);
                    dismiss();
                    break;
                }
                default:{
                    dismiss();
                }
            }
//            activity.showToast(items[i].toString());
//            dialogInterface.dismiss();
        });

        Dialog dialog  = builder.create();

        dialog.getWindow().setBackgroundDrawableResource(R.drawable.cr15dp);

        return dialog;
    }
}