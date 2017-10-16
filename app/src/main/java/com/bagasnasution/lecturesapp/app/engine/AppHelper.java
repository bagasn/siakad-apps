package com.bagasnasution.lecturesapp.app.engine;

import android.content.Context;
import android.content.SharedPreferences;

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

}
