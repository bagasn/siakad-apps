package com.bagasnasution.lecturesapp.app.connect;


import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.bagasnasution.lecturesapp.app.config.Config;
import com.bagasnasution.lecturesapp.app.model.ResponseDefault;
import com.bagasnasution.lecturesapp.app.model.ResponseLogin;

import java.util.HashMap;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConnectRetrofit {
    private static final int timeout = 10000;
    private static final String BASE_URL = Config.BASE_URL;

    private static Retrofit getRetrofit() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(timeout, java.util.concurrent.TimeUnit.SECONDS);
        builder.readTimeout(timeout, java.util.concurrent.TimeUnit.SECONDS);
        builder.writeTimeout(timeout, java.util.concurrent.TimeUnit.SECONDS);
        builder.addInterceptor(interceptor);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(builder.build())
                .build();

        return retrofit;
    }

    private static Services getConnection() {
        return getRetrofit().create(Services.class);
    }

    private static ProgressDialog initProgressDialog(Context context) {
        ProgressDialog p = new ProgressDialog(context);
        p.setMessage("Loading...");
        p.setCancelable(false);
        return p;
    }

    private static boolean isSuccess(String responseCode) {
        if (responseCode.equals(Config.API_CODE_SUCCESS)) {
            return true;
        }
        return false;
    }

    private static Toast showMessage(Context context, String code, String message) {
        Toast toast = new Toast(context);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setText(message + " [" + code + "]");
        return toast;
    }

    public static synchronized void login(final Context context, HashMap<String, String> params, final OnResponse<ResponseLogin> listener) {
        final ProgressDialog p = initProgressDialog(context);
        p.show();

        getConnection().login(params).enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                if (isSuccess(response.body().getCode())) {
                    listener.onResponse(call, response);
                }
                else {
                    showMessage(context, response.body().getCode(), response.body().getMessage())
                            .show();
                }
                p.dismiss();
            }

            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable throwable) {
                listener.onFailure(call, throwable);
                p.dismiss();
            }
        });

    }




    public interface OnResponse<N> {
        void onResponse(Call<N> call, Response<N> response);
        void onFailure(Call<N> call, Throwable throwable);
    }

}
