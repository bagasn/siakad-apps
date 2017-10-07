package com.bagasnasution.lecturesapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.bagasnasution.lecturesapp.app.engine.AppActivity;

public class SplashActivity extends AppActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        initComponent();
    }

    public void initComponent() {
        Thread timer = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    startActivity(new Intent(SplashActivity.this, HomeActivity.class));
                    finish();
                }
            }
        };
        timer.start();
    }


}
