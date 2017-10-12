package com.bagasnasution.lecturesapp;


import android.os.Bundle;
import android.support.annotation.Nullable;

import com.bagasnasution.lecturesapp.app.engine.AppActivity;

/**
 * Created by Batavianet on 10/12/2017.
 */

public class LoginActivity extends AppActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
}
