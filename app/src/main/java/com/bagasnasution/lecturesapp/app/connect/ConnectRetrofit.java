package com.bagasnasution.lecturesapp.app.connect;


import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

import com.bagasnasution.lecturesapp.app.config.Config;
import com.bagasnasution.lecturesapp.app.model.ResponseLogin;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConnectRetrofit {
    private static final int timeout = 20;
    private static final String BASE_URL = Config.BASE_URL;
    private static final String TOKEN = Config.API_TOKEN;

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

    private static void showMessage(Context context, String code, String message) {
        Toast.makeText(context, code + ": " + message, Toast.LENGTH_LONG).show();
    }

    public static synchronized void login(final Context context, String username, String password, final OnResponse<ResponseLogin> listener) {
        final ProgressDialog p = initProgressDialog(context);
        p.show();

        RequestBody token = RequestBody.create(MediaType.parse("text/plain"), TOKEN);
        RequestBody rUsername = RequestBody.create(MediaType.parse("text/plain"), username);
        RequestBody rPassword = RequestBody.create(MediaType.parse("text/plain"), password);

        getConnection().login(token, rUsername, rPassword).enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                if (isSuccess(response.body().getCode())) {
                    listener.onResponse(call, response);
                }
                else {
                    showMessage(context, response.body().getCode(), response.body().getMessage());
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
