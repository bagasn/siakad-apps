package com.bagasnasution.lecturesapp;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bagasnasution.lecturesapp.app.config.Config;
import com.bagasnasution.lecturesapp.app.connect.ConnectRetrofit;
import com.bagasnasution.lecturesapp.app.db.DBUser;
import com.bagasnasution.lecturesapp.app.engine.AppActivity;
import com.bagasnasution.lecturesapp.app.engine.AppHelper;
import com.bagasnasution.lecturesapp.app.model.response.ResponseLogin;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Batavianet on 10/12/2017.
 */

public class LoginActivity extends AppActivity implements View.OnClickListener {
    private Button btn_login;
    private EditText edtx_username;
    private EditText edtx_password;

    private ProgressDialog pDialog;
    private ProgressBar progressBar;
    private AppHelper helper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        helper = new AppHelper().getInstance(this);
        pDialog = AppHelper.makeProgressDialod(this);

        btn_login = (Button) findViewById(R.id.btn_login);
        edtx_username = (EditText) findViewById(R.id.edtx_username);
        edtx_password = (EditText) findViewById(R.id.edtx_password);
        progressBar = (ProgressBar) findViewById(R.id.prgs_load);

        btn_login.setOnClickListener(this);
        edtx_password.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == 221) {
                    onClick(btn_login);
                    return true;
                }
                return false;
            }
        });

    }

    @Override
    public void onClick(View v) {
        hideKeyBoard(v);
        validasiLogin();
    }

    private void validasiLogin() {
        if (edtx_username.getText().toString().isEmpty()) {
            Toast.makeText(this, "Username Kosong!", Toast.LENGTH_LONG).show();
            edtx_username.requestFocus();
            return;
        } else if (edtx_password.getText().toString().isEmpty()) {
            Toast.makeText(this, "Password Kosong!", Toast.LENGTH_LONG).show();
            edtx_password.requestFocus();
            return;
        } else {
            doLogin();
        }
    }

    private void doLogin() {
        setDisable();
        String username = edtx_username.getText().toString();
        String password = toMD5(edtx_password.getText().toString());

        Log.i("SendLOGIN", "Username: " + username + ", Password: " + password);

        ConnectRetrofit.login(this, username, password, new ConnectRetrofit.OnResponse<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                if (response.body().getCode().equals(Config.API_CODE_SUCCESS)) {
                    onLoginSuccess(response);
                } else {
                    AppHelper.showToast(LoginActivity.this, response.body().getMessage());
                    setEnable();
                }

            }

            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable throwable) {
                Toast.makeText(getApplicationContext(), throwable.toString(), Toast.LENGTH_LONG).show();
                Log.e("Failure", "--- Login Failure --->> ", throwable);
                setEnable();
            }
        });
    }

    private void onLoginSuccess(Response<ResponseLogin> response) {
        DBUser.User user = new DBUser.User();
        ResponseLogin.Data data = response.body().getData();
        user.setNama(data.getNama());
        user.setNpm(data.getNim());
        user.setEmail(data.getEmail());
        user.setFakultas(data.getFakultas());
        user.setProdi(data.getProdi());
        user.setJenisKelamin(data.getJenisKelamin());
        user.setLinkFoto(data.getLinkFoto());

        if (DBUser.insertDataUser(this, user)) {
            new AppHelper().getInstance(this).setLoginInitiate(true);
            startActivity(new Intent(this, HomeActivity.class));
            finish();
        } else {
            Toast.makeText(this, "DataBaseExcaption", Toast.LENGTH_SHORT).show();
        }

    }

    private void setEnable() {
        edtx_username.setEnabled(true);
        edtx_password.setEnabled(true);
        btn_login.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }

    private void setDisable() {
        edtx_username.setEnabled(false);
        edtx_password.setEnabled(false);
        btn_login.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideKeyBoard(View view) {
        try {

            InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (view != null) {
                manager.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        } catch (NullPointerException e) {

        }
    }

}
