package com.example.kolokvijum1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;


public class RecipeAddedReceiver extends BroadcastReceiver {

    private static final String PREFS_NAME = "RecipeReceiverPrefs";
    private static final String KEY_TOTAL_TIME = "total_prep_time";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent == null) return;

        int prepTime = intent.getIntExtra(RecipeFragment.EXTRA_PREP_TIME, 0);

        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        int totalTime = prefs.getInt(KEY_TOTAL_TIME, 0) + prepTime;
        prefs.edit().putInt(KEY_TOTAL_TIME, totalTime).apply();

        if (totalTime > 120) {
            Toast.makeText(context, "Predugo kuvanje!", Toast.LENGTH_LONG).show();
        }
    }
}
