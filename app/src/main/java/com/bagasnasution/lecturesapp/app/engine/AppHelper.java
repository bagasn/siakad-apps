package com.bagasnasution.lecturesapp.app.engine;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.bagasnasution.lecturesapp.app.config.Config;

/**
 * Created by Batavianet on 10/13/2017.
 */

public class AppHelper {
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    public AppHelper getInstance(Context context){
        mSharedPreferences = context.getSharedPreferences(Config.PREFERENCES_MASTER, Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
        return this;
    }

    public boolean isLoginInitiate() {
        boolean temp = mSharedPreferences.getBoolean(Config.PREFERENCES_HAS_LOGIN, false);
        return temp;
    }

    public void setLoginInitiate(boolean isLogin) {
        mEditor.putBoolean(Config.PREFERENCES_HAS_LOGIN, isLogin);
        mEditor.commit();
    }

    public void removeLoginInitiate() {
        mEditor.clear();
        mEditor.commit();
    }

    public static void showToast(Context context, String mText){
        try {
            Toast.makeText(context, mText, Toast.LENGTH_LONG).show();
        } catch (NullPointerException e) {}
    }

    public static void showToast(Context context, String mText, String mCode){
        try {
        Toast.makeText(context, mText + "[" + mCode + "]", Toast.LENGTH_LONG).show();
        } catch (NullPointerException e) {}
    }

    public static ProgressDialog makeProgressDialod(Context context) {
        return makeProgressDialod(context, "Loading...");
    }

    public static ProgressDialog makeProgressDialod(Context context, String message) {
        return makeProgressDialod(context, message, false);
    }

    public static ProgressDialog makeProgressDialod(Context context, String message, boolean cancelAble) {
        ProgressDialog p = new ProgressDialog(context);
        p.setMessage(message);
        p.setCancelable(cancelAble);
        return p;
    }

    public static String getSimpleName(String fullname) {
        String[] names = fullname.split(" ");

        if (names.length > 2) {
            fullname = names[0] + " " + names[1];
            for (int i = 2; i < names.length; i++) {
                fullname += " " + names[i].toUpperCase().charAt(0);
            }
        }
        return fullname;
    }

    public static String getFirstName(String fullname) {
        String[] nyet = fullname.split(" ");

        return nyet[0];
    }

    public static void hideKeyBoard(Context context, View view) {
        try {
            InputMethodManager manager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (view != null) {
                manager.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        } catch (Exception e) {
            Log.e("AppHelper", "hideKeyBoard: " + "failed to hide soft input keyboard");
        }
    }
}
