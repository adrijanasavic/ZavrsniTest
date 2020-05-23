package com.example.zavrsnitest.tools;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.EditText;


public class Tools {


    public static String KEY = "KEY";
    public static final String NOTIF_CHANNEL_ID = "notif_channel_007";


    public static boolean validateInput(EditText editText) {

        String titleInput = editText.getText().toString().trim();

        if (titleInput.isEmpty()) {
            editText.setError( "Polje ne moze da bude prazno!!!" );
            return false;
        } else {
            editText.setError( null );
            return true;
        }
    }

}
