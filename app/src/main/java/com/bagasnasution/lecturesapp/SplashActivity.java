package com.bagasnasution.lecturesapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.bagasnasution.lecturesapp.app.engine.AppActivity;
import com.bagasnasution.lecturesapp.app.engine.AppHelper;

public class SplashActivity extends AppActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        initComponent();
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }

    private void initComponent() {
        Thread timer = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    validasiLogin();
                }
            }
        };
        timer.start();
    }

    private synchronized boolean validasiLogin() {
        AppHelper helper = new AppHelper().getInstance(this);

        if (isDestroyed())
            return false;

        if (helper.isLoginInitiate()) {
            startActivity(new Intent(this, HomeActivity.class));
            finish();
            return true;
        }
        else {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
        return false;
    }


}
