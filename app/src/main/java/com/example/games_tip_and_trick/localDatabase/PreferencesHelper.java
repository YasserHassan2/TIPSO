package com.example.games_tip_and_trick.localDatabase;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesHelper {


    private static SharedPreferences getPref(Context context) {
        return context.getSharedPreferences(Constants.MY_TIPSO_NAME, Context.MODE_PRIVATE);
    }
    public static void setDatabaseVersion(Context context, int databaseVersion) {
        getPref(context).edit().putInt(Constants.DATABASE_VERSION, databaseVersion).apply();
    }

    public static int getDatabaseVersion(Context context) {
        return getPref(context).getInt(Constants.DATABASE_VERSION, -1);
    }
}
