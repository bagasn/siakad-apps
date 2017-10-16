package com.bagasnasution.lecturesapp;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bagasnasution.lecturesapp.app.connect.ConnectRetrofit;
import com.bagasnasution.lecturesapp.app.engine.AppActivity;
import com.bagasnasution.lecturesapp.app.model.ResponseLogin;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Batavianet on 10/12/2017.
 */

public class LoginActivity extends AppActivity implements View.OnClickListener{
    private Button btn_login;
    private EditText edtx_username;
    private EditText edtx_password;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn_login = (Button) findViewById(R.id.btn_login);
        edtx_username = (EditText) findViewById(R.id.edtx_username);
        edtx_password = (EditText) findViewById(R.id.edtx_password);

        btn_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        validasiLogin();
    }

    private void validasiLogin() {
        if (edtx_username.getText().toString().isEmpty()) {
            Toast.makeText(this, "Username Kosong!", Toast.LENGTH_LONG).show();
            edtx_username.requestFocus();
            return;
        }
        else if (edtx_password.getText().toString().isEmpty()) {
            Toast.makeText(this, "Password Kosong!", Toast.LENGTH_LONG).show();
            edtx_password.requestFocus();
            return;
        }
        else {
            doLogin();
        }
    }

    private void doLogin() {
        String username = edtx_username.getText().toString();
        String password = toMD5(edtx_password.getText().toString());

        ConnectRetrofit.login(this, username, password, new ConnectRetrofit.OnResponse<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                Toast.makeText(getApplicationContext(), response.body().toString(), Toast.LENGTH_LONG).show();
                Log.e("Response", "--- Login result --->> " + response.body().toString());
            }

            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable throwable) {
                Toast.makeText(getApplicationContext(), throwable.toString(), Toast.LENGTH_LONG).show();
                Log.e("Failure", "--- Login result --->> ", throwable);
            }
        });


    }
}
