package com.example.crys_.makeandroidgreatagain;

/**
 * Created by crys_ on 08.11.2017.
 */

import android.app.Dialog;
import android.content.Context;
import android.widget.Toast;

public class ToastMessage {
    public static void message(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}